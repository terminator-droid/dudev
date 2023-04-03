package com.dudev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.dudev.entity.BaseEntity_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> phoneNumber;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile ListAttribute<User, UserLikedProduct> userLikedProducts;
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile ListAttribute<User, Product> userProducts;
	public static volatile SingularAttribute<User, String> username;

	public static final String PASSWORD = "password";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ADDRESS = "address";
	public static final String ROLE = "role";
	public static final String USER_LIKED_PRODUCTS = "userLikedProducts";
	public static final String FULL_NAME = "fullName";
	public static final String USER_PRODUCTS = "userProducts";
	public static final String USERNAME = "username";

}

