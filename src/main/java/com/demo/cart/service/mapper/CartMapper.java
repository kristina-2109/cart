package com.demo.cart.service.mapper;

import com.demo.cart.controller.dto.response.cart.CartItemDisplayDto;
import com.demo.cart.controller.dto.response.cart.ShoppingCartDisplayDto;
import com.demo.cart.controller.dto.response.cart.PriceDisplayDto;
import com.demo.cart.model.Item.Item;
import com.demo.cart.model.cart.CartItem;
import com.demo.cart.model.cart.ShoppingCart;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CartMapper {

  public ShoppingCartDisplayDto toCartDto(ShoppingCart cart, List<Item> items){
    Map<Long, Item> itemMap = items.stream()
        .collect(Collectors.toMap(Item::getItemId, Function.identity()));

    List<CartItemDisplayDto> cartItemDisplayDtos =
        cart.getItems().stream()
        .map(cartItem -> mapCartItemDto(cartItem, itemMap))
        .toList();

    return ShoppingCartDisplayDto.builder()
        .customerId(cart.getCustomerId())
        .items(cartItemDisplayDtos)
        .build();
  }

  private CartItemDisplayDto mapCartItemDto(CartItem cartItem, Map<Long, Item> itemMap) {
    CartItemDisplayDto dto = CartItemDisplayDto.builder()
        .quantity(cartItem.getQuantity())
        .action(cartItem.getAction())
        .build();

    Item item = itemMap.get(cartItem.getItemId());

    if (item != null) {
      item.getOffers().stream()
          .filter(o -> o.getOfferId().equals(cartItem.getOfferId()))
          .findFirst()
          .ifPresent(offer -> {
            PriceDisplayDto priceDto =
                PriceDisplayDto.builder()
                .type(offer.getPrice().getType())
                .amount(offer.getPrice().getAmount())
                .recurrence(offer.getPrice().getRecurrence())
                .build();
            dto.setCartItemName(offer.getOfferName());
            dto.setPrice(priceDto);
          });
      if(cartItem.getOldOfferId()!=null){
        item.getOffers().stream()
            .filter(o -> o.getOfferId().equals(cartItem.getOldOfferId()))
            .findFirst()
            .ifPresent(offer ->dto.setOldOfferName(offer.getOfferName()));
      }
    }

    return dto;
  }

}
