package com.dudev.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(name = "withUserProducts",
        attributeNodes = {
                @NamedAttributeNode(value = "userProducts", subgraph = "withBrandAndChangeType")
        }, subgraphs = {
        @NamedSubgraph(name = "withBrandAndChangeType", attributeNodes = {@NamedAttributeNode("brand"), @NamedAttributeNode("changeType")})
}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"userLikedProducts", "userProducts"})
@EqualsAndHashCode(exclude = {"userLikedProducts", "userProducts"})
@SuperBuilder
@Entity
@Table(name = "users", schema = "public")
public class User extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String password;

    private String address;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserLikedProduct> userLikedProducts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Product> userProducts = new ArrayList<>();
}
