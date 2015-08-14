package com.cloudera.director.openstack.nova;


import java.util.Collections;
import java.util.Map;

import org.jclouds.openstack.nova.v2_0.domain.Server.Status;

import com.cloudera.director.spi.v1.model.InstanceStatus;
import com.cloudera.director.spi.v1.model.util.AbstractInstanceState;
import com.google.common.collect.Maps;


/**
 * Nova instance state implementation
 */
public class NovaInstanceState extends AbstractInstanceState<Status> {
	
	/**
	 * The map from Nova instance state to Director instance state.
	 */
	private static final Map<Status, NovaInstanceState> INSTANCE_STATE_MAP;
	
	static {
		Map<Status, NovaInstanceState> map = Maps.newEnumMap(Status.class);
		addInstanceState(map,Status.BUILD, InstanceStatus.PENDING);
		addInstanceState(map,Status.ACTIVE, InstanceStatus.RUNNING);
		addInstanceState(map,Status.SHUTOFF, InstanceStatus.STOPPED);
		addInstanceState(map,Status.DELETED, InstanceStatus.DELETED);
		addInstanceState(map,Status.ERROR, InstanceStatus.FAILED);
		addInstanceState(map,Status.UNKNOWN, InstanceStatus.UNKNOWN);
		INSTANCE_STATE_MAP = Collections.unmodifiableMap(map);
		
	}
	
	/**
	 * Returns the Director instance state for the specified Nova instance name.
	 * @param instanceStateName the Nova instance state name
	 * @return the corresponding Director instance state.
	 */
	public static NovaInstanceState fromInstanceStateName(Status instanceStateName) {
		return (INSTANCE_STATE_MAP.get(instanceStateName) == null) ? 
				INSTANCE_STATE_MAP.get(Status.UNKNOWN) : INSTANCE_STATE_MAP.get(instanceStateName);
		
	}
    
	/**
	 * Add an entry in the specified map associating the specified Nova instance state name with a Director instance state.
	 * with the corresponding instance status.
	 *  
	 * @param map                 the map from Nova instance state name to Director instance states
	 * @param instanceStateName   the Nova instance state name
	 * @param instanceStatus      the corresponding instance status
	 */
	private static void addInstanceState(Map<Status, NovaInstanceState> map,
			Status instanceStateName, InstanceStatus instanceStatus) {
		map.put(instanceStateName, new NovaInstanceState(instanceStatus, instanceStateName));
	}
	/**
	 * Create a Nova instance state with the specified parameters.
	 * @param instanceStatus the instance status
	 * @param instanceStateDetails the provider-specific instance details
	 */
	public NovaInstanceState(InstanceStatus instanceStatus, Status instanceStateDetails) {
		super(instanceStatus, instanceStateDetails);
		
	}

}
