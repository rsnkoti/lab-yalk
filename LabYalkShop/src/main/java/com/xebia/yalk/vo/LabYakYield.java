package com.xebia.yalk.vo;

import java.io.Serializable;

public class LabYakYield implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double milkYield;
    int skinYield;
    public LabYakYield(double milkYield, int skinYield){
        this.milkYield = milkYield;
        this.skinYield = skinYield;
    }
    
	public double getMilkYield() {
		return milkYield;
	}
	
	public int getSkinYield() {
		return skinYield;
	}

}
