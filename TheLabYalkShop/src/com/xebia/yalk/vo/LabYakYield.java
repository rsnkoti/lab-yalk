package com.xebia.yalk.vo;

public class LabYakYield {
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
