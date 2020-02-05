package com.wildcodeschool.festivalorleansjoue.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.util.Date;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ExportProtozoneRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportShopRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportVolunteerPlanning;
import com.wildcodeschool.festivalorleansjoue.entity.ExportVolunteerRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ExportProtozoneRegistrationsRepo;
import com.wildcodeschool.festivalorleansjoue.repository.ExportRegistrationsRepo;
import com.wildcodeschool.festivalorleansjoue.repository.ExportShopRegistrationsRepo;
import com.wildcodeschool.festivalorleansjoue.repository.ExportVolunteerPlanningRepo;
import com.wildcodeschool.festivalorleansjoue.repository.ExportVolunteerRegistrationsRepo;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.utils.DateUtils;

@Service
public class ExportService {

	@Autowired
	EditorRegistrationService registrationService;

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	ExportRegistrationsRepo exportRegistrationsRepo;
	
	@Autowired
	ExportShopRegistrationsRepo exportShopRegistrationsRepo;
	
	@Autowired
	ExportVolunteerRegistrationsRepo exportVolunteerRegistrationsRepo;
	
	@Autowired
	ExportVolunteerPlanningRepo exportVolunteerPlanningRepo;

	@Autowired
	ExportProtozoneRegistrationsRepo exportProtozoneRegistrationsRepo;
	
	public void exportCSV(HttpServletResponse response, String queryName, Long eventId) throws Exception {

        //set file name and content type
        String filename = queryName + ".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExportRegistrations> writer = new StatefulBeanToCsvBuilder<ExportRegistrations>(response.getWriter())
                .withQuotechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withSeparator(';')
                .withOrderedResults(true)
                .build();

        //write all users to csv file
        writer.write(exportRegistrationsRepo.exportAllRegistrationsSQL(eventId));
				
    }
	
	public void exportShopCSV(HttpServletResponse response, String queryName, Long eventId) throws Exception {

        //set file name and content type
        String filename = queryName + ".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExportShopRegistrations> writer = new StatefulBeanToCsvBuilder<ExportShopRegistrations>(response.getWriter())
                .withQuotechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withSeparator(';')
                .withOrderedResults(true)
                .build();

        //write all users to csv file
        writer.write(exportShopRegistrationsRepo.exportAllShopRegistrationsSQL(eventId));
				
    }
	
	public void exportVolunteerCSV(HttpServletResponse response, String queryName, Long eventId) throws Exception {

        //set file name and content type
        String filename = queryName + ".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExportVolunteerRegistrations> writer = new StatefulBeanToCsvBuilder<ExportVolunteerRegistrations>(response.getWriter())
                .withQuotechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withSeparator(';')
                .withOrderedResults(true)
                .build();

        //write all users to csv file
        writer.write(exportVolunteerRegistrationsRepo.exportVolunteerAllRegistrationsSQL(eventId));
				
    }
	
	public void exportVolunteerPlanningCSV(HttpServletResponse response, String queryName, Long eventId) throws Exception {

        //set file name and content type
        String filename = queryName + ".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExportVolunteerPlanning> writer = new StatefulBeanToCsvBuilder<ExportVolunteerPlanning>(response.getWriter())
                .withQuotechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withSeparator(';')
                .withOrderedResults(true)
                .build();

        //write all users to csv file
        writer.write(exportVolunteerPlanningRepo.exportVolunteerPlanningSQL(eventId));
				
    }
	
	public void exportProtozoneCSV(HttpServletResponse response, String queryName, Long eventId) throws Exception {

        //set file name and content type
        String filename = queryName + ".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExportProtozoneRegistrations> writer = new StatefulBeanToCsvBuilder<ExportProtozoneRegistrations>(response.getWriter())
                .withQuotechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withSeparator(';')
                .withOrderedResults(true)
                .build();

        //write all users to csv file
        writer.write(exportProtozoneRegistrationsRepo.exportAllProtozoneRegistrationsSQL(eventId));
				
    }
}
