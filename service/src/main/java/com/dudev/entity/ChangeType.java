package com.dudev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
@Builder
@Entity
@Table(name = "change_type")
public class ChangeType extends BaseEntity<Integer>{

    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "changeType")
    @Builder.Default
    private List<Product> products = new ArrayList<>();

}
