package com.mymax.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymax.entity.ProspectingCustomer;
import com.mymax.exception.rest.StoreException;
import com.mymax.helper.DateTimeHelper;
import com.mymax.model.request.ProspectingRegisterRequestDTO;
import com.mymax.model.response.ProspectingInformationDetailResponseDTO;
import com.mymax.model.response.ProspectingRegisterResponseDTO;
import com.mymax.repository.MasterCustomerTypeRepository;
import com.mymax.repository.MasterEmployeeRepository;
import com.mymax.repository.MasterGenderRepository;
import com.mymax.repository.MasterOccupationRepository;
import com.mymax.repository.MasterProspectingStatusRepository;
import com.mymax.repository.MasterReligionRepository;
import com.mymax.repository.MasterMaritalStatusRepository;
import com.mymax.repository.ProspectingCustomerRepository;

@Service
public class ProspectingServiceImpl implements ProspectingService {

	@Autowired
	ProspectingCustomerRepository prospectingCustomerRepository;
	@Autowired
	private MasterGenderRepository masterGenderRepository;
	@Autowired
	private MasterMaritalStatusRepository masterStatusMaritalRepository;
	@Autowired
	private MasterOccupationRepository masterOccupationRepository;
	@Autowired
	private MasterCustomerTypeRepository masterCustomerTypeRepository;
	@Autowired
	private MasterEmployeeRepository masterEmployeeRepository;
	@Autowired
	private MasterProspectingStatusRepository masterProspectingStatusRepository;
	@Autowired
	MasterReligionRepository masterReligionRepository;
	
	@Override
	public ProspectingRegisterResponseDTO create(ProspectingRegisterRequestDTO request) {
		
		ProspectingCustomer newData = new ProspectingCustomer();
		String prospectingId = "PID" + DateTimeHelper.getCurentTimestamp();
		LocalDate currDate = LocalDate.now();
		LocalTime currTime = LocalTime.now();
		
		newData.setId(prospectingId);		
		newData.setInputDate(currDate);
		newData.setInputTime(currTime);
		newData.setIdCardNumber(request.getIdCardNumber());
		newData.setFullName(request.getFullName());
		newData.setAddress(request.getAddress());
		newData.setBirthPlace(request.getBirthPlace());
		newData.setBirthDate(request.getBirthDate());
		newData.setReligion(masterReligionRepository.findById(request.getReligionId()).get());
		newData.setGender(masterGenderRepository.findById(request.getGenderId()).get());
		newData.setMaritalStatus(masterStatusMaritalRepository.findById(request.getMaritalStatusId()).get());
		newData.setMobilePhone(request.getMobilePhone());
		newData.setOccupation(masterOccupationRepository.findById(request.getOccupationId()).get());
		newData.setEmail(request.getEmail());
		newData.setCustomerType(masterCustomerTypeRepository.findById(request.getCustomerTypeId()).get());
		newData.setLatitude(request.getLatitude());
		newData.setLongitude(request.getLongitude());
		newData.setSalesUser(masterEmployeeRepository.findById(request.getSalesUserId()).get());
		newData.setCurrentStatus(masterProspectingStatusRepository.findById(request.getCurrentStatusId()).get());
		
		try {
			prospectingCustomerRepository.save(newData);
		} catch (Exception e) {
			throw new StoreException(e.getLocalizedMessage());
		}
		
		ProspectingRegisterResponseDTO response = new ProspectingRegisterResponseDTO();
		response.setProspectingCustomerId(prospectingId);
		response.setInputDate(currDate);
		response.setInputTime(currTime);
		response.setIdCardNumber(request.getIdCardNumber());
		response.setFullName(request.getFullName());
		response.setCurrentStatusId(masterProspectingStatusRepository.findById("01").get().getId());
		response.setCurrentStatusDescription(masterProspectingStatusRepository.findById("01").get().getName());
		
		return response;
	}	

	@Override
	public Boolean isExist(String nik) {
		if (prospectingCustomerRepository.findByIdCardNumber(nik).isPresent()) {
			return true;
		} 
		
		return false;
	}

	@Override
	public List<ProspectingInformationDetailResponseDTO> findAllBySalesId(String salesId) {		
		List<ProspectingInformationDetailResponseDTO> responses = new ArrayList<ProspectingInformationDetailResponseDTO>();
		
		List<ProspectingCustomer> pcDatas =  prospectingCustomerRepository.findAllBySalesId(salesId);
		for (ProspectingCustomer pcData : pcDatas) {
			ProspectingInformationDetailResponseDTO response = new ProspectingInformationDetailResponseDTO();
			response.setId(pcData.getId());
			response.setInputDate(pcData.getInputDate());
			response.setInputTime(pcData.getInputTime());
			response.setIdCardNumber(pcData.getIdCardNumber());
			response.setFullName(pcData.getFullName());
			response.setAddress(pcData.getAddress());
			response.setBirthPlace(pcData.getBirthPlace());
			response.setBirthDate(pcData.getBirthDate());
			response.setReligionId(pcData.getReligion().getId());
			response.setReligionDescription(pcData.getReligion().getName());
			response.setGenderId(pcData.getGender().getId());
			response.setGenderDescription(pcData.getGender().getName());
			response.setMaritalStatusId(pcData.getMaritalStatus().getId());
			response.setMaritalStatusDescription(pcData.getMaritalStatus().getName());
			response.setMobilePhone(pcData.getMobilePhone());
			response.setOccupationId(pcData.getOccupation().getId());
			response.setOccupationDescription(pcData.getOccupation().getName());
			response.setEmail(pcData.getEmail());
			response.setCustomerTypeId(pcData.getCustomerType().getId());
			response.setCustomerTypeDescription(pcData.getCustomerType().getName());
			response.setLatitude(pcData.getLatitude());
			response.setLongitude(pcData.getLongitude());
			response.setSalesUserId(pcData.getSalesUser().getId());
			response.setSalesUserName(pcData.getSalesUser().getSalesName());
			response.setCurrentStatusId(pcData.getCurrentStatus().getId());
			response.setCurrentStatusDescription(pcData.getCurrentStatus().getName());
			
			responses.add(response);
		}
		
		return responses;
	}

