package com.cloudera.director.openstack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

import com.cloudera.director.openstack.nova.NovaProvider;
import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.ConfigurationValidator;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.provider.CloudProviderMetadata;
import com.cloudera.director.spi.v1.provider.CredentialsProvider;
import com.cloudera.director.spi.v1.provider.ResourceProvider;
import com.cloudera.director.spi.v1.provider.ResourceProviderMetadata;
import com.cloudera.director.spi.v1.provider.util.AbstractCloudProvider;
import com.cloudera.director.spi.v1.provider.util.SimpleCloudProviderMetadataBuilder;
import com.typesafe.config.Config;

public class OpenStackProvider extends AbstractCloudProvider {
	
	/**
	 * The cloud provider ID.
	 */
	public static final String ID = "openstack";
	
	/**
	 * The resource provider metadata.
	 */
	private static final List<ResourceProviderMetadata> RESOURCE_PROVIDER_METADATA =
	      Collections.unmodifiableList(Arrays.asList(NovaProvider.METADATA));
	
	
	private OpenStackCredentials credentials;
	private Config openstackConfig;
	
	
	protected OpenStackCredentials getOpenStackCredentials(Configured configuration,
			LocalizationContext localizationContext){
		
		CredentialsProvider<OpenStackCredentials> provider = new OpenStackCredentialsProvider();
		OpenStackCredentials credential = provider.createCredentials(configuration, localizationContext);
		checkNotNull(credential, "OpenStackCredentials is null!");
		return credential;
	}
	
	/**
	 * The cloud provider metadata.
	 */
	protected static final CloudProviderMetadata METADATA = new SimpleCloudProviderMetadataBuilder()
			.id(ID)
			.name("OpenStack")
			.description("OpenStack cloud provider implementation")
			.configurationProperties(Collections.<ConfigurationProperty>emptyList())
			.credentialsProviderMetadata(OpenStackCredentialsProvider.METADATA)
			.resourceProviderMetadata(RESOURCE_PROVIDER_METADATA)
			.build();

	public OpenStackProvider(Configured configuration, Config openstackConfig,
			LocalizationContext rootLocalizationContext) {
		super(METADATA, rootLocalizationContext);
		this.openstackConfig = openstackConfig;
		this.credentials = getOpenStackCredentials(configuration, rootLocalizationContext);
	}

	@Override
	protected ConfigurationValidator getResourceProviderConfigurationValidator(
	      ResourceProviderMetadata resourceProviderMetadata) {
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResourceProvider createResourceProvider(String resourceProviderId,
			Configured configuration) {
		ResourceProviderMetadata resourceProviderMetadata =
				 getProviderMetadata().getResourceProviderMetadata(resourceProviderId);
		if (resourceProviderMetadata.getId().equals(NovaProvider.METADATA.getId())){
			return new NovaProvider(configuration, this.credentials, this.openstackConfig,
			   getLocalizationContext());
		}
		
		//TODO: add trove provider later
		return null;
	}

}
