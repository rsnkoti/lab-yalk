package com.xebia.yalk.service;

import com.xebia.yalk.vo.LabYakYield;

public interface LabYakStoreService {

	 void storeTotalYieldForAYak(String name, LabYakYield yield);
	 LabYakYield getYieldforAllYakFromStore();

}
