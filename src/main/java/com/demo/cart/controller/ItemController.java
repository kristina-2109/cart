package com.demo.cart.controller;

import com.demo.cart.controller.dto.response.ExploreItemDisplayDto;
import com.demo.cart.service.ShoppingCartService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class ItemController {
  ShoppingCartService service;

  @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
  @GetMapping("/explore")
  public ResponseEntity<List<ExploreItemDisplayDto>> getCart(){
    List<ExploreItemDisplayDto> items = service.fetchExploreDisplay();
    return ResponseEntity.ok().body(items);
  }

}
