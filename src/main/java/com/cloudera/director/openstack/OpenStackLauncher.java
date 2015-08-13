package com.cloudera.director.openstack;

import java.io.File;
import java.util.Collections;
import java.util.Locale;

import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;
import com.cloudera.director.spi.v1.provider.CloudProvider;
import com.cloudera.director.spi.v1.provider.util.AbstractLauncher;
import com.cloudera.director.spi.v1.common.http.HttpProxyParameters;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigSyntax;

public class OpenStackLauncher extends AbstractLauncher{
	
	private Config openstackConfig = null;

	public OpenStackLauncher() {
		super(Collections.singletonList(OpenStackProvider.METADATA), null);
	}
	
	/**
	 * The config is loaded from a "openstack.conf" file on the classpath. 
	 */	
	@Override
	public void initialize(File configurationDirectory, HttpProxyParameters httpProxyParameters) {
		File configFile = new File(configurationDirectory, Configurations.CONFIGURATION_FILE_NAME);

	    if (configFile.canRead()) {	    	
	    	try{
	    		openstackConfig = parseConfigFile(configFile);
	    	}catch(Exception e){
	    		throw new RuntimeException(e);
	    	}
	    }
	}
	
	/**
	 * Parses the specified configuration file.
	 *
	 * @param configFile the configuration file
	 * @return the parsed configuration
	 */
	private static Config parseConfigFile(File configFile) {
		ConfigParseOptions options = ConfigParseOptions.defaults()
				.setSyntax(ConfigSyntax.CONF)
	            .setAllowMissing(false);

		return ConfigFactory.parseFileAnySyntax(configFile, options);
	}

	public CloudProvider createCloudProvider(String cloudProviderId,
			Configured configuration, Locale locale) {
		
		if (!OpenStackProvider.ID.equals(cloudProviderId)) {
			throw new IllegalArgumentException("Cloud provider not found: " + cloudProviderId);
		}
		
		LocalizationContext localizationContext = getLocalizationContext(locale);

	    // At this point the configuration object will already contain
	    // the required data for authentication.	
		
		return  new OpenStackProvider(configuration, openstackConfig, localizationContext);
	}

}
