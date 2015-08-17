package com.cloudera.director.openstack.nova;

import java.util.List;
import java.util.Map;

import com.cloudera.director.spi.v1.compute.ComputeInstanceTemplate;
import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.util.ConfigurationPropertiesUtil;

/**
 * Represents a template for constructing Nova compute instance.
 */
public class NovaInstanceTemplate extends ComputeInstanceTemplate{
	
	/**
	 * The list of configuration properties (including inherited properties).
	 */

	private static final List<ConfigurationProperty> CONFIGURATION_PROPERTIES = 
			ConfigurationPropertiesUtil.merge(
					ComputeInstanceTemplate.getConfigurationProperties(),
					ConfigurationPropertiesUtil.asConfigurationPropertyList(
							NovaInstanceTemplateConfigurationProperty.values())
					);
	
	/**
	 * Returns the list of configuration properties for creating an Nova instance template,
	 * including inherited properties.
	 * 
	 * @return the list of configuration properties for creating an Nova instance template,
	 * including inherited properties.
	 */
    public static List<ConfigurationProperty> getConfigurationProperties() {
    	return CONFIGURATION_PROPERTIES;
    }
	public NovaInstanceTemplate(String name, Configured configuration,
			Map<String, String> tags,
			LocalizationContext providerLocalizationContext) {
		super(name, configuration, tags, providerLocalizationContext);
		
	}

}
