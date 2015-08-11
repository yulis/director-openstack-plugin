package com.cloudera.director.openstack;

import com.cloudera.director.spi.v1.model.ConfigurationValidator;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.provider.CloudProviderMetadata;
import com.cloudera.director.spi.v1.provider.ResourceProvider;
import com.cloudera.director.spi.v1.provider.ResourceProviderMetadata;
import com.cloudera.director.spi.v1.provider.util.AbstractCloudProvider;
import com.cloudera.director.spi.v1.provider.util.SimpleCloudProviderMetadataBuilder;

public class OpenStackProvider extends AbstractCloudProvider {
	
	/**
	 * The cloud provider ID.
	 */
	public static final String ID = "openstack";
	
	/**
	 * The cloud provider metadata.
	 */
	protected static final CloudProviderMetadata METADATA = new SimpleCloudProviderMetadataBuilder()
			.id(ID)
			.name("OpenStack")
			.description("OpenStack cloud provider implementation")
			.build();

	public OpenStackProvider(CloudProviderMetadata providerMetadata,
			LocalizationContext rootLocalizationContext) {
		super(providerMetadata, rootLocalizationContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ConfigurationValidator getResourceProviderConfigurationValidator(
	      ResourceProviderMetadata resourceProviderMetadata) {
		return null;
	}
	
	
	public ResourceProvider createResourceProvider(String resourceProviderId,
			Configured configuration) {
		return null;
	}

}
