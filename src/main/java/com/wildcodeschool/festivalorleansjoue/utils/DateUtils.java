package com.wildcodeschool.festivalorleansjoue.utils;

import java.util.Date;
import com.wildcodeschool.festivalorleansjoue.entity.Event;

public class DateUtils {

	public static Date getDate() {

		Date today = new Date();
		return today;
	}
	

	public static void registrationCondition(Date today, Event event) {

		if (today.before(event.getEditorsRegistrationBeginDate())) {
			event.setRegistrationMessage("L'inscription n'est pas encore ouverte");
		} else {
			event.setRegistrationMessage("L'inscription est cloturée");
		}
	}
}
