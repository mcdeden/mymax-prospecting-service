package com.mymax.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mymax.exception.rest.DataNotFoundException;
import com.mymax.exception.rest.DeleteException;
import com.mymax.exception.rest.NIKDuplicationException;
import com.mymax.model.request.ProspectingRegisterRequestDTO;
import com.mymax.model.response.ProspectingInformationDetailResponseDTO;
import com.mymax.model.response.ProspectingRegisterResponseDTO;
import com.mymax.service.ProspectingService;

@RestController
@RequestMapping("prospectings")
public class ProspectingController {

	@Autowired
	private ProspectingService prospectingService;

	@PostMapping(value = "register")
	public ResponseEntity<ProspectingRegisterResponseDTO> store(@Valid @RequestBody ProspectingRegisterRequestDTO request) {
		
		if (prospectingService.isExist(request.getIdCardNumber())) {
		   	throw new NIKDuplicationException("Prospecting customer with NIK: " + request.getIdCardNumber() + " is already registered");
		}		

		ProspectingRegisterResponseDTO response = prospectingService.create(request);

		return new ResponseEntity<ProspectingRegisterResponseDTO>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "{salesid}")
	public ResponseEntity<List<ProspectingInformationDetailResponseDTO>> getAllProcpectings(@PathVariable("salesid") String salesid) {		

		List<ProspectingInformationDetailResponseDTO> responses = prospectingService.findAllBySalesId(salesid);

		return new ResponseEntity<List<ProspectingInformationDetailResponseDTO>>(responses, HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<ProspectingInformationDetailResponseDTO> getDetailProspecting(@PathVariable String id) {		

		ProspectingInformationDetailResponseDTO response = prospectingService.findOneById(id);

		return new ResponseEntity<ProspectingInformationDetailResponseDTO>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteProspecting(@PathVariable String id) {		

		try {
			prospectingService.deleteOneById(id);
		    return new ResponseEntity<String>("Data deleted",HttpStatus.NO_CONTENT);
		} catch (Exception e) {
		    throw new DeleteException(e.getLocalizedMessage());
		}
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<ProspectingRegisterResponseDTO> updateProspecting(@RequestParam String id, @Valid @RequestBody ProspectingRegisterRequestDTO request) {		

		if (!prospectingService.isExistById(id)) {
		   	throw new DataNotFoundException("Prospecting customer with id: " + id + " is not found");
		}	
		
		ProspectingRegisterResponseDTO response = prospectingService.update(id,request);

		return new ResponseEntity<ProspectingRegisterResponseDTO>(response, HttpStatus.OK);
	}
	
}
