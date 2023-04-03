package com.dudev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGuitar is a Querydsl query type for Guitar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGuitar extends EntityPathBase<Guitar> {

    private static final long serialVersionUID = 199144094L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuitar guitar = new QGuitar("guitar");

    public final QProduct _super;

    // inherited
    public final QBrand brand;

    // inherited
    public final QCategory category;

    // inherited
    public final QChangeType changeType;

    //inherited
    public final NumberPath<Double> changeValue;

    //inherited
    public final StringPath changeWish;

    //inherited
    public final BooleanPath closed;

    public final StringPath country = createString("country");

    //inherited
    public final StringPath description;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final StringPath media;

    public final StringPath model = createString("model");

    //inherited
    public final ListPath<OfferProduct, QOfferProduct> offerProducts;

    public final StringPath pickUps = createString("pickUps");

    //inherited
    public final NumberPath<Double> price;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> timestamp;

    // inherited
    public final QUser user;

    //inherited
    public final ListPath<UserLikedProduct, QUserLikedProduct> userLikedProducts;

    public final StringPath wood = createString("wood");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QGuitar(String variable) {
        this(Guitar.class, forVariable(variable), INITS);
    }

    public QGuitar(Path<? extends Guitar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGuitar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGuitar(PathMetadata metadata, PathInits inits) {
        this(Guitar.class, metadata, inits);
    }

    public QGuitar(Class<? extends Guitar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QProduct(type, metadata, inits);
        this.brand = _super.brand;
        this.category = _super.category;
        this.changeType = _super.changeType;
        this.changeValue = _super.changeValue;
        this.changeWish = _super.changeWish;
        this.closed = _super.closed;
        this.description = _super.description;
        this.media = _super.media;
        this.offerProducts = _super.offerProducts;
        this.price = _super.price;
        this.timestamp = _super.timestamp;
        this.user = _super.user;
        this.userLikedProducts = _super.userLikedProducts;
    }

}

