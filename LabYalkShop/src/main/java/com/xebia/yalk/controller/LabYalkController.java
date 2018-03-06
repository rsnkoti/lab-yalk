/**
 * 
 */
package com.xebia.yalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.yalk.service.LabYakService;
import com.xebia.yalk.vo.LabYakDaysYield;
import com.xebia.yalk.vo.LabYakYield;;

/**
 * @author rsnkoti
 *
 */
@RestController
@RequestMapping("/yak-shop")
public class LabYalkController {
	
	@Autowired
	LabYakService labYakService;
	

	@GetMapping("stock/{T}")
	public LabYakYield checkStock(@PathVariable Integer days) {
		LabYakYield yield = labYakService.provideTotalYield();
		return yield;
	}
	
	
	@GetMapping("herd/{T}")
	public List<LabYakDaysYield> checkHerd(@PathVariable Integer days) {
		return null;
	}
	
	@PostMapping("order/{T}")
	@ResponseBody
	public LabYakYield placeOrder(@PathVariable Integer userName)  {
		LabYakYield yield = labYakService.provideTotalYield();
		return yield;
	}
	

}