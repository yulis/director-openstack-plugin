package com.cloudera.director.openstack.nova;

import java.util.Map;

import com.cloudera.director.spi.v1.compute.ComputeInstanceTemplate;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.LocalizationContext;

public class NovaInstanceTemplate extends ComputeInstanceTemplate{

	public NovaInstanceTemplate(String name, Configured configuration,
			Map<String, String> tags,
			LocalizationContext providerLocalizationContext) {
		super(name, configuration, tags, providerLocalizationContext);
		// TODO Auto-generated constructor stub
	}

}
