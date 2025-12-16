package com.demo.cart.service;

import static com.demo.cart.service.util.ShoppingCartMessages.INVALID_ITEM_ERROR_MESSAGE;
import static com.demo.cart.service.util.ShoppingCartMessages.INVALID_MODIFICATION_TYPE_ERROR_MESSAGE;
import static com.demo.cart.service.util.ShoppingCartMessages.NO_CART_ERROR_MESSAGE;
import static com.demo.cart.service.util.ShoppingCartMessages.REMOVE_ITEM_ERROR_MESSAGE;
import static com.demo.cart.service.util.ShoppingCartMessages.SUBSCRIPTION_ADD_ERROR_MESSAGE;
import static com.demo.cart.service.util.ShoppingCartMessages.SUBSCRIPTION_QUANTITY_ERROR_MESSAGE;

import com.demo.cart.controller.dto.request.AddItemRequestDto;
import com.demo.cart.controller.dto.request.ModifyItemRequestDto;
import com.demo.cart.controller.dto.response.cart.ShoppingCartDisplayDto;
import com.demo.cart.controller.dto.response.ExploreItemDisplayDto;
import com.demo.cart.exception.CartNotFoundException;
import com.demo.cart.exception.InvalidItemException;
import com.demo.cart.exception.InvalidModificationTypeException;
import com.demo.cart.exception.SubscriptionAlreadyExistsException;
import com.demo.cart.model.Item.Item;
import com.demo.cart.model.Item.ItemType;
import com.demo.cart.model.cart.Action;
import com.demo.cart.model.cart.CartItem;
import com.demo.cart.model.cart.ShoppingCart;
import com.demo.cart.repository.CartRepository;
import com.demo.cart.repository.ItemRepository;
import com.demo.cart.service.mapper.CartEventMapper;
import com.demo.cart.service.mapper.CartMapper;
import com.demo.cart.service.mapper.ItemMapper;
import com.demo.cart.service.statistic.CartEventPublisher;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ShoppingCartService {

  private final ItemRepository itemRepository;
  private final CartRepository cartRepository;
  private final ItemMapper itemMapper;
  private final CartMapper cartMapper;
  private final CartEventMapper cartEventMapper;
  private final CartEventPublisher cartEventPublisher;

  public List<ExploreItemDisplayDto> fetchExploreDisplay() {
    List<Item> items = itemRepository.findAll();
    return items.stream().map(itemMapper::toDisplayDto).toList();
  }

  public ShoppingCartDisplayDto getShoppingCart(String userId) {
    return mapToDto(cartRepository.findByCustomerId(userId).orElseThrow(() -> new CartNotFoundException(NO_CART_ERROR_MESSAGE)));
  }

  public List<ShoppingCartDisplayDto> fetchAllCarts() {
    List<ShoppingCart> carts = cartRepository.findAll();
    return carts.stream()
        .map(this::mapToDto)
        .toList();
  }

  @Transactional
  public ShoppingCartDisplayDto addToCart(String customerId, AddItemRequestDto selectedItem) {

    ItemType type = checkItemType(selectedItem.getItemId(), selectedItem.getOfferId());

    ShoppingCart userCart = cartRepository.findByCustomerId(customerId).orElse(createNewCart(customerId));

    if(type == ItemType.DEVICE){
      if(selectedItem.getAction()==Action.ADD)
        return mapToDto(addOrDeleteDevice(userCart, selectedItem, Integer::sum));
      else
        return mapToDto(addOrDeleteDevice(userCart, selectedItem,  (current, delta) -> current - delta));
    }
    else
      return mapToDto(addOrDeleteSubscription(userCart, selectedItem));
  }

  @Transactional
  public ShoppingCartDisplayDto modifyCartItem(String customerId, ModifyItemRequestDto modificationReq) {

    ItemType type =  checkItemType(modificationReq.getItemId(), modificationReq.getNewOfferId());

    if(type != ItemType.SUBSCRIPTION){
      log.error("Invalid modification request - only type SUBSCRIPTION can be modified.");
      throw new InvalidModificationTypeException(INVALID_MODIFICATION_TYPE_ERROR_MESSAGE);
    }

    ShoppingCart userCart = cartRepository.findByCustomerId(customerId).orElse(createNewCart(customerId));
    CartItem newSubscription = CartItem.builder()
        .itemId(modificationReq.getItemId())
        .offerId(modificationReq.getNewOfferId())
        .oldOfferId(modificationReq.getOldOfferId())
        .action(Action.MODIFY)
        .build();

    Optional<CartItem> existing = existsInCart(userCart, modificationReq.getItemId(), modificationReq.getAction());

    existing.ifPresent(cartItem -> userCart.getItems().remove(cartItem)); //already modified item subscription, so overwriting it

    userCart.getItems().add(newSubscription);

    return mapToDto(cartRepository.save(userCart));

  }

  public void deleteCart(String userId){
    ShoppingCart cart = cartRepository.findByCustomerId(userId).orElseThrow(() -> new CartNotFoundException(NO_CART_ERROR_MESSAGE));
    cartRepository.delete(cart);
  }

  public void makeOrder(String userId){
    ShoppingCart cart = cartRepository.findByCustomerId(userId).orElseThrow(() -> new CartNotFoundException(NO_CART_ERROR_MESSAGE));
    cart.getItems()
        .stream()
        .map(cartEventMapper::toCartEvent)
        .forEach(cartEventPublisher::publishEvent);
    cartRepository.delete(cart);
  }

  private ShoppingCartDisplayDto mapToDto(ShoppingCart cart) {
    List<Long> ids = cart.getItems().stream()
        .flatMap(item -> Stream.of(item.getItemId(), item.getOldOfferId()))
        .filter(Objects::nonNull)
        .toList();
    return cartMapper.toCartDto(cart, findCartItems(ids));
  }

  private List<Item> findCartItems(List<Long> itemIds) {
    return itemRepository.findByItemIdIn(itemIds);
  }

  private ShoppingCart createNewCart(String customerId) {
    ShoppingCart cart = new ShoppingCart();
    cart.setCustomerId(customerId);
    return cart;
  }

  private Integer getCartQuantity(ShoppingCart cart, AddItemRequestDto newItem) {
    List<CartItem> items = cart.getItems();
    return items.stream()
        .filter(item -> (item.getOfferId().equals(newItem.getOfferId())))
        .map(CartItem::getQuantity)
        .findFirst()
        .orElse(0);
  }

  private Optional<CartItem> existsInCart(ShoppingCart cart, Long newItemId, Action action) {
    List<CartItem> items = cart.getItems();
    return items.stream()
        .filter(item -> (item.getItemId().equals(newItemId)&&item.getAction().equals(action)))
        .findFirst();
  }

  private ItemType checkItemType(Long itemId, Long offerId) {
    Item dbItem = itemRepository.findByItemIdAndOfferId(itemId, offerId)
        .orElseThrow(() -> new InvalidItemException(INVALID_ITEM_ERROR_MESSAGE));
    return dbItem.getType();
  }

  private ShoppingCart addOrDeleteDevice(ShoppingCart cart, AddItemRequestDto newItem, BiFunction<Integer, Integer, Integer> quantityUpdater) {
    if (getCartQuantity(cart, newItem) == 0) {

      if(newItem.getAction()==Action.DELETE){
        log.warn("Invalid delete request - only DEVICES from the cart can be deleted.");
        throw new InvalidItemException(REMOVE_ITEM_ERROR_MESSAGE);
      }

      cart.getItems().add(itemMapper.toCartItem(newItem));
    } else {
      cart.getItems().stream()
          .filter(item -> item.getOfferId().equals(newItem.getOfferId()))
          .findFirst()
          .ifPresent(item -> {
            Integer newQuantity = quantityUpdater.apply(item.getQuantity(), newItem.getQuantity());
            if(newQuantity>0){
              item.setQuantity(newQuantity);
            }else{
              cart.getItems().remove(item);
            }
          });
    }
    cart.setUpdatedAt(Instant.now());
    return cartRepository.save(cart);
  }

  private ShoppingCart addOrDeleteSubscription(ShoppingCart cart, AddItemRequestDto newItem) {

    if(newItem.getQuantity()!=1){
      throw new SubscriptionAlreadyExistsException(SUBSCRIPTION_QUANTITY_ERROR_MESSAGE);
    }

    if(existsInCart(cart, newItem.getItemId(), newItem.getAction()).isPresent()){
      log.info("Trying to do the same action with the same itemId, throwing an exception");
      throw new SubscriptionAlreadyExistsException(SUBSCRIPTION_ADD_ERROR_MESSAGE);
    }
    Optional<CartItem> inCartOpposite = existsInCart(cart, newItem.getItemId(),getOppositeAction(newItem.getAction()));

    if(inCartOpposite.isEmpty()) {
      log.info("Adding new cart item with itemId and offerId: {}, {} - {}", newItem.getItemId(), newItem.getOfferId(), newItem.getAction());
      cart.getItems().add(itemMapper.toCartItem(newItem));
    }else{
      log.info("Found item with the same itemId in cart: {}", inCartOpposite.get());
      if(isSameOffer(inCartOpposite.get(), newItem)){
          log.info("Removing previously selected action for subscription plan {} from the cart.", newItem.getOfferId());
          cart.getItems().remove(inCartOpposite.get());

      }else{
        cart.getItems().add(itemMapper.toCartItem(newItem));
      }
    }

    cart.setUpdatedAt(Instant.now());
    return cartRepository.save(cart);

  }

  private Boolean isSameOffer(CartItem item1, AddItemRequestDto item2) {
    return item1.getOfferId().equals(item2.getOfferId());
  }

  private Action getOppositeAction(Action action){
    return action == Action.ADD? Action.DELETE: Action.ADD;
  }

}
