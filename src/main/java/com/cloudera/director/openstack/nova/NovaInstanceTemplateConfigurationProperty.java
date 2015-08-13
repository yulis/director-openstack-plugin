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
     IMGER(new SimpleConfigurationPropertyBuilder()
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
     SECURITY_GROUP_IDS(new SimpleConfigurationPropertyBuilder()
    		 .configKey("securityGroupIds")
    		 .name("Security group IDs")
    		 .widget(ConfigurationProperty.Widget.OPENLIST)
    		 .required(true)
    		 .defaultDescription("Specify the list of security group IDs.")
    		 .defaultErrorMessage("Security group IDs are mandatory")
    		 .build()),
     
     /**
      * The ID of the subnet.
      */
     SUNET_ID(new SimpleConfigurationPropertyBuilder()
    		 .configKey("subnetId")
    		 .name("Subnet ID")
    		 .required(true)
    		 .defaultDescription("The subnet ID")
    		 .defaultErrorMessage("Subnet ID is mandatory")
    		 .build()),
     
     /**
      * The instance type (e.g. m1.medium, m1.large, etc
      */
     TYPE(new SimpleConfigurationPropertyBuilder()
    		 .configKey(ComputeInstanceTemplateConfigurationPropertyToken.TYPE.unwrap().getConfigKey())
    		 .name("Instance flavor")
    		 .required(true)
    		 .widget(ConfigurationProperty.Widget.OPENLIST)
    		 .defaultDescription("Size of image to launch")
    		 .defaultErrorMessage("Instance flavor is mandatory")
    		 .addValidValues(
    				 "m1.tiny","m1.small","m1.medium","m1.large","m1.xlarge")
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
    		 .hidden(true)
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
