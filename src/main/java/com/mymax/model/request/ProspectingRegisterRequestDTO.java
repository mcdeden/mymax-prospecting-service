package com.mymax.model.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
public class ProspectingRegisterRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@NotBlank(message="is required.")
	@Pattern(regexp = "^[0-9]*$",message = "should be number.")
	@Size(min=16, max=16, message="must be 16 characters long.")
	private String idCardNumber;
	
	@NotBlank(message="is required.")
	@Pattern(regexp = "^[A-Za-z\\s]*$",message = "should be character and space only.")
	@Size(min=3, max=200, message="must be 3-200 characters long.")
	private String fullName;
	
	@NotBlank(message="is required.")
	@Size(min=5, max=20, message="must be 5-20 characters long.")
	private String address;
	
	private String birthPlace;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="is required.")
	@Past(message = "should be past day.")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate birthDate;
	
	@NotBlank(message="is required.")
	@Size(min=1, max=1, message="must be 1 character long.")
	private String religionId;
	
	@NotBlank(message="is required.")
	@Size(min=1, max=1, message="must be 1 character long.")
	private String genderId;
	
	@NotBlank(message="is required.")
	@Size(min=1, max=1, message="must be 1 character long.")
	private String maritalStatusId;
	
	@NotBlank(message="is required.")
	@Pattern(regexp = "^[0-9]*$",message = "should be number.")
	@Size(min=10, max=15, message="must be 10-15 characters long.")
	private String mobilePhone;
	
	@NotBlank(message="is required.")
	@Size(min=3, max=3, message="must be 3 character long.")
	private String occupationId;
	
	private String email;
	
	@NotBlank(message="is required.")
	@Size(min=2, max=2, message="must be 2 character long.")
	private String customerTypeId;
	
	private String latitude;
	private String longitude;
	
	@NotBlank(message="is required.")
	@Size(min=6, max=6, message="must be 6 character long.")
	private String salesUserId;
	
	@NotBlank(message="is required.")
	@Size(min=2, max=2, message="must be 2 character long.")
	private String currentStatusId;	
	
}
