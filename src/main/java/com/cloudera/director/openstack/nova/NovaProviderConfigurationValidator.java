package com.cloudera.director.openstack.nova;

import org.jclouds.ContextBuilder;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.NovaApiMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.cloudera.director.openstack.nova.NovaProviderConfigurationProperty.REGION;
import static com.cloudera.director.spi.v1.model.util.Validations.addError;

import com.cloudera.director.openstack.OpenStackCredentials;
import com.cloudera.director.spi.v1.model.ConfigurationValidator;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.model.exception.PluginExceptionConditionAccumulator;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

/**
 * Validate OpenStack Nova provider configuration
 */
public class NovaProviderConfigurationValidator implements ConfigurationValidator{
	
	private static final Logger LOG = LoggerFactory.getLogger(NovaProviderConfigurationValidator.class);
	
	private static final String REGION_NOT_FOUND_MSG = "Region '%s' not found.";
	
	private OpenStackCredentials credentials;
	
	
	public NovaProviderConfigurationValidator(OpenStackCredentials credentials) {
		this.credentials = credentials;
	}
    @Override 
	public void validate(String name, Configured configuration,
			PluginExceptionConditionAccumulator accumulator, LocalizationContext localizationContext) {
		checkRegion(configuration, accumulator, localizationContext);
		
	}
	
    /**
     * validates the configured region
     * @param configuration the configuration to be validated.
     * @param accumulator the exception condition accumulator
     * @param localizationContext the localization context
     */
	void checkRegion(Configured configuration,
			PluginExceptionConditionAccumulator accumulator,
			LocalizationContext localizationContext) {
		String regionName = configuration.getConfigurationValue(REGION, localizationContext);
		LOG.info(">> Querying Region '{}'", regionName);
		Iterable<Module> modules = ImmutableSet.<Module>of(new SLF4JLoggingModule());
		String endpoint = credentials.getEndpoint();
		String identity = credentials.getIdentity();
		String credential = credentials.getCredential();
		
		NovaApi novapi = ContextBuilder.newBuilder(new NovaApiMetadata())
				  .endpoint(endpoint)
	              .credentials(identity, credential)
	              .modules(modules)
	              .buildApi(NovaApi.class);
		if (!novapi.getConfiguredRegions().contains(regionName)) {
			addError(accumulator, REGION, localizationContext, null, REGION_NOT_FOUND_MSG, regionName);
		}	
		
	}

}
