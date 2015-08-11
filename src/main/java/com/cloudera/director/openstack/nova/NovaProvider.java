package com.cloudera.director.openstack.nova;

import java.util.Collection;
import java.util.Map;

import com.cloudera.director.spi.v1.compute.util.AbstractComputeProvider;
import com.cloudera.director.spi.v1.model.Configured;
import com.cloudera.director.spi.v1.model.InstanceState;
import com.cloudera.director.spi.v1.model.Resource.Type;

public class NovaProvider 
	extends AbstractComputeProvider<NovaInstance, NovaInstanceTemplate> {

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
		// TODO Auto-generated method stub
		return null;
	}
}
