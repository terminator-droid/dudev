package com.dudev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOfferProduct is a Querydsl query type for OfferProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOfferProduct extends EntityPathBase<OfferProduct> {

    private static final long serialVersionUID = -2010800633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOfferProduct offerProduct = new QOfferProduct("offerProduct");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOffer offer;

    public final QProduct product;

    public QOfferProduct(String variable) {
        this(OfferProduct.class, forVariable(variable), INITS);
    }

    public QOfferProduct(Path<? extends OfferProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOfferProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOfferProduct(PathMetadata metadata, PathInits inits) {
        this(OfferProduct.class, metadata, inits);
    }

    public QOfferProduct(Class<? extends OfferProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.offer = inits.isInitialized("offer") ? new QOffer(forProperty("offer"), inits.get("offer")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

