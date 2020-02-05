package com.wildcodeschool.festivalorleansjoue.services;

import com.wildcodeschool.festivalorleansjoue.entity.Event;

public class EventCardHomeView {
	
	private Event event;
	private boolean subscribeBtnOn = true;
	private boolean hasSubscribe = false;
	
	public EventCardHomeView(Event event, boolean subscribeBtnOn, boolean hasSubsribe) {
		
		this.event = event;
		this.subscribeBtnOn = subscribeBtnOn;
		this.hasSubscribe = hasSubsribe;
		
	}
	

	public Event getEvent() {
		
		return this.event;
	}

	
	public void setEvent(Event event) {
		
		this.event = event;
	}

	
	public boolean isSubscribeBtnOn() {
		
		return this.subscribeBtnOn;
	}

	
	public void setSubscribeBtnOn(boolean subscribeBtnOn) {
		
		this.subscribeBtnOn = subscribeBtnOn;
	}


	public boolean isHasSubscribe() {
		
		return this.hasSubscribe;
	}


	public void setHasSubscribe(boolean hasSubscribe) {
		
		this.hasSubscribe = hasSubscribe;
	}
	
	
	
	

}
