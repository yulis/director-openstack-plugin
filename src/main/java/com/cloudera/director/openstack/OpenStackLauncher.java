package com.cloudera.director.openstack;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext.Factory;
import com.cloudera.director.spi.v1.provider.CloudProvider;
import com.cloudera.director.spi.v1.provider.CloudProviderMetadata;
import com.cloudera.director.spi.v1.provider.util.AbstractLauncher;
import com.cloudera.director.spi.v1.common.http.HttpProxyParameters;

public class OpenStackLauncher extends AbstractLauncher{

	public OpenStackLauncher() {
		super(Collections.singletonList(OpenStackProvider.METADATA), null);
	}
	
	@Override
	public void initialize(File configurationDirectory, HttpProxyParameters httpProxyParameters) {
		
	}
	
	protected OpenStackLauncher(
			List<CloudProviderMetadata> cloudProviderMetadata,
			Factory localizationContextFactory) {
		super(cloudProviderMetadata, localizationContextFactory);
		// TODO Auto-generated constructor stub
	}

	public CloudProvider createCloudProvider(String cloudProviderId,
			Configured configuration, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

}
