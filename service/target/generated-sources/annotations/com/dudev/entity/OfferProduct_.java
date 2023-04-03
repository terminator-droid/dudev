package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OfferProduct.class)
public abstract class OfferProduct_ extends com.dudev.entity.BaseEntity_ {

	public static volatile SingularAttribute<OfferProduct, Offer> offer;
	public static volatile SingularAttribute<OfferProduct, Product> product;

	public static final String OFFER = "offer";
	public static final String PRODUCT = "product";

}

