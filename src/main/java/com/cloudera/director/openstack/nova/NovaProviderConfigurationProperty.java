package com.cloudera.director.openstack.nova;

import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.ConfigurationPropertyToken;
import com.cloudera.director.spi.v1.model.util.SimpleConfigurationPropertyBuilder;

/**
 * OpenStack Nova configuration properties.
 */
public enum NovaProviderConfigurationProperty implements ConfigurationPropertyToken{
	 REGION(new SimpleConfigurationPropertyBuilder()
     .configKey("region")
     .name("Region")
     .defaultValue("regionOne")
     .defaultDescription("Region to target for deployment.")
     .build());

	/**
	 * The configuration property.
	 */
	private final ConfigurationProperty configurationProperty;

	/**
	 * Creates a configuration property token with the specified parameters.
	 *
	 * @param configurationProperty the configuration property
	 */
	private NovaProviderConfigurationProperty(ConfigurationProperty configurationProperty) {
	    this.configurationProperty = configurationProperty;
	}
	
	@Override
	public ConfigurationProperty unwrap() {
		return configurationProperty;
	}


}
