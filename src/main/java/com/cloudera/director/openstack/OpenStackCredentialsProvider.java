package com.cloudera.director.openstack;

import java.util.List;

import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.provider.CredentialsProvider;
import com.cloudera.director.spi.v1.provider.CredentialsProviderMetadata;
import com.cloudera.director.spi.v1.provider.util.SimpleCredentialsProviderMetadata;
import com.cloudera.director.spi.v1.util.ConfigurationPropertiesUtil;

import static com.cloudera.director.openstack.OpenStackCredentialsProviderConfigurationProperty.KEYSTONE_ENDPOINT;
import static com.cloudera.director.openstack.OpenStackCredentialsProviderConfigurationProperty.IDENTITY;
import static com.cloudera.director.openstack.OpenStackCredentialsProviderConfigurationProperty.CREDENTIAL;

public class OpenStackCredentialsProvider implements CredentialsProvider<OpenStackCredentials>{

	private static final List<ConfigurationProperty> CONFIGURATION_PROPERTIES =
			ConfigurationPropertiesUtil.asConfigurationPropertyList(
					OpenStackCredentialsProviderConfigurationProperty.values());

	public static CredentialsProviderMetadata METADATA =
			new SimpleCredentialsProviderMetadata(CONFIGURATION_PROPERTIES);
	
	@Override
	public CredentialsProviderMetadata getMetadata() {
		return METADATA;
	}

	@Override
	public OpenStackCredentials createCredentials(Configured configuration,
			LocalizationContext localizationContext) {	
		return new OpenStackCredentials(
			configuration.getConfigurationValue(KEYSTONE_ENDPOINT, localizationContext),
			configuration.getConfigurationValue(IDENTITY, localizationContext),
			configuration.getConfigurationValue(CREDENTIAL, localizationContext));
	}

}
