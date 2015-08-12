package com.cloudera.director.openstack.nova;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import org.jclouds.openstack.nova.v2_0.domain.Server;

import com.cloudera.director.spi.v1.compute.util.AbstractComputeInstance;
import com.cloudera.director.spi.v1.util.DisplayPropertiesUtil;
import com.cloudera.director.spi.v1.model.DisplayProperty;
import com.cloudera.director.spi.v1.model.DisplayPropertyToken;


/**
 * Nova compute instance.
 */
public class NovaInstance 
	extends AbstractComputeInstance<NovaInstanceTemplate, Server>{
	
	/**
	 * The list of display properties (including inherited properties).
	 */
	private static final List<DisplayProperty> DISPLAY_PROPERTIES =
			DisplayPropertiesUtil.asDisplayPropertyList(NovaInstanceDisplayPropertyToken.values());
	
	/**
	 * Returns the list of display properties for an nova instance, including inherited properties.
	 * @return the list of display properties for an nova instance, including inherited properties.
	 */
	public static List<DisplayProperty> getDisplayProperties() {return DISPLAY_PROPERTIES;}
	
	/**
	 * Nova compute instance display properties.
	 */
	public static enum NovaInstanceDisplayPropertyToken implements DisplayPropertyToken {
		;
		
		/**
		 * The display property.
		 */
		private final DisplayProperty displayProperty;
		private NovaInstanceDisplayPropertyToken(DisplayProperty displayProperty) {
			this.displayProperty = displayProperty;
		}
		public DisplayProperty unwrap() {
			// TODO Auto-generated method stub
			return displayProperty;
		}
		
	}
	protected NovaInstance(NovaInstanceTemplate template, String instanceId,
			InetAddress privateIpAddress) {
		super(template, instanceId, privateIpAddress);
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

}
