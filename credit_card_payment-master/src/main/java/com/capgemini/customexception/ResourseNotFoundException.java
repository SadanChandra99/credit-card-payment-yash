package com.capgemini.customexception;

public class ResourseNotFoundException extends RuntimeException {
	 
	String resourceName;
	String fieldName;
	long fieldId;
	
	
	
	
	public String getResourceName() {
		return resourceName;
	}




	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}




	public String getFieldName() {
		return fieldName;
	}




	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}




	public long getFieldId() {
		return fieldId;
	}




	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}




	public ResourseNotFoundException(String resourceName, String fieldName, long fieldId) {
		super(String.format("%s not found with %s : %s",  resourceName, fieldName, fieldId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
	
	
}
