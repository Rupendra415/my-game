package com.mygame.dto;

import com.mygame.model.User;

public class MyGameDTO {
	private User user;
	private String location;
	private String game;
	private String date;
	private String startTime;
	private String endTime;
	private String bufferStartTime;
	private String bufferEndTime;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "MyGameDTO [user=" + user + ", location=" + location + ", game=" + game + ", date=" + date
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", bufferStartTime=" + bufferStartTime
				+ ", bufferEndTime=" + bufferEndTime + "]";
	}
	
}
