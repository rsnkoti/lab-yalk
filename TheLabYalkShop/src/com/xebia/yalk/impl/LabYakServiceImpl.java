package com.xebia.yalk.impl;

import com.xebia.yalk.service.LabYakService;
import com.xebia.yalk.service.LabYakStoreService;
import com.xebia.yalk.vo.LabYak;
import com.xebia.yalk.vo.LabYakYield;

public class LabYakServiceImpl implements LabYakService {

	private LabYakStoreService storeService = new LabYakStoreServiceImpl();
	  
	public void produceYieldForAYak(LabYak labyak, int elapsed){
		validateInputData(labyak,elapsed);
		LabYakYield yield = computeYieldForAYak(labyak,elapsed);
		storeService.storeTotalYieldForAYak(labyak.getName(), yield);
	}

	private void validateInputData(LabYak labyak, int elapsed) {		
		if (labyak == null)
			throw new IllegalArgumentException(" Input data can't be null");
		
		double age = labyak.getAge();
		if (age<0 || age >= lifeExpectancy)
				throw new IllegalArgumentException("Invalid Age ");
	
		String name= labyak.getName();
		if(name ==null || name.trim().length() < 1)
			throw new IllegalArgumentException("Invalid Name ");
		
		if(elapsed<0)
			throw new IllegalArgumentException("Invalid elapsedTime ");		
	}
	
	private LabYakYield computeYieldForAYak(LabYak labyak, int elapsed) {
		int ageDays= (int) (labyak.getAge()*yakYearDays);
		int actualElapsedDays= getActualElapsedDays(ageDays,elapsed);
		double milkAmount=0.0;
		if(labyak.isMilkEligible())
			milkAmount=calculateMilkProduced(ageDays,actualElapsedDays);
		int skins=calculateSkinProduced(ageDays,actualElapsedDays);
		return new LabYakYield(milkAmount, skins);
		
	}
	
	private int getActualElapsedDays(int ageDays, int elapsedDays){	
		int yakTotalAge=(int)(lifeExpectancy*yakYearDays);
		if(ageDays+elapsedDays > yakTotalAge )
			return (yakTotalAge-ageDays);
		return elapsedDays;
	}
	
	private double calculateMilkProduced(int ageDays, int elapsedDays){
		return elapsedDays*(50.0-(ageDays*0.03)-((elapsedDays-1)*0.015));	
	}
	
	private int calculateSkinProduced(int ageDays, int elapsedDays){
		int nextShave=0;
		int skin=0;
		while(nextShave<elapsedDays){
			skin++;
			nextShave=(int)(8+(ageDays*0.01)) +nextShave;			
			ageDays=ageDays+nextShave;	
			nextShave++;
		}
		return skin;
	}
	
	public LabYakYield provideTotalYield(){
		return storeService.getYieldforAllYakFromStore();		
	}
	
	public String computeAgeDetails(LabYak labyak, int elapsed) {
		int ageDays= (int) (labyak.getAge()*yakYearDays);
		int actualElapsedDays= getActualElapsedDays(ageDays,elapsed);
		if(actualElapsedDays<elapsed)
			return labyak.getName()+ " died after day "+actualElapsedDays;
		else
	        return labyak.getName() + " " + (ageDays+actualElapsedDays)/100.00 + " years old" ;
	}
}
