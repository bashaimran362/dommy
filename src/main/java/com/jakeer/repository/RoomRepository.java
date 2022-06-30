package com.jakeer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jakeer.modal.Room;
@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

}
