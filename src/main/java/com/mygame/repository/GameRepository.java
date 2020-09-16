package com.mygame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mygame.model.GameDetails;

public interface GameRepository extends JpaRepository<GameDetails, Integer>{
	List<GameDetails> findAllByUsername(String id);
	
	@Query(value="SELECT * FROM game_details WHERE location=?1 AND game=?2 AND date=?3 ORDER BY start ASC", nativeQuery = true)
	List<GameDetails> findCurrentBookings(String location, String game, String date);
}
