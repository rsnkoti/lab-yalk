package com.xebia.yalk.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xebia.yalk.service.LabYakStoreService;
import com.xebia.yalk.vo.LabYakYield;

@Service
public class LabYakStoreServiceImpl  implements  LabYakStoreService{

    private Map<String, LabYakYield> labYakStore;;
   
    public void storeTotalYieldForAYak(String name, LabYakYield yield) {
        if(labYakStore == null) {
        	labYakStore = new HashMap<>();
        }
        labYakStore.put(name,yield);
    }
    
    public LabYakYield getYieldforAllYakFromStore() {
        double totalMilkAmount = 0;
        int totalSkins = 0;
        if(labYakStore == null) {
        	labYakStore = new HashMap<>();
        }
        for (String key : labYakStore.keySet()) {
        	LabYakYield eachYalkYield = labYakStore.get(key);
        	totalMilkAmount=totalMilkAmount+ eachYalkYield.getMilkYield();
        	totalSkins=totalSkins + eachYalkYield.getSkinYield();
        }
        return  new LabYakYield(totalMilkAmount,totalSkins);
    }

    public LabYakYield getYieldforAllYakFromStoreForElapsedDays(int elapseddays) {
    	//TODO
    	return null;
    }
}
