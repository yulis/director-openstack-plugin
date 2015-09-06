package com.cloudera.director.openstack.nova;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudera.director.spi.v1.model.ConfigurationValidator;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.model.exception.PluginExceptionConditionAccumulator;

/**
 * Validates Nova instance template configuration.
 */
public class NovaInstanceTemplateConfigurationValidator implements ConfigurationValidator{
    private static final Logger LOG = 
    		LoggerFactory.getLogger(NovaInstanceTemplateConfigurationValidator.class);
    
    /**
     * The Nova provider
     */
    //private final NovaProvider provider;
	public void validate(String name, Configured configuration,
			PluginExceptionConditionAccumulator accumulator, LocalizationContext locallizationContext) {
	     		
	}

}
