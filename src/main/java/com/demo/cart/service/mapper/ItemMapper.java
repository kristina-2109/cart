package com.demo.cart.service.mapper;

import com.demo.cart.controller.dto.request.AddItemRequestDto;
import com.demo.cart.controller.dto.response.ExploreItemDisplayDto;
import com.demo.cart.controller.dto.response.OfferDisplayDto;
import com.demo.cart.controller.dto.response.cart.PriceDisplayDto;
import com.demo.cart.model.Item.Item;
import com.demo.cart.model.Item.Offer;
import com.demo.cart.model.Item.Price;
import com.demo.cart.model.cart.CartItem;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

  public CartItem toCartItem(AddItemRequestDto itemDto){
    return CartItem.builder()
        .itemId(itemDto.getItemId())
        .offerId(itemDto.getOfferId())
        .action(itemDto.getAction())
        .quantity(itemDto.getQuantity())
        .build();
  }

  public ExploreItemDisplayDto toDisplayDto(Item item) {
    return ExploreItemDisplayDto.builder()
        .name(item.getName())
        .type(item.getType())
        .offers(mapOffers(item.getOffers()))
        .build();
  }

  private List<OfferDisplayDto> mapOffers(List<Offer> offers) {
    return offers.stream()
        .map(this::toOfferDisplayDto)
        .toList();
  }

  private OfferDisplayDto toOfferDisplayDto(Offer offer) {
    return OfferDisplayDto.builder()
        .offerName(offer.getOfferName())
        .price(toPriceDisplayDto(offer.getPrice()))
        .build();
  }


  private PriceDisplayDto toPriceDisplayDto(Price price) {
    return PriceDisplayDto.builder()
        .type(price.getType())
        .amount(price.getAmount())
        .recurrence(price.getRecurrence())
        .build();
  }
}
