package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ChangeType.class)
public abstract class ChangeType_ extends com.dudev.entity.BaseEntity_ {

	public static volatile SingularAttribute<ChangeType, String> description;
	public static volatile ListAttribute<ChangeType, Product> products;

	public static final String DESCRIPTION = "description";
	public static final String PRODUCTS = "products";

}

