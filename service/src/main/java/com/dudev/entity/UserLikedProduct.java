package com.dudev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_liked_product")
@SuperBuilder
public class UserLikedProduct extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public void setUser(User user) {
        this.user = user;
        user.getUserLikedProducts().add(this);
    }

    public void setProduct(Product product) {
        this.product = product;
        product.getUserLikedProducts().add(this);
    }
}
