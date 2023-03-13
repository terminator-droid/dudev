package com.dudev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOffer is a Querydsl query type for Offer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOffer extends EntityPathBase<Offer> {

    private static final long serialVersionUID = 706098632L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOffer offer = new QOffer("offer");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath accepted = createBoolean("accepted");

    public final QUser buyer;

    public final QChangeType changeType;

    public final NumberPath<Double> changeValue = createNumber("changeValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<OfferProduct, QOfferProduct> offerProducts = this.<OfferProduct, QOfferProduct>createList("offerProducts", OfferProduct.class, QOfferProduct.class, PathInits.DIRECT2);

    public final QUser seller;

    public final DateTimePath<java.time.LocalDateTime> timestamp = createDateTime("timestamp", java.time.LocalDateTime.class);

    public QOffer(String variable) {
        this(Offer.class, forVariable(variable), INITS);
    }

    public QOffer(Path<? extends Offer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOffer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOffer(PathMetadata metadata, PathInits inits) {
        this(Offer.class, metadata, inits);
    }

    public QOffer(Class<? extends Offer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new QUser(forProperty("buyer")) : null;
        this.changeType = inits.isInitialized("changeType") ? new QChangeType(forProperty("changeType")) : null;
        this.seller = inits.isInitialized("seller") ? new QUser(forProperty("seller")) : null;
    }

}

