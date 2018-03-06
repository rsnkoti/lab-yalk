package com.xebia.yalk.vo;

import java.io.Serializable;

public class LabYakDaysYield implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	String name;
	double age;
    double ageLastShaved;
    
    
	public LabYakDaysYield(String name, double age, double ageLastShaved) {
		super();
		this.name = name;
		this.age = age;
		this.ageLastShaved = ageLastShaved;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getAge() {
		return age;
	}


	public void setAge(double age) {
		this.age = age;
	}


	public double getAgeLastShaved() {
		return ageLastShaved;
	}


	public void setAgeLastShaved(double ageLastShaved) {
		this.ageLastShaved = ageLastShaved;
	}

	
 
    

}
