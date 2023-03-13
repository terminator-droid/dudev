package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Guitar.class)
public abstract class Guitar_ extends com.dudev.entity.Product_ {

	public static volatile SingularAttribute<Guitar, String> country;
	public static volatile SingularAttribute<Guitar, Integer> year;
	public static volatile SingularAttribute<Guitar, String> model;
	public static volatile SingularAttribute<Guitar, String> wood;
	public static volatile SingularAttribute<Guitar, Integer> id;
	public static volatile SingularAttribute<Guitar, String> pickUps;

	public static final String COUNTRY = "country";
	public static final String YEAR = "year";
	public static final String MODEL = "model";
	public static final String WOOD = "wood";
	public static final String ID = "id";
	public static final String PICK_UPS = "pickUps";

}

