package com.cloudera.director.openstack.nova;

import com.cloudera.director.spi.v1.compute.ComputeInstanceTemplate.ComputeInstanceTemplateConfigurationPropertyToken;
import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.ConfigurationPropertyToken;
import com.cloudera.director.spi.v1.model.util.SimpleConfigurationPropertyBuilder;

public enum NovaInstanceTemplateConfigurationProperty implements ConfigurationPropertyToken{
	 
	 /**
	  * The availability zone.
	  */
     AVAILABILITY_ZONE(new SimpleConfigurationPropertyBuilder()
    		 .configKey("availabilityZone")
    		 .name("Availability zone")
    		 .widget(ConfigurationProperty.Widget.OPENLIST)
    		 .defaultDescription("The availability zone")
    		 .hidden(true)
    		 .build()),	
     
     /**
      * The image ID.
      */
     IMAGE(new SimpleConfigurationPropertyBuilder()
    		 .configKey(ComputeInstanceTemplateConfigurationPropertyToken.IMAGE.unwrap().getConfigKey())
    		 .name("Image ID")
    		 .required(true)
    		 .widget(ConfigurationProperty.Widget.OPENLIST)
    		 .defaultDescription("The image id")
    		 .defaultErrorMessage("Image ID is mandatory")
    		 .build()),
     
     /**
      * The IDs of the security groups (comma separated).
      */
     SECURITY_GROUP_NAMES(new SimpleConfigurationPropertyBuilder()
    		 .configKey("securityGroupNames")
    		 .name("Security group names")
    		 .widget(ConfigurationProperty.Widget.OPENLIST)
    		 .required(true)
    		 .defaultDescription("Specify the list of security group names.")
    		 .defaultErrorMessage("Security group names are mandatory")
    		 .build()),
     
     /**
      * The ID of the network.
      */
     NETWORK_ID(new SimpleConfigurationPropertyBuilder()
    		 .configKey("networkId")
    		 .name("Network ID")
    		 .required(true)
    		 .defaultDescription("The network ID")
    		 .defaultErrorMessage("Network ID is mandatory")
    		 .build()),
     
     /**
      * The instance type (e.g. m1.medium, m1.large, etc, input must be the ID.
      */
     TYPE(new SimpleConfigurationPropertyBuilder()
    		 .configKey(ComputeInstanceTemplateConfigurationPropertyToken.TYPE.unwrap().getConfigKey())
    		 .name("Instance flavor ID")
    		 .required(true)
    		 .defaultDescription("Size of image to launch")
    		 .defaultErrorMessage("Instance flavor ID is mandatory")
    		 .build()),
     
     /**
      * Name of the key pair to use for new instances.
      */
     KEY_NAME(new SimpleConfigurationPropertyBuilder()
    		 .configKey("keyName")
    		 .name("Key name")
    		 .required(false)
    		 .widget(ConfigurationProperty.Widget.TEXT)
    		 .defaultDescription("The Nova key pair")
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
	private NovaInstanceTemplateConfigurationProperty(ConfigurationProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
	
	@Override
	public ConfigurationProperty unwrap() {
		return configurationProperty;
	}
	
}
