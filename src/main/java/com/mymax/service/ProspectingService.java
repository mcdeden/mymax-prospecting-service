package com.mymax.service;

import java.util.List;

import com.mymax.model.request.ProspectingRegisterRequestDTO;
import com.mymax.model.response.ProspectingInformationDetailResponseDTO;
import com.mymax.model.response.ProspectingRegisterResponseDTO;

public interface ProspectingService {

	ProspectingRegisterResponseDTO create(ProspectingRegisterRequestDTO request);
	Boolean isExist(String nik);
	Boolean isExistById(String id);
	List<ProspectingInformationDetailResponseDTO> findAllBySalesId(String salesId);
	ProspectingInformationDetailResponseDTO findOneById(String id);
	void deleteOneById(String id);
	ProspectingRegisterResponseDTO update(String id, ProspectingRegisterRequestDTO request);
	
}
