package com.dudev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Builder
@ToString(exclude = "offerProducts")
@EqualsAndHashCode(exclude = "offerProducts")
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity<Integer> {


    @ManyToOne(optional = false)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne(optional = false)
    @JoinColumn(name = "change_type_id")
    private ChangeType changeType;

    @Builder.Default
    @OneToMany(mappedBy = "offer")
    private List<OfferProduct> offerProducts = new ArrayList<>();

    private double changeValue;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    private boolean accepted;


}