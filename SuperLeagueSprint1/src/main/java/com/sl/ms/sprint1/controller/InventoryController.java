package com.sl.ms.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ms.sprint1.service.InventoryService;

@RestController
public class InventoryController {

@Autowired
private InventoryService inventoryService;
	
	/**
	 * 
	 */
	@GetMapping("/sprint1")
	public void getSprint1Reports() {
		inventoryService.generateSummeryRport();
	}

}
