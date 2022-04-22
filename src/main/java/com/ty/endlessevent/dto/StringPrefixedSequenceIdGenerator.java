package com.ty.endlessevent.dto;

import java.io.Serializable;
      
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class StringPrefixedSequenceIdGenerator extends SequenceStyleGenerator{

	public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT = "";
	private String valuePrefix;

	public static final String VALUE_FORMAT_PARAMETER = "numberFormat";
	public static final String VALUE_FORMAT_DEFAULT = "%d";
	private String numberFormat;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return valuePrefix + String.format(numberFormat, super.generate(session, object));
	}
	
	@Override
	public void configure(Type arg0, Properties arg1, ServiceRegistry arg2) throws MappingException {
		super.configure(LongType.INSTANCE, arg1, arg2);
		valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, arg1, VALUE_FORMAT_DEFAULT);
		numberFormat = ConfigurationHelper.getString(VALUE_FORMAT_PARAMETER, arg1, VALUE_PREFIX_DEFAULT);
	}

}
