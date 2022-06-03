package com.revature.hotel_management_system.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.hotel_management_system.model.ReceptionistLogIn;
import com.revature.hotel_management_system.repo.ReceptionistRepo;

@Service
public class ReceptionistLogInService {
	
	@Autowired
	private ReceptionistRepo receptionistRepo;
	
	
	public String getReceptionistName(String emailId) {
		
		ReceptionistLogIn receptionistLogIn=receptionistRepo.findById(emailId).orElse(null);
		return receptionistLogIn.getName();
		
	}

	public boolean addReceptionist(ReceptionistLogIn receptionistLogIn) {
		
		ReceptionistLogIn findReceptionist=receptionistRepo.findById(receptionistLogIn.getEmailId()).orElse(null);
		if(Objects.nonNull(findReceptionist))
			return false;
		else {
		receptionistRepo.save(receptionistLogIn);
		return true;
		}
		
	}
	
	public List<ReceptionistLogIn> showAllReceptionists(){
		
		List<ReceptionistLogIn> receptionists=(List<ReceptionistLogIn>) receptionistRepo.findAll();
		return receptionists;
		
	}
}
