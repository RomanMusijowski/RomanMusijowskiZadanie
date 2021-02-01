package com.zadanie.RomanMusijowskiZadanie.storage;

import org.springframework.context.annotation.Configuration;

@Configuration("storage")
public class StorageProperties {

	private String location = "upload-dir";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
