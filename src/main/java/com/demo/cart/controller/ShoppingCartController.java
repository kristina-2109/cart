package com.demo.cart.controller;

import com.demo.cart.controller.dto.request.AddItemRequestDto;
import com.demo.cart.controller.dto.request.ModifyItemRequestDto;
import com.demo.cart.controller.dto.response.cart.ShoppingCartDisplayDto;
import com.demo.cart.model.cart.Action;
import com.demo.cart.service.ShoppingCartService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ShoppingCartController {
  ShoppingCartService shoppingCartService;

  @PreAuthorize("hasAnyRole('CUSTOMER')")
  @GetMapping("/cart/items")
  public ResponseEntity<ShoppingCartDisplayDto> getCart( @AuthenticationPrincipal Jwt jwt){
    String customerId = jwt.getSubject();
    return ResponseEntity.ok().body(shoppingCartService.getShoppingCart(customerId));
  }

  @PreAuthorize("hasAnyRole('CUSTOMER')")
  @PostMapping("/cart/items")
  public ResponseEntity<ShoppingCartDisplayDto> addToCart( @AuthenticationPrincipal Jwt jwt, @RequestBody AddItemRequestDto itemDto){
    itemDto.setAction(Action.ADD);
    return ResponseEntity.ok().body(shoppingCartService.addToCart(jwt.getSubject(), itemDto));
  }

  @PreAuthorize("hasAnyRole('CUSTOMER')")
  @PutMapping("/cart/items")
  public ResponseEntity<ShoppingCartDisplayDto> modifyCart( @AuthenticationPrincipal Jwt jwt, @RequestBody ModifyItemRequestDto itemDto){
    itemDto.setAction(Action.MODIFY);
    return ResponseEntity.ok().body(shoppingCartService.modifyCartItem(jwt.getSubject(), itemDto));
  }

  @PreAuthorize("hasAnyRole('CUSTOMER')")
  @DeleteMapping("/cart/items")
  public ResponseEntity<ShoppingCartDisplayDto> removeFromCart( @AuthenticationPrincipal Jwt jwt, @RequestBody AddItemRequestDto itemDto){
    itemDto.setAction(Action.DELETE);
    return ResponseEntity.ok().body(shoppingCartService.addToCart(jwt.getSubject(), itemDto));
  }

  @PreAuthorize("hasAnyRole('CUSTOMER')")
  @DeleteMapping("/cart")
  public ResponseEntity<String> deleteCart( @AuthenticationPrincipal Jwt jwt){
    shoppingCartService.deleteCart(jwt.getSubject());
    return ResponseEntity.ok().body("Cart successfully deleted.");
  }

  @PreAuthorize("hasAnyRole('CUSTOMER')")
  @PostMapping("/cart")
  public ResponseEntity<String> confirmCart( @AuthenticationPrincipal Jwt jwt){
    shoppingCartService.makeOrder(jwt.getSubject());
    return ResponseEntity.ok().body("Order successfully confirmed.");
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping("/carts")
  public ResponseEntity<List<ShoppingCartDisplayDto>> getCarts(){
    return ResponseEntity.ok().body(shoppingCartService.fetchAllCarts());
  }



}
