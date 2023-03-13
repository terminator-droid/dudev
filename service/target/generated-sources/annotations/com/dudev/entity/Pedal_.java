package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedal.class)
public abstract class Pedal_ extends com.dudev.entity.Product_ {

	public static volatile SingularAttribute<Pedal, Double> shopPrice;
	public static volatile SingularAttribute<Pedal, String> model;
	public static volatile SingularAttribute<Pedal, Integer> id;

	public static final String SHOP_PRICE = "shopPrice";
	public static final String MODEL = "model";
	public static final String ID = "id";

}

