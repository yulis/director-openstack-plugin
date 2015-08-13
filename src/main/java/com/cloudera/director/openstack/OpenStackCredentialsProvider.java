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
import static com.cloudera.director.openstack.OpenStackCredentialsProviderConfigurationProperty.TENANT_NAME;
import static com.cloudera.director.openstack.OpenStackCredentialsProviderConfigurationProperty.USER_NAME;
import static com.cloudera.director.openstack.OpenStackCredentialsProviderConfigurationProperty.PASSWORD;

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
			configuration.getConfigurationValue(TENANT_NAME, localizationContext),
			configuration.getConfigurationValue(USER_NAME, localizationContext),
			configuration.getConfigurationValue(PASSWORD, localizationContext));
	}

}
