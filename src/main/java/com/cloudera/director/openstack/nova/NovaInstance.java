package com.cloudera.director.openstack.nova;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Maps;
import org.jclouds.openstack.nova.v2_0.domain.Server;

import com.cloudera.director.spi.v1.compute.util.AbstractComputeInstance;
import com.cloudera.director.spi.v1.util.DisplayPropertiesUtil;
import com.cloudera.director.spi.v1.model.DisplayProperty;
import com.cloudera.director.spi.v1.model.DisplayPropertyToken;
import com.cloudera.director.spi.v1.model.util.SimpleDisplayPropertyBuilder;


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
	 */
	public static List<DisplayProperty> getDisplayProperties() {return DISPLAY_PROPERTIES;}
	
	/**
	 * Nova compute instance display properties.
	 */
	public static enum NovaInstanceDisplayPropertyToken implements DisplayPropertyToken {
		
		/**
		 * The ID of the image used to launch the instance.
		 */
		IMAGE_ID(new SimpleDisplayPropertyBuilder()
				.displayKey("imageID")
				.defaultDescription("The ID of the image used to launch the instance.")
				.sensitive(false)
				.build()) {
			@Override
			protected String getPropertyValue(Server instance) {
				return instance.getImage().getId();
			}	
		},
		
		/**
		 * The ID of the instance.
		 */
		INSTANCE_ID(new SimpleDisplayPropertyBuilder()
				.displayKey("instanceID")
				.defaultDescription("The ID of the instance")
				.sensitive(false)
				.build()) {
			@Override
			protected String getPropertyValue(Server instance) {
				return instance.getId();
			}
		},
		
		/**
		 * The instance type.
		 */
		INSTANCE_TYPE(new SimpleDisplayPropertyBuilder()
				.displayKey("instanceType")
				.defaultDescription("The instance type")
				.sensitive(false)
				.build()) {
			@Override
			protected String getPropertyValue(Server instance) {
				return instance.getFlavor().getName();
			}
		},
		
		/**
		 * The name of the key pair, if this instance was launched with an associated key pair.
		 */
		KEY_PAIR(new SimpleDisplayPropertyBuilder()
				.displayKey("keyName")
				.defaultDescription("The name of the key pair, if this instance was launched with an associated key pair.")
				.sensitive(false)
				.build()) {
			@Override
			protected String getPropertyValue(Server instance) {
				return instance.getKeyName();
			}
		},
		
		LAUNCH_TIME(new SimpleDisplayPropertyBuilder()
				.displayKey("launchTime")
				.defaultDescription("The time the instance was launched.")
				.sensitive(false)
				.build()) {
			@Override 
			protected String getPropertyValue(Server instance) {
				return instance.getCreated().toString();
			}
		},
		
		PRIVATE_IP_ADDRESS(new SimpleDisplayPropertyBuilder()
				.displayKey("privateIpAddress")
				.defaultDescription("The private IP address assigned to the instance.")
				.sensitive(false)
				.build()) {
			@Override
			protected String getPropertyValue(Server instance) {
				return instance.getAccessIPv4();
			}
		},
		
		PUBLIC_IP_ADDRESS(new SimpleDisplayPropertyBuilder()
				.displayKey("publicIpAddress")
				.defaultDescription("The public IP address assigned to the instance.")
				.sensitive(false)
				.build()) {
			@Override
			protected String getPropertyValue(Server instance) {
				return instance.getAccessIPv4();
			}
		},
		
		NETWORK_ID(new SimpleDisplayPropertyBuilder()
				.displayKey("networkId")
				.defaultDescription("The ID of the network in which the instance is running.")
				.sensitive(false)
				.build()) {
			@Override
			protected String getPropertyValue(Server instance) {
				return instance.getAccessIPv4();
			}
		}
		
		
 		;
		
		/**
		 * The display property.
		 */
		private final DisplayProperty displayProperty;
		
		/**
		 * Create an Nova instance display property token with the specified parameters.
		 * @param displayProperty the display property
		 */
		private NovaInstanceDisplayPropertyToken(DisplayProperty displayProperty) {
			this.displayProperty = displayProperty;
		}
		
		/**
		 * Returns the value of the property from the specified instance.
		 * 
		 * @param instance the instance
		 * @return the value of the property from the specified instance
		 */
		protected abstract String getPropertyValue(Server instance);
		
		@Override
		public DisplayProperty unwrap() {
			return displayProperty;
		}
		
	}
	
	public static final Type TYPE = new ResourceType("NovaInstance");	
	
	protected NovaInstance(NovaInstanceTemplate template, String instanceId,
			InetAddress privateIpAddress) {
		super(template, instanceId, privateIpAddress);
		
	}

	public Map<String, String> getProperties() {
		Map<String, String> properties = Maps.newHashMap();
		Server instance = unwrap();
		if (instance != null) {
			for (NovaInstanceDisplayPropertyToken propertyToken : NovaInstanceDisplayPropertyToken.values()) {
				properties.put(propertyToken.unwrap().getDisplayKey(), propertyToken.getPropertyValue(instance));
			}
		}
		return properties;
	}

}
