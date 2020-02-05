package com.wildcodeschool.festivalorleansjoue.services;

import org.springframework.stereotype.Service;


@Service
public class NavbarLinksService {
	
	private String currentPage;
	private boolean homepageLinkOn;
	
	
	public NavbarLinksService() {
	}

	
	public String getCurrentPage() {
		
		return this.currentPage;
	}
	

	public void setCurrentPage(String currentPage) {
		
		this.currentPage = currentPage;
	}


	public boolean isHomepageLinkOn() {
		
		return this.homepageLinkOn;
	}


	public void setHomepageLinkOn(boolean homepageLinkOn) {
		
		this.homepageLinkOn = homepageLinkOn;
	}
	
	
	
}
