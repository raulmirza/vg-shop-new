package org.fasttrackit.videogameshop.web;


import org.fasttrackit.videogameshop.domain.Cart;
import org.fasttrackit.videogameshop.service.CartService;
import org.fasttrackit.videogameshop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.videogameshop.transfer.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> addProductsToCart(
            @PathVariable long userId, @Valid @RequestBody AddProductsToCartRequest request) {
        cartService.addProductsToCart(userId, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable long userId) {
        CartResponse cart = cartService.getCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/{productId}")
    public ResponseEntity<CartResponse> deleteProductFromCart(@PathVariable long productId, @PathVariable long cartId) {
        CartResponse cartResponse = cartService.deleteProductFromCart(productId);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }
}
