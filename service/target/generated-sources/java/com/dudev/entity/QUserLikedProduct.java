package com.dudev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLikedProduct is a Querydsl query type for UserLikedProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLikedProduct extends EntityPathBase<UserLikedProduct> {

    private static final long serialVersionUID = 375768321L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLikedProduct userLikedProduct = new QUserLikedProduct("userLikedProduct");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> created_at = createDateTime("created_at", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QProduct product;

    public final QUser user;

    public QUserLikedProduct(String variable) {
        this(UserLikedProduct.class, forVariable(variable), INITS);
    }

    public QUserLikedProduct(Path<? extends UserLikedProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLikedProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLikedProduct(PathMetadata metadata, PathInits inits) {
        this(UserLikedProduct.class, metadata, inits);
    }

    public QUserLikedProduct(Class<? extends UserLikedProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

