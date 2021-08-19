package com.mobiquity.atm.controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mobiquity.atm.exception.AtmNotFoundException;
import com.mobiquity.atm.model.Atms;

@RestController
public class AppController {

	private final String URI_ATMS = "/atms/";

	@Autowired
	RestTemplate restTemplate;

	// 2. getForEntity(url, responseType)
	@GetMapping("atms")
	public ResponseEntity<List<Atms>> getAllAtms() {

		String response = restTemplate.getForObject(URI_ATMS, String.class);

		String formatedResponse = "";
		Scanner scanner = new Scanner(response);
		int count = 1;
		while (scanner.hasNextLine()) {
			if (count == 1) {
				scanner.nextLine();
				count++;
				continue;

			}
			formatedResponse = scanner.nextLine();

		}
		scanner.close();

		ObjectMapper objectMapper = new ObjectMapper();
		List<Atms> atmsList = null;
		try {
			atmsList = objectMapper.readValue(formatedResponse,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Atms.class));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<List<Atms>>(atmsList, HttpStatus.OK);

	}

	@GetMapping("atms/{city}")
	public ResponseEntity<List<Atms>> getAtmsByCity(@PathVariable final String city) {
		String response = restTemplate.getForObject(URI_ATMS, String.class);

		String formatedResponse = "";
		Scanner scanner = new Scanner(response);
		int count = 1;
		while (scanner.hasNextLine()) {
			if (count == 1) {
				scanner.nextLine();
				count++;
				continue;

			}
			formatedResponse = scanner.nextLine();

		}
		scanner.close();

		ObjectMapper objectMapper = new ObjectMapper();
		List<Atms> atmsList = null;
		try {
			atmsList = objectMapper.readValue(formatedResponse,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Atms.class));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Atms> atmsListByCity = atmsList.stream().filter(atm -> atm.getAddress().getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());
		
		if(atmsListByCity.size()==0) {
			
			throw new AtmNotFoundException("ATM is not there in the {} city "+city);
			
		}
		
		return new ResponseEntity<List<Atms>>(atmsListByCity, HttpStatus.OK);

	}
}
