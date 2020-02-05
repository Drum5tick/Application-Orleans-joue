package com.wildcodeschool.festivalorleansjoue.services;

import java.util.Date;
import java.util.List;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.User;

public interface IEventService {

	public List<Event> returnEvent(Date today);
	
	public void setMessage(User user, List<Event> events, Date today);
}
