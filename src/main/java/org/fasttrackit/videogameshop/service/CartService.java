package org.fasttrackit.videogameshop.service;

import org.fasttrackit.videogameshop.domain.Cart;
import org.fasttrackit.videogameshop.domain.Product;
import org.fasttrackit.videogameshop.domain.User;
import org.fasttrackit.videogameshop.exception.ResourceNotFoundException;
import org.fasttrackit.videogameshop.persistance.CartRepository;
import org.fasttrackit.videogameshop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.videogameshop.transfer.cart.CartResponse;
import org.fasttrackit.videogameshop.transfer.cart.ProductInCartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Transactional
    public void addProductsToCart(long cartId, AddProductsToCartRequest request) {
        LOGGER.info("Adding products to cart: {}: {}", cartId, request);

        final Cart cart = cartRepository.findById(cartId)
                .orElse(new Cart());

        if (cart.getUser() == null) {
            final User user = userService.getUser(cartId);

            cart.setUser(user);
        }

        for (Long productId : request.getProductIds()) {
            Product product = productService.getProduct(productId);
            cart.addProductToCart(product);
        }
        cartRepository.save(cart);
    }
    @Transactional
    public CartResponse deleteProductFromCart(long productId) {
        LOGGER.info("Delete product from cart: {}", productId);
        cartRepository.deleteByProductId(productId);
        CartResponse cart;
        cart = getCart(1);
        return cart;
    }

    @Transactional
    public CartResponse getCart(long id) {
        LOGGER.info("Retrieving cart {}", id);
        final Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart " + id + "does not exist"));

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());

        List<ProductInCartResponse> productDtos = new ArrayList<>();

        for (Product product : cart.getProducts()) {
            ProductInCartResponse productResponse = new ProductInCartResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setImageUrl(product.getImageUrl());

            productDtos.add(productResponse);
        }
      cartResponse.setProducts(productDtos);

        return cartResponse;
    }
}
