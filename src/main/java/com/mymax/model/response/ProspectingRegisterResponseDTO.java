package com.mymax.model.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ProspectingRegisterResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String prospectingCustomerId;
	private LocalDate inputDate;
	private LocalTime inputTime;
	private String idCardNumber;
	private String fullName;
	private String currentStatusId;
	private String currentStatusDescription;
	
}
