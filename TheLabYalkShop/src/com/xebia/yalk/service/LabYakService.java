package com.xebia.yalk.service;

import com.xebia.yalk.vo.LabYak;
import com.xebia.yalk.vo.LabYakYield;

public interface LabYakService {
	double lifeExpectancy=10; //years
	int yakYearDays=100;
	double firstSkinAge=1; //age in years
	
	void produceYieldForAYak(LabYak labyak, int elapsed);
	LabYakYield provideTotalYield();
	String computeAgeDetails(LabYak labyak, int elapsed);
}
