package com.dudev.entity;

import com.dudev.entity.Audit.Operation;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Audit.class)
public abstract class Audit_ {

	public static volatile SingularAttribute<Audit, String> entityContent;
	public static volatile SingularAttribute<Audit, String> entityName;
	public static volatile SingularAttribute<Audit, Long> id;
	public static volatile SingularAttribute<Audit, Operation> operation;

	public static final String ENTITY_CONTENT = "entityContent";
	public static final String ENTITY_NAME = "entityName";
	public static final String ID = "id";
	public static final String OPERATION = "operation";

}

