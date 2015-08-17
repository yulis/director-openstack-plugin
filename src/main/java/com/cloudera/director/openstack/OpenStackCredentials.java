package com.cloudera.director.openstack;

public class OpenStackCredentials {

	private final String keystone_endpoint;
	private final String identity;
	private final String credential;

	public OpenStackCredentials(String endpoint, String tenant, String user, String credential){
		this.keystone_endpoint = endpoint;
		this.identity = tenant + ":" + user;
		this.credential = credential;
	}
	
	public String getEndpoint(){
		return keystone_endpoint;
	}
	
	public String getIdentity(){
		return identity;
	}
	
	public String getCredential(){
		return credential;
	}
	
	public boolean equals(OpenStackCredentials cre){
		return keystone_endpoint.equals(cre.getEndpoint()) && 
			   identity.equals(cre.getIdentity()) &&
			   credential.equals(cre.getCredential());
	}
}
