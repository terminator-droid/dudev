package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserLikedProduct.class)
public abstract class UserLikedProduct_ extends com.dudev.entity.BaseEntity_ {

	public static volatile SingularAttribute<UserLikedProduct, Product> product;
	public static volatile SingularAttribute<UserLikedProduct, User> user;

	public static final String PRODUCT = "product";
	public static final String USER = "user";

}

