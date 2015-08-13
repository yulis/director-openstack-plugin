package com.cloudera.director.openstack;

import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.ConfigurationPropertyToken;
import com.cloudera.director.spi.v1.model.util.SimpleConfigurationPropertyBuilder;


/**
 * An enum of properties required for building credentials.
 */
public enum OpenStackCredentialsProviderConfigurationProperty implements ConfigurationPropertyToken{
	
	KEYSTONE_ENDPOINT(new SimpleConfigurationPropertyBuilder()
	      .configKey("keystoneEndpoint")
	      .name("Keystone Endpoint")
	      .defaultDescription("Endpoint of openstack keystone.")
	      .defaultErrorMessage("OpenStack credentials configuration is missing the keystone endpoint.")
	      .required(true)
	      .build()),
	IDENTITY(new SimpleConfigurationPropertyBuilder()
	      .configKey("identity")
	      .name("OpenStack Identity")
	      .defaultDescription("Username of openstack.")
	      .defaultErrorMessage("OpenStack credentials configuration is missing the identity.")
	      .required(true)
	      .build()),
	CREDENTIAL(new SimpleConfigurationPropertyBuilder()
	      .configKey("credential")
	      .name("OpenStack Credential")
	      .defaultDescription("Password of openstack.")
	      .defaultErrorMessage("OpenStack credentials configuration is missing the credential.")
	      .required(true)
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
	private OpenStackCredentialsProviderConfigurationProperty(
			ConfigurationProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}

	@Override
	public ConfigurationProperty unwrap() {
		return configurationProperty;
	}

}
