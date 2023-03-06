package com.dudev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "offer_product")
public class OfferProduct extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void setOffer(Offer offer) {
        this.offer = offer;
        offer.getOfferProducts().add(this);
    }

    public void setProduct(Product product) {
        this.product = product;
        product.getOfferProducts().add(this);
    }
}
