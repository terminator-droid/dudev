package com.dudev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedals", schema = "public")
@PrimaryKeyJoinColumn(name = "id")
public class Pedal extends Product{

    @Builder(builderMethodName = "pedalBuilder")
    public Pedal(Category category, Brand brand, ChangeType changeType, double changeValue, String changeWish, User user, String media, LocalDateTime timestamp, double price, boolean closed, String description, List<UserLikedProduct> userLikedProducts, List<OfferProduct> offerProducts, Integer id, String model, String shopPrice) {
        super(category, brand, changeType, changeValue, changeWish, user, media, timestamp, price, closed, description, userLikedProducts, offerProducts);
        this.id = id;
        this.model = model;
        this.shopPrice = shopPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String model;

    private String shopPrice;

}
