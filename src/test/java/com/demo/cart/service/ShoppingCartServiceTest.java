package com.demo.cart.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.demo.cart.controller.dto.request.AddItemRequestDto;
import com.demo.cart.exception.InvalidItemException;
import com.demo.cart.model.Item.Item;
import com.demo.cart.model.Item.ItemType;
import com.demo.cart.model.Item.Offer;
import com.demo.cart.model.cart.Action;
import com.demo.cart.model.cart.ShoppingCart;
import com.demo.cart.repository.CartRepository;
import com.demo.cart.repository.ItemRepository;
import com.demo.cart.service.mapper.CartEventMapper;
import com.demo.cart.service.mapper.CartMapper;
import com.demo.cart.service.mapper.ItemMapper;
import com.demo.cart.service.statistic.CartEventPublisher;
import com.demo.cart.service.util.ShoppingCartMessages;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ShoppingCartServiceTest {

  private CartRepository cartRepository;
  private ItemRepository itemRepository;
  private ItemMapper itemMapper;
  private CartMapper cartMapper;
  private CartEventMapper cartEventMapper;
  private CartEventPublisher cartEventPublisher;

  private ShoppingCartService shoppingCartService;

  @BeforeEach
  void setUp() {
    cartRepository = Mockito.mock(CartRepository.class);
    itemRepository = Mockito.mock(ItemRepository.class);
    itemMapper = Mockito.mock(ItemMapper.class);
    cartMapper = Mockito.mock(CartMapper.class);
    cartEventMapper = Mockito.mock(CartEventMapper.class);
    cartEventPublisher = Mockito.mock(CartEventPublisher.class);

    shoppingCartService = new ShoppingCartService(
        itemRepository,
        cartRepository,
        itemMapper,
        cartMapper,
        cartEventMapper,
        cartEventPublisher
    );
  }

  @Test
  void deletingNonExistingDeviceShouldThrowException() {
    String customerId = "customer-1";

    when(cartRepository.findByCustomerId(customerId))
        .thenReturn(Optional.of(new ShoppingCart()));

    when(itemRepository.findByItemIdAndOfferId(1L, 102L))
    .thenReturn(Optional.of(new Item("507f1f77bcf86cd799439011", 1L, "iPhone 15", ItemType.DEVICE, new ArrayList<Offer>())));

    AddItemRequestDto deleteRequest = AddItemRequestDto.builder()
        .itemId(1L)
        .offerId(102L)
        .quantity(1)
        .action(Action.DELETE)
        .build();


    assertThatThrownBy(() -> shoppingCartService.addToCart(customerId, deleteRequest))
        .isInstanceOf(InvalidItemException.class)
        .hasMessageContaining(ShoppingCartMessages.REMOVE_ITEM_ERROR_MESSAGE);
  }

}
