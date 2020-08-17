package org.fasttrackit.videogameshop.persistance;

import org.fasttrackit.videogameshop.domain.Cart;
import org.fasttrackit.videogameshop.transfer.cart.CartResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Modifying
    @Query(value = "DELETE from cart_product WHERE " +
            "product_id = :productId and cart_id = 1", nativeQuery = true)
    void deleteByProductId(@Param("productId")long productId);

}
