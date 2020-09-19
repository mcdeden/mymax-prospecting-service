package com.mymax.model.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ProspectingInformationDetailResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private LocalDate inputDate;
	private LocalTime inputTime;
	private String idCardNumber;
	private String fullName;
	private String address;		
	private String birthPlace;
	private LocalDate birthDate;	
	private String religionId;
	private String religionDescription;
	private String genderId;
	private String genderDescription;
	private String maritalStatusId;
	private String maritalStatusDescription;
	private String mobilePhone;
	private String occupationId;
	private String occupationDescription;
	private String email;
	private String customerTypeId;
	private String customerTypeDescription;
	private String latitude;
	private String longitude;
	private String salesUserId;
	private String salesUserName;
	private String currentStatusId;
	private String currentStatusDescription;
	
}
