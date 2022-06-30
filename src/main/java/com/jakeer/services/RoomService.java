package com.jakeer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jakeer.modal.Room;
import com.jakeer.repository.RoomRepository;
@Component
@Service
@Transactional
public class RoomService {
	@Autowired
	private final RoomRepository userRepository;
	
	public RoomService(RoomRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public void saveMyRoom(Room user ) {
		userRepository.save(user);
	}
	
	public List<Room> showAllRooms(){
		List<Room> users = new ArrayList<Room>();
		for(Room user : userRepository.findAll()) {
			users.add(user);
		}
		
		return users;
	}
	
	public void deleteMyRoom(int id) {
		userRepository.deleteById(id);
	}
	
	public Optional<Room> editRoom(int id) {
		return userRepository.findById(id);
	}
	
	}

	