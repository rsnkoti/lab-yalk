package com.xebia.yalk;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import com.xebia.yalk.impl.LabYakServiceImpl;
import com.xebia.yalk.service.LabYakService;
import com.xebia.yalk.util.HerdInputXMLParser;
import com.xebia.yalk.vo.LabYak;
import com.xebia.yalk.vo.LabYakYield;

@SpringBootApplication
public class LabYalkApplication implements ApplicationRunner {
	static LabYakService labYakservice = new LabYakServiceImpl();

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LabYalkApplication.class);
	
	public static void main(String... args) {
		SpringApplication.run(LabYalkApplication.class, args);
	}
	
	@Override
    public void run(ApplicationArguments args) throws Exception {
    	LOGGER.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
    	LOGGER.info("NonOptionArgs: {}", args.getNonOptionArgs());

    	final String inputXml = args.getNonOptionArgs().get(0);
    	final String days = args.getNonOptionArgs().get(1);
        
    	//Get file from resources folder
    	//ClassLoader classLoader = getClass().getClassLoader();
    	//File file = new File(classLoader.getResource(inputXml).getFile());
    	
    	//File file = new File("classpath:inputXml");
    	
    	//File file =ResourceUtils.getFile("classpath:herd.xml");
    	//File file =ResourceUtils.getFile("jar:inputXml");
    	
    	File file = new File(inputXml);
        int elapsedDays = Integer.parseInt(days);
    	
        Set<LabYak> labYakSet = new HerdInputXMLParser(file).parse();

        System.out.println("Output for T = "+elapsedDays+":\n\n");

        for(LabYak labYak : labYakSet){
        	labYakservice.produceYieldForAYak(labYak, elapsedDays);
        }
        
        LabYakYield totalYakYield = labYakservice.provideTotalYield();
        displayStock(totalYakYield);
        displayHerd(labYakSet,elapsedDays);
    }

    public static void displayStock(LabYakYield totalYield){
        System.out.println("In Stock:");
        System.out.println("\t"+totalYield.getMilkYield()+" liters of milk");
        System.out.println("\t"+totalYield.getSkinYield()+" skins of wool");
    }
        
    public static void displayHerd(Set<LabYak> yakSet,int elapsedDays){
        System.out.println("Herd:");
        for(LabYak labYak: yakSet)
        	System.out.println("\t"+labYakservice.computeAgeDetails(labYak, elapsedDays));

    }
}
