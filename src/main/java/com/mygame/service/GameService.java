package com.mygame.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygame.dto.MyGameDTO;
import com.mygame.model.GameDetails;
import com.mygame.model.User;
import com.mygame.repository.GameRepository;
import com.mygame.repository.UserRepository;

@Service
public class GameService {
	@Autowired
    private GameRepository gameRepository;
	@Autowired
    private UserRepository userRepository;
	
	public Optional<User> findByUsername(String id) {
		return userRepository.findByUserName(id);
	}
	
	public List<GameDetails> findRecordsByUsername(String id) {
		return gameRepository.findAllByUsername(id);
	}
	
	public GameDetails updateGameDetails(MyGameDTO myGameDTO) {
		GameDetails game = new GameDetails();
		User user = myGameDTO.getUser();
		game.setEmail(user.getEmail());
		game.setName(user.getFullName());
		game.setUsername(user.getUserName());
		game.setLocation(myGameDTO.getLocation());
		game.setGame(myGameDTO.getGame());
		game.setDate(myGameDTO.getDate());
		game.setStartTime(myGameDTO.getStartTime());
		game.setEndTime(myGameDTO.getEndTime());
		game.setBufferStartTime(myGameDTO.getBufferStartTime());
		game.setBufferEndTime(myGameDTO.getBufferEndTime());
		GameDetails result = gameRepository.save(game);
		return result;
	}

	public List<GameDetails> findCurrentBookings(String location, String game, String date) {
		return gameRepository.findCurrentBookings(location, game, date);
	}
	
	public void deleteById(Integer id) {
		gameRepository.deleteById(id);
	}
}
