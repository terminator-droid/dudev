package com.dudev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"userLikedProducts", "offerProducts"})
@EqualsAndHashCode(exclude = {"userLikedProducts", "offerProducts"})
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
public class Product extends BaseEntity<Integer> {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "change_type_id")
    private ChangeType changeType;
    private double changeValue;
    private String changeWish;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String media;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @Column(nullable = false)
    private double price;
    private boolean closed;
    @Column(nullable = false)
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<UserLikedProduct> userLikedProducts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<OfferProduct> offerProducts = new ArrayList<>();
}
