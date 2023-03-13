package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ extends com.dudev.entity.BaseEntity_ {

	public static volatile ListAttribute<Product, OfferProduct> offerProducts;
	public static volatile ListAttribute<Product, UserLikedProduct> userLikedProducts;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, ChangeType> changeType;
	public static volatile SingularAttribute<Product, String> changeWish;
	public static volatile SingularAttribute<Product, Boolean> closed;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, Double> changeValue;
	public static volatile SingularAttribute<Product, String> media;
	public static volatile SingularAttribute<Product, Category> category;
	public static volatile SingularAttribute<Product, Brand> brand;
	public static volatile SingularAttribute<Product, User> user;

	public static final String OFFER_PRODUCTS = "offerProducts";
	public static final String USER_LIKED_PRODUCTS = "userLikedProducts";
	public static final String PRICE = "price";
	public static final String CHANGE_TYPE = "changeType";
	public static final String CHANGE_WISH = "changeWish";
	public static final String CLOSED = "closed";
	public static final String DESCRIPTION = "description";
	public static final String CHANGE_VALUE = "changeValue";
	public static final String MEDIA = "media";
	public static final String CATEGORY = "category";
	public static final String BRAND = "brand";
	public static final String USER = "user";

}

