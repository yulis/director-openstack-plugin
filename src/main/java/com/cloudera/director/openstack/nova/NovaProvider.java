package com.cloudera.director.openstack.nova;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudera.director.openstack.OpenStackCredentials;
import com.cloudera.director.spi.v1.compute.util.AbstractComputeProvider;
import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.InstanceState;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.model.Resource.Type;
import com.cloudera.director.spi.v1.provider.ResourceProviderMetadata;
import com.cloudera.director.spi.v1.provider.util.SimpleResourceProviderMetadata;
import com.cloudera.director.spi.v1.util.ConfigurationPropertiesUtil;
import com.typesafe.config.Config;

public class NovaProvider extends AbstractComputeProvider<NovaInstance, NovaInstanceTemplate> {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(NovaProvider.class);
	
	private static final String novaProvider = "openstack-nova";
	
	/**
	 * The provider configuration properties.
	 */	
	protected static final List<ConfigurationProperty> CONFIGURATION_PROPERTIES =
			ConfigurationPropertiesUtil.asConfigurationPropertyList(
					NovaProviderConfigurationProperty.values());
	
	/**
	 * The resource provider ID.
	 */
	public static final String ID = NovaProvider.class.getCanonicalName();
	
	/**
	 * The resource provider metadata.
	 */
	public static final ResourceProviderMetadata METADATA = SimpleResourceProviderMetadata.builder()
		.id(ID)
		.name("Nova")
	    .description("OpenStack Nova compute provider")
	    .providerClass(NovaProvider.class)
	    .providerConfigurationProperties(CONFIGURATION_PROPERTIES)
	    .resourceTemplateConfigurationProperties(NovaInstanceTemplate.getConfigurationProperties())
	    .resourceDisplayProperties(NovaInstance.getDisplayProperties())
	    .build();
	
	 private OpenStackCredentials credentials;
	 private Config openstackConfig;
	 
	 protected NovaApi novaApi;
	
	public NovaProvider(Configured configuration, OpenStackCredentials credentials,
			Config openstackConfig, LocalizationContext localizationContext) {
		super(configuration, METADATA, localizationContext);
		this.credentials = credentials;
		this.openstackConfig = openstackConfig;
		this.novaApi = buildNovaAPI();
	}
	
	
	private NovaApi buildNovaAPI(){
		
		String endpoint = credentials.getEndpoint();
		String identity = credentials.getIdentity();
		String credential = credentials.getCredential();
		
		return ContextBuilder.newBuilder(novaProvider)
			  .endpoint(endpoint)
              .credentials(identity, credential)
              .buildApi(NovaApi.class);
	}
	

	public Map<String, InstanceState> getInstanceState(
			NovaInstanceTemplate arg0, Collection<String> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public void allocate(NovaInstanceTemplate arg0, Collection<String> arg1,
			int arg2) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	public NovaInstanceTemplate createResourceTemplate(String arg0,
			Configured arg1, Map<String, String> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(NovaInstanceTemplate arg0, Collection<String> arg1)
			throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	public Collection<NovaInstance> find(NovaInstanceTemplate arg0,
			Collection<String> arg1) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	public Type getResourceType() {
		return NovaInstance.TYPE;
	}
}
