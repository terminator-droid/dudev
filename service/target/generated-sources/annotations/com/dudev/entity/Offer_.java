package com.dudev.entity;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Offer.class)
public abstract class Offer_ extends com.dudev.entity.BaseEntity_ {

	public static volatile SingularAttribute<Offer, User> seller;
	public static volatile ListAttribute<Offer, OfferProduct> offerProducts;
	public static volatile SingularAttribute<Offer, ChangeType> changeType;
	public static volatile SingularAttribute<Offer, Boolean> accepted;
	public static volatile SingularAttribute<Offer, Double> changeValue;
	public static volatile SingularAttribute<Offer, User> buyer;
	public static volatile SingularAttribute<Offer, LocalDateTime> timestamp;

	public static final String SELLER = "seller";
	public static final String OFFER_PRODUCTS = "offerProducts";
	public static final String CHANGE_TYPE = "changeType";
	public static final String ACCEPTED = "accepted";
	public static final String CHANGE_VALUE = "changeValue";
	public static final String BUYER = "buyer";
	public static final String TIMESTAMP = "timestamp";

}

