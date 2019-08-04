package com.bm.company.demoapp.web.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bm.company.demoapp.constants.CommonConstants;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Note;
import com.bm.company.demoapp.service.NotesService;

@RestController
@RequestMapping("/notes")
@CrossOrigin
(origins = CommonConstants.CLIENT_BASE_URI, maxAge = 3600)
public class NotesController extends BaseController {
	
	@Autowired
	private NotesService notesService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotesController.class);
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Note> updateCustomerStatus( @PathVariable UUID id, @RequestBody Note noteToUpdate) throws ResourceNotFoundException {
		LOGGER.info("Updating note {}", id);
		Note updatedNote = notesService.updateNote(id, noteToUpdate.getContent());
		return new ResponseEntity<>( updatedNote, HttpStatus.OK);
	}

}
