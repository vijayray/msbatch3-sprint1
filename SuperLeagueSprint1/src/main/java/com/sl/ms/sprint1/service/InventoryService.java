package com.sl.ms.sprint1.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.ms.sprint1.model.Inventory;

@Service
public class InventoryService {

	@Autowired
	private InventoryFileReader inventoryFileReader;

	static Predicate<Inventory> currMonth = p -> p.getInvetoryDate().getMonth().equals(LocalDate.now().getMonth());
	
	public void generateSummeryRport() {
				
		List<Inventory> inventoryList = new ArrayList<>();
		inventoryList = inventoryFileReader.readInventoryFile();
		//Report1
		stockSummaryPerDay(inventoryList);
		//Report2
		salesLeaderBoard(inventoryList);
		//Report3
	    totalItemsSoldToday(inventoryList);
	    //Report4
	    totalItemsPerMonth(inventoryList);
	    //Report5
	    summaryParticularItem(inventoryList);

	}
	
	/**
	   * Stock Summary per day
	  * @param inventoryList
	  */
	  private static void stockSummaryPerDay(List<Inventory> inventoryList) {

	   
	    System.out.println("************************************************************");
		System.out.println("     Stock Summary Per Day                 					");
		System.out.println("************************************************************");

		Map<LocalDate, List<Inventory>> list = inventoryList.stream()
				.collect(Collectors.groupingBy(Inventory::getInvetoryDate));
		list.entrySet().forEach(i -> {
			System.out.println("-----" + i.getKey() + "-------");
			System.out.println("InvDate    Id  Name  Price Quantity");
			i.getValue().forEach(a -> {
				System.out.println(a.toString());
			});
		});
	  }

	  /**
	   * Stock Summary sold today
	  * @param inventoryList
	  */
	  private static void totalItemsSoldToday(List<Inventory> inventoryList) {
	    
		System.out.println("***************************************************************");
		System.out.println("      Summary of total items sold per day              		   ");
		System.out.println("***************************************************************");

	    int itemsSoldPerDay = inventoryList.stream().filter(i -> i.getInvetoryDate().equals(LocalDate.now()))
				.collect(Collectors.summingInt(i -> i.getQuantity()));
		System.out.println(LocalDate.now() + "---> " + itemsSoldPerDay);

	  }

	  /**
	  * This method provides Summary of quantity of sale for one particular item
	 * @param inventoryList
	 * @param itemId
	 */
	  private static void summaryParticularItem(List<Inventory> inventoryList) {
	    
		System.out.println("***************************************************************");
		System.out.println("     Summary of quantity of sale for one particular item       ");
		System.out.println("***************************************************************");

	    Map<Object, Long> particularItemSale = inventoryList.stream()
				.collect(Collectors.groupingBy(i -> i.getName(), Collectors.summingLong(i -> i.getQuantity())));
			particularItemSale.entrySet()
				.forEach(i -> System.out.print(i.getKey() + "    " + i.getValue() + System.lineSeparator()));

	  }

	  /**
	   * Sales leader board - Top 5 Item in demand 
	 * @param inventoryList
	 */
	  private static void salesLeaderBoard(List<Inventory> inventoryList) {

		System.out.println("***************************************************************");
		System.out.println("  	Sales leader board - Top 5 Item in demand                   ");
		System.out.println("****************************************************************");

			
		inventoryList.stream() .filter(currMonth)
	  	.collect(Collectors.groupingBy(Inventory::getName,LinkedHashMap::new,Collectors.summingInt(Inventory::getQuantity)))
	  	.entrySet()
	  	.stream()
	  	.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	  	.limit(5)
	  	.forEach(x -> {
			System.out.println(x.toString());
		});
			 
	  }

	/**
	 * Total items sold per month
	* @param inventoryList
	*/
	  private static void totalItemsPerMonth(List<Inventory> inventoryList) {
		  
		  System.out.println("***************************************************************");
		  System.out.println("  	Summary of total item sold per month                     ");
		  System.out.println("****************************************************************");

	    
	    Map<Object, Integer> particularMotnh = inventoryList.stream()
				.collect(Collectors.groupingBy(i -> i.getInvetoryDateMonth(), Collectors.summingInt(i -> i.getQuantity())));
	    	particularMotnh.entrySet()
				.forEach(i -> System.out.print(i.getKey() + " total item sold --> " + i.getValue() + System.lineSeparator()));

	  }

}