	@Override
	public ProspectingInformationDetailResponseDTO findOneById(String id) {
		ProspectingInformationDetailResponseDTO response = new ProspectingInformationDetailResponseDTO();
		
		Optional<ProspectingCustomer> OptionalPcData =  prospectingCustomerRepository.findById(id);
		if (OptionalPcData.isPresent()) {
			response.setId(OptionalPcData.get().getId());
			response.setInputDate(OptionalPcData.get().getInputDate());
			response.setInputTime(OptionalPcData.get().getInputTime());
			response.setIdCardNumber(OptionalPcData.get().getIdCardNumber());
			response.setFullName(OptionalPcData.get().getFullName());
			response.setAddress(OptionalPcData.get().getAddress());
			response.setBirthPlace(OptionalPcData.get().getBirthPlace());
			response.setBirthDate(OptionalPcData.get().getBirthDate());
			response.setReligionId(OptionalPcData.get().getReligion().getId());
			response.setReligionDescription(OptionalPcData.get().getReligion().getName());
			response.setGenderId(OptionalPcData.get().getGender().getId());
			response.setGenderDescription(OptionalPcData.get().getGender().getName());
			response.setMaritalStatusId(OptionalPcData.get().getMaritalStatus().getId());
			response.setMaritalStatusDescription(OptionalPcData.get().getMaritalStatus().getName());
			response.setMobilePhone(OptionalPcData.get().getMobilePhone());
			response.setOccupationId(OptionalPcData.get().getOccupation().getId());
			response.setOccupationDescription(OptionalPcData.get().getOccupation().getName());
			response.setEmail(OptionalPcData.get().getEmail());
			response.setCustomerTypeId(OptionalPcData.get().getCustomerType().getId());
			response.setCustomerTypeDescription(OptionalPcData.get().getCustomerType().getName());
			response.setLatitude(OptionalPcData.get().getLatitude());
			response.setLongitude(OptionalPcData.get().getLongitude());
			response.setSalesUserId(OptionalPcData.get().getSalesUser().getId());
			response.setSalesUserName(OptionalPcData.get().getSalesUser().getSalesName());
			response.setCurrentStatusId(OptionalPcData.get().getCurrentStatus().getId());
			response.setCurrentStatusDescription(OptionalPcData.get().getCurrentStatus().getName());
		} else {
			response = null;
		}
		
		return response;
		
	}

	@Override
	public void deleteOneById(String id) {
		prospectingCustomerRepository.deleteById(id);
	}

	@Override
	public ProspectingRegisterResponseDTO update(String id, ProspectingRegisterRequestDTO request) {
		LocalDate currDate = LocalDate.now();
		LocalTime currTime = LocalTime.now();
		Optional<ProspectingCustomer> updateData = prospectingCustomerRepository.findById(id);

	    if (updateData.isPresent()) {
	    	updateData.get().setFullName(request.getFullName());
	    	updateData.get().setAddress(request.getAddress());
	    	updateData.get().setBirthPlace(request.getBirthPlace());
	    	updateData.get().setBirthDate(request.getBirthDate());
	    	updateData.get().setReligion(masterReligionRepository.findById(request.getReligionId()).get());
	    	updateData.get().setGender(masterGenderRepository.findById(request.getGenderId()).get());
	    	updateData.get().setMaritalStatus(masterStatusMaritalRepository.findById(request.getMaritalStatusId()).get());
	    	updateData.get().setMobilePhone(request.getMobilePhone());
	    	updateData.get().setOccupation(masterOccupationRepository.findById(request.getOccupationId()).get());
	    	updateData.get().setEmail(request.getEmail());
	    	updateData.get().setCustomerType(masterCustomerTypeRepository.findById(request.getCustomerTypeId()).get());
	    	updateData.get().setLatitude(request.getLatitude());
	    	updateData.get().setLongitude(request.getLongitude());
	    	updateData.get().setSalesUser(masterEmployeeRepository.findById(request.getSalesUserId()).get());
	    	updateData.get().setCurrentStatus(masterProspectingStatusRepository.findById(request.getCurrentStatusId()).get());
			
			try {
				prospectingCustomerRepository.save(updateData.get());
			} catch (Exception e) {
				throw new StoreException(e.getLocalizedMessage());
			}
			
			ProspectingRegisterResponseDTO response = new ProspectingRegisterResponseDTO();
			response.setProspectingCustomerId(id);
			response.setInputDate(currDate);
			response.setInputTime(currTime);
			response.setIdCardNumber(request.getIdCardNumber());
			response.setFullName(request.getFullName());
			response.setCurrentStatusId(masterEmployeeRepository.findById(request.getSalesUserId()).get().getId());
			response.setCurrentStatusDescription(masterProspectingStatusRepository.findById(request.getCurrentStatusId()).get().getName());
			
			return response;
			
	    } else {
	      return null;
	    }
	}

	@Override
	public Boolean isExistById(String id) {
		if (prospectingCustomerRepository.findById(id).isPresent()) {
			return true;
		} 
		
		return false;
	}
	
}
