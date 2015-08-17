package com.cloudera.director.openstack.nova;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Server.Status;
import org.jclouds.openstack.nova.v2_0.domain.ServerCreated;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;
import org.jclouds.openstack.nova.v2_0.options.CreateServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.cloudera.director.openstack.nova.NovaProviderConfigurationProperty.REGION;
import static com.cloudera.director.openstack.nova.NovaInstanceTemplateConfigurationProperty.IMAGE;
import static com.cloudera.director.openstack.nova.NovaInstanceTemplateConfigurationProperty.NETWORK_ID;
import static com.cloudera.director.openstack.nova.NovaInstanceTemplateConfigurationProperty.TYPE;
import static com.cloudera.director.openstack.nova.NovaInstanceTemplateConfigurationProperty.SECURITY_GROUP_IDS;
import static com.cloudera.director.openstack.nova.NovaInstanceTemplateConfigurationProperty.AVAILABILITY_ZONE;

import com.cloudera.director.openstack.OpenStackCredentials;
import com.cloudera.director.spi.v1.compute.util.AbstractComputeProvider;
import com.cloudera.director.spi.v1.model.ConfigurationProperty;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.InstanceState;
import com.cloudera.director.spi.v1.model.InstanceTemplate;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.model.Resource.Type;
import com.cloudera.director.spi.v1.model.util.SimpleResourceTemplate;
import com.cloudera.director.spi.v1.provider.ResourceProviderMetadata;
import com.cloudera.director.spi.v1.provider.util.SimpleResourceProviderMetadata;
import com.cloudera.director.spi.v1.util.ConfigurationPropertiesUtil;
import com.google.common.collect.Maps;
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
	
	/*
	 * The credentials of the OpenStack environment
	 */
	private OpenStackCredentials credentials;
	
	/*
	 * The configuration of the OpenStack environment
	 */
	@SuppressWarnings("unused")
	private Config openstackConfig;
	
	/*
	 * The nova api for OpenStack Nova service
	 */
	private final NovaApi novaApi;
	
	/*
	 * Region of the provider
	 */
	private String region;
	
	
	public NovaProvider(Configured configuration, OpenStackCredentials credentials,
			Config openstackConfig, LocalizationContext localizationContext) {
		super(configuration, METADATA, localizationContext);
		this.credentials = credentials;
		this.openstackConfig = openstackConfig;
		this.novaApi = buildNovaAPI();
		this.region = configuration.getConfigurationValue(REGION, localizationContext);
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
	
	public NovaInstanceTemplate createResourceTemplate(String name,
			Configured configuration, Map<String, String> tags) {
		return new NovaInstanceTemplate(name, configuration, tags, this.getLocalizationContext());
	}

	public Map<String, InstanceState> getInstanceState(NovaInstanceTemplate template, 
			Collection<String> instanceIds) {
		
		Map<String, InstanceState> instanceStateByInstanceId =
		        Maps.newHashMapWithExpectedSize(instanceIds.size());
		
		//TODO: add the try catch   
		for (String currentId : instanceIds) {
			Status instance_state =  novaApi.getServerApi(region).get(currentId).getStatus();
			InstanceState instanceState = NovaInstanceState.fromInstanceStateName(instance_state);
			instanceStateByInstanceId.put(currentId, instanceState);
		}
		
		return instanceStateByInstanceId;
	}

	public void allocate(NovaInstanceTemplate template, Collection<String> instanceIds,
			int minCount) throws InterruptedException {

	    LocalizationContext providerLocalizationContext = getLocalizationContext();
	    LocalizationContext templateLocalizationContext =
	        SimpleResourceTemplate.getTemplateLocalizationContext(providerLocalizationContext);
		
		
		// Provisioning the cluster
		ServerApi  serverApi = novaApi.getServerApi(region); 
		
		for (String currentId : instanceIds){
			String decorateInstanceName = decorateInstanceName(template, currentId, templateLocalizationContext);
			String image = template.getConfigurationValue(IMAGE, templateLocalizationContext);
			String flavor = template.getConfigurationValue(TYPE, templateLocalizationContext);
			String network = template.getConfigurationValue(NETWORK_ID, templateLocalizationContext);
			String azone = template.getConfigurationValue(AVAILABILITY_ZONE, templateLocalizationContext);
			String security_group = template.getConfigurationValue(SECURITY_GROUP_IDS, templateLocalizationContext);
			
			//TODO: consider the block device mapping
			//....
			
			
			//Add Tags to the server for better search later
			//Map<String, String> tags = new HashMap<String, String>();
			//tags.put("DIRECTOR_ID", currentId);
			//tags.put("", value);
			
			
			
			CreateServerOptions createServerOps = new CreateServerOptions()
								.networks(network)
								.availabilityZone(azone)
								.securityGroupNames(security_group);
			
			
			
			ServerCreated currentServer = serverApi.create(decorateInstanceName, image, flavor, createServerOps);
			
			
				
		}
		
		
		
		// Tag all the new instances so that we can easily find them later on
	}

	public void delete(NovaInstanceTemplate template, Collection<String> virtualInstanceIds)
			throws InterruptedException {
		// TODO 
		
	}

	public Collection<NovaInstance> find(NovaInstanceTemplate template,
			Collection<String> virtualInstanceIds) throws InterruptedException {
		// TODO 
		return null;
	}

	public Type getResourceType() {
		return NovaInstance.TYPE;
	}
	
	private static String decorateInstanceName(NovaInstanceTemplate template, String currentId,
		      LocalizationContext templateLocalizationContext){
		
		return template.getConfigurationValue(
		        InstanceTemplate.InstanceTemplateConfigurationPropertyToken.INSTANCE_NAME_PREFIX,
		        templateLocalizationContext) + "-" + currentId;
	}
}
