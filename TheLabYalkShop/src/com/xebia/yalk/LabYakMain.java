package com.xebia.yalk;

import java.io.File;
import java.util.Set;

import com.xebia.yalk.impl.LabYakServiceImpl;
import com.xebia.yalk.service.LabYakService;
import com.xebia.yalk.util.HerdInputXMLParser;
import com.xebia.yalk.vo.LabYak;
import com.xebia.yalk.vo.LabYakYield;

public class LabYakMain 
{
	static LabYakService labYakservice = new LabYakServiceImpl();
	 
    public static void main( String[] args )
    {
    	LabYakMain main = new LabYakMain();
    	
        File input = new File(args[0]);
        int elapsedDays = Integer.parseInt(args[1]);
        
        Set<LabYak> labYakSet = new HerdInputXMLParser(input).parse();

        System.out.println("Output for T = "+elapsedDays+":\n\n");

        for(LabYak labYak : labYakSet){
        	labYakservice.produceYieldForAYak(labYak, elapsedDays);
        }
        
        LabYakYield totalYakYield = labYakservice.provideTotalYield();
        main.displayStock(totalYakYield);
        main.displayHerd(labYakSet,elapsedDays);
    }

    public void displayStock(LabYakYield totalYield){
        System.out.println("In Stock:");
        System.out.println("\t"+totalYield.getMilkYield()+" liters of milk");
        System.out.println("\t"+totalYield.getSkinYield()+" skins of wool");
    }
        
    public void displayHerd(Set<LabYak> yakSet,int elapsedDays){
        System.out.println("Herd:");
        for(LabYak labYak: yakSet)
        	System.out.println("\t"+labYakservice.computeAgeDetails(labYak, elapsedDays));

    }
}
