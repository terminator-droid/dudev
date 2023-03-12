package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Brand.class)
public abstract class Brand_ extends com.dudev.entity.BaseEntity_ {

	public static volatile SingularAttribute<Brand, String> name;
	public static volatile SingularAttribute<Brand, Category> category;

	public static final String NAME = "name";
	public static final String CATEGORY = "category";

}

