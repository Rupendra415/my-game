package com.mygame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game_details")
public class GameDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "location")
    private String location;
    @Column(name = "game")
    private String game;
    @Column(name = "date")
    private String date;
    @Column(name = "start")
    private String startTime;
    @Column(name = "end")
    private String endTime;
    @Column(name = "bufferstart")
    private String bufferStartTime;
    @Column(name = "bufferend")
    private String bufferEndTime;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBufferStartTime() {
		return bufferStartTime;
	}
	public void setBufferStartTime(String bufferStartTime) {
		this.bufferStartTime = bufferStartTime;
	}
	public String getBufferEndTime() {
		return bufferEndTime;
	}
	public void setBufferEndTime(String bufferEndTime) {
		this.bufferEndTime = bufferEndTime;
	}
}
