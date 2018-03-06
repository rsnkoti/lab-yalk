package com.xebia.yalk;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.xebia.yalk.impl.LabYakServiceImpl;
import com.xebia.yalk.service.LabYakService;
import com.xebia.yalk.vo.LabYak;
import com.xebia.yalk.vo.LabYakYield;
import com.xebia.yalk.vo.YakSex;

public class LabYalkMainTest {


	@Test(expected=IllegalArgumentException.class)
	public void testLabYalkSericeForInvalidArgumentT() {	
		LabYakService labYakservice = new LabYakServiceImpl();
        labYakservice.produceYieldForAYak(new LabYak.Builder().name("alpha").age(4).sex(YakSex.FEMALE).build(), -1);     
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLabYalkSericeForInvalidNameNull() {	
		LabYakService labYakservice = new LabYakServiceImpl();
        labYakservice.produceYieldForAYak(new LabYak.Builder().name(null).age(4).sex(YakSex.FEMALE).build(), 1);
        
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLabYalkSericeForInvalidNameEmpty() {
		LabYakService labYakservice = new LabYakServiceImpl();
        labYakservice.produceYieldForAYak(new LabYak.Builder().name(" ").age(4).sex(YakSex.FEMALE).build(), 1);
        
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLabYalkSericeForNullEntry(){
		LabYakService labYakservice = new LabYakServiceImpl();
        labYakservice.produceYieldForAYak(null, 1);     
	}
	
	@Test
	public void testLabYalkSericeForMaleYakNoMilk(){
		LabYakService labYakservice = new LabYakServiceImpl();
		labYakservice.produceYieldForAYak(new LabYak.Builder().name("Male-1").age(4).sex(YakSex.MALE).build(), 10);
		labYakservice.produceYieldForAYak(new LabYak.Builder().name("Male-2").age(8).sex(YakSex.MALE).build(), 10);
		LabYakYield totalYakYield = labYakservice.provideTotalYield();
		assertEquals("Test5 failed where 0.00 was expected as milk yield for Male Yak", 0.00,totalYakYield.getMilkYield(),0.00);	
	}
	
	@Test
	public void testLabYalkSericeForInputExampleOneT13() {
		
		Set<LabYak> yakSet = new HashSet<LabYak>();
		LabYakService labYakservice = new LabYakServiceImpl();
		
		LabYak yakOne = new LabYak.Builder().name("Betty-1").age(4).sex(YakSex.FEMALE).build();
		LabYak yakTwo = new LabYak.Builder().name("Betty-2").age(8).sex(YakSex.FEMALE).build();
		LabYak yakThree = new LabYak.Builder().name("Betty-3").age(9.5).sex(YakSex.FEMALE).build();
	
		yakSet.add(yakOne);
		yakSet.add(yakTwo);
		yakSet.add(yakThree);

        for(LabYak labYak : yakSet)
        	labYakservice.produceYieldForAYak(labYak, 13);
		
        LabYakYield totalYakYield = labYakservice.provideTotalYield();
		assertEquals("Test6 failed where 1104.480 was expected as milk yield", 1104.48,totalYakYield.getMilkYield(),0.10);
		assertEquals("Test6 failed where 3 was expected as skin yield", 3, totalYakYield.getSkinYield());	
	}
	
	@Test
	public void testLabYalkSericeForInputExampleOneT14() {
		
		Set<LabYak> yakSet = new HashSet<LabYak>();
		LabYakService labYakservice = new LabYakServiceImpl();
		
		LabYak yakOne = new LabYak.Builder().name("Betty-1").age(4).sex(YakSex.FEMALE).build();
		LabYak yakTwo = new LabYak.Builder().name("Betty-2").age(8).sex(YakSex.FEMALE).build();
		LabYak yakThree = new LabYak.Builder().name("Betty-3").age(9.5).sex(YakSex.FEMALE).build();
	
		yakSet.add(yakOne);
		yakSet.add(yakTwo);
		yakSet.add(yakThree);

        for(LabYak labYak : yakSet)
        	labYakservice.produceYieldForAYak(labYak, 14);
		
        LabYakYield totalYakYield = labYakservice.provideTotalYield();
        
		assertEquals("Test7 failed where 1188.81 was expected as milk yield", 1188.81,totalYakYield.getMilkYield(),0.10);
		assertEquals("Test7failed where 4 was expected as skin yield", 4, totalYakYield.getSkinYield());
		
	}
	
	@Test
	public void testLabYalkSericeForZeroDaySkin() {
		
		Set<LabYak> yakSet = new HashSet<LabYak>();
		LabYakService labYakservice = new LabYakServiceImpl();
		
		LabYak yakOne = new LabYak.Builder().name("Betty-1").age(4).sex(YakSex.MALE).build();
		LabYak yakTwo = new LabYak.Builder().name("Betty-2").age(8).sex(YakSex.FEMALE).build();
		LabYak yakThree = new LabYak.Builder().name("Betty-3").age(9.5).sex(YakSex.FEMALE).build();
	
		yakSet.add(yakOne);
		yakSet.add(yakTwo);
		yakSet.add(yakThree);

        for(LabYak labYak : yakSet)
        	labYakservice.produceYieldForAYak(labYak, 1);
		
        LabYakYield totalYakYield = labYakservice.provideTotalYield();
		assertEquals("Test8 failed where 3 was expected as skin yield for zero day", 3, totalYakYield.getSkinYield());
		
	}
	
	@Test
	public void testAgeComputeOneYakDies() {
		
		Set<LabYak> yakSet = new HashSet<LabYak>();
		LabYakService labYakservice = new LabYakServiceImpl();
		
		LabYak yakOne = new LabYak.Builder().name("Betty-1").age(4).sex(YakSex.FEMALE).build();
		LabYak yakTwo = new LabYak.Builder().name("Betty-2").age(8).sex(YakSex.FEMALE).build();
		LabYak yakThree = new LabYak.Builder().name("Betty-3").age(9.5).sex(YakSex.FEMALE).build();
	
        String ageDetailOne=  labYakservice.computeAgeDetails(yakOne, 51);
        String ageDetailTwo=  labYakservice.computeAgeDetails(yakTwo, 51);
        String ageDetailThree=  labYakservice.computeAgeDetails(yakThree, 51);
        
        assertEquals("Test9 failed where Betty-1expected to be alive", "Betty-1 4.51 years old", ageDetailOne);
        assertEquals("Test9 failed where Betty-2 expected to be alive", "Betty-2 8.51 years old", ageDetailTwo);
        assertEquals("Test9 failed where Betty-3 was expected to be dead", "Betty-3 died after day 50", ageDetailThree);
        
	}
}
