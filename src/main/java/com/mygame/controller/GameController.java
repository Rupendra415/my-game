package com.mygame.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygame.MyGameApplication;
import com.mygame.dto.MyGameDTO;
import com.mygame.model.GameDetails;
import com.mygame.model.User;
import com.mygame.service.GameService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/myGame")
@RestController
public class GameController extends User{

	private List<GameDetails> currentBookings = new ArrayList<GameDetails>();
	
	@Autowired
	private GameService gameService;

    @PostMapping(value = "/addGameDetails")
    @CrossOrigin(origins = "http://localhost:4200")
    public String alternate(@RequestParam("details") String details) {
    	ObjectMapper objectMapper = new ObjectMapper();
    	MyGameDTO dto = null;
    	try {
			dto = objectMapper.readValue(details, MyGameDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String userName = dto.getUser().getUserName();
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	Date reqStartDate = null;
    	Date reqEndDate = null;
		try {
			reqStartDate = formatter.parse(dto.getDate()+" "+dto.getStartTime());
			reqEndDate = formatter.parse(dto.getDate()+" "+dto.getEndTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		final Date finalReqStartDate = reqStartDate;
		final Date finalReqEndDate = reqEndDate;
		final int reqTotalMinus =  (reqEndDate.getHours()*60+reqEndDate.getMinutes())
				- (reqStartDate.getHours()*60+reqStartDate.getMinutes());
		currentBookings = gameService.findCurrentBookings(dto.getLocation(), dto.getGame(), dto.getDate());
		AtomicInteger startBwCount = new AtomicInteger(0);
		AtomicInteger endBwCount = new AtomicInteger(0);
		AtomicInteger sameUserStartBwCount = new AtomicInteger(0);
		AtomicInteger sameUserEndBwCount = new AtomicInteger(0);
		AtomicInteger actTotalMinus = new AtomicInteger(0);
		if (currentBookings.size() > 0) {
			currentBookings.stream().forEach(curBok -> {
				Date actStartDate = null;
		    	Date actEndDate = null;
		    	try {
					actStartDate = formatter.parse(curBok.getDate()+" "+curBok.getStartTime());
					actEndDate = formatter.parse(curBok.getDate()+" "+curBok.getEndTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (userName.equals(curBok.getUsername())) {
					actTotalMinus.getAndAdd((actEndDate.getHours()*60+actEndDate.getMinutes())
							- (actStartDate.getHours()*60+actStartDate.getMinutes()));
					if (reqTotalMinus + actTotalMinus.get() > 60) 
						throw new RuntimeException("Booking time exceeded max limit for the selected criteria");
					if ((actStartDate.before(finalReqStartDate) && actEndDate.after(finalReqStartDate))
							|| actStartDate.equals(finalReqStartDate)) 
						sameUserStartBwCount.getAndIncrement();
					if (actStartDate.before(finalReqEndDate) && actEndDate.after(finalReqEndDate)
							|| actEndDate.equals(finalReqEndDate)) 
						sameUserEndBwCount.getAndIncrement();
					if (sameUserStartBwCount.get() > 0 || sameUserEndBwCount.get() > 0) 
						throw new RuntimeException("You already have active booking for the selected criteria");
				}
				if ((actStartDate.before(finalReqStartDate) && actEndDate.after(finalReqStartDate))
						|| actStartDate.equals(finalReqStartDate)) startBwCount.getAndIncrement();
				if (actStartDate.before(finalReqEndDate) && actEndDate.after(finalReqEndDate)
						|| actEndDate.equals(finalReqEndDate)) endBwCount.getAndIncrement();
				if (startBwCount.get() >= 4 || endBwCount.get() >= 4) 
					throw new RuntimeException("Exceeded max users allowed for the selected criteria");
			});
		}
    	GameDetails abc = gameService.updateGameDetails(dto);
    	return "Success";
    }
    
    @PostMapping(value = "/userAuthentication")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> authenticateUser(@RequestParam("username") String username, 
    		@RequestParam("password") String password) {
    	Optional<User> optionalUsers = gameService.findByUsername(username);
    	optionalUsers.ifPresent(user -> MyGameApplication.getInstance().setUser(user));
    	if (optionalUsers.isPresent() &&
    			MyGameApplication.getInstance().getUser().getPassword().equals(password)) {
    		MyGameApplication.getInstance().getUser().setPassword(null);
    		return ResponseEntity.ok().body(optionalUsers.get());
    	}
    	else
    		throw new RuntimeException("Username or password is incorrect");
    }
    @GetMapping(value = "/viewCurrentBookings", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<GameDetails> currentBookings(@RequestParam("location") String location, 
    		@RequestParam("game") String game, @RequestParam("date") String date) {
    	currentBookings = gameService.findCurrentBookings(location, game, date);
		return currentBookings;
    }
    @GetMapping(value = "/viewMyBookings", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<GameDetails> myBookings(@RequestParam("username") String userName) {
    	List<GameDetails> myBookings = gameService.findRecordsByUsername(userName);
		return myBookings;
    }
    @DeleteMapping(value = "/deleteBookings", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<GameDetails> deleteBookings(@RequestParam("id") String id) {
    		gameService.deleteById(Integer.parseInt(id));
    	return null;
    }
    @GetMapping(value = "/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public String all() {
		return "Success";
    }
}
