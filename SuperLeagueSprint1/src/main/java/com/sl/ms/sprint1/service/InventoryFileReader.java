package com.sl.ms.sprint1.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.sl.ms.sprint1.model.Inventory;
@Service
public class InventoryFileReader {
	
	public List<Inventory> readInventoryFile() {
		
	    String inputDir = "src\\main\\resources";  // Relative path taken - TODO - Dependency Injection to be done
	    File[] files = new File(inputDir).listFiles(obj -> obj.isFile() && obj.getName().endsWith(".csv"));

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    List<Inventory> list = new ArrayList<>();
	    List<Inventory> inventoryList = new ArrayList<>();

	    for (File file : files) {

	      System.out.println("Reading inventory file : " + file.getName());
	      try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {

	        list = stream
	            .skip(1)
	            .map(line -> {
	              String[] tempArray = line.split(",");
	              return new Inventory(LocalDate.parse(tempArray[0], formatter),
	                  tempArray[1],
	                  tempArray[2],
	                  Integer.parseInt(tempArray[3]),
	                  Integer.parseInt(tempArray[4]));
	        }).collect(Collectors.toCollection(ArrayList::new));

	        inventoryList.addAll(list);
	        
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return inventoryList;
	}

}
