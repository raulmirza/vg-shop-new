package org.fasttrackit.videogameshop.transfer.cart;

import java.util.List;

public class AddProductsToCartRequest {

    private long userId;
    private List<Long> productIds;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "AddProductsToCartRequest{" +
                "userId=" + userId +
                ", productIds=" + productIds +
                '}';
    }
}
