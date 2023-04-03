package com.dudev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChangeType is a Querydsl query type for ChangeType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChangeType extends EntityPathBase<ChangeType> {

    private static final long serialVersionUID = -158865634L;

    public static final QChangeType changeType = new QChangeType("changeType");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<Product, QProduct> products = this.<Product, QProduct>createList("products", Product.class, QProduct.class, PathInits.DIRECT2);

    public QChangeType(String variable) {
        super(ChangeType.class, forVariable(variable));
    }

    public QChangeType(Path<? extends ChangeType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChangeType(PathMetadata metadata) {
        super(ChangeType.class, metadata);
    }

}

