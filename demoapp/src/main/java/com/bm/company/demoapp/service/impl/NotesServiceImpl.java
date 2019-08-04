package com.bm.company.demoapp.service.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.company.demoapp.dao.NotesRepository;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Note;
import com.bm.company.demoapp.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class); 
	
	@Autowired
	NotesRepository notesRepository;
	
	@Override
	public Note createNote( final String content ) {
		LOGGER.debug("Creating a note");
		
		Note note = new Note();
		note.setContent(content);
		note.setStatus(Boolean.TRUE);
		note.setCreationTime(new Date());
		note.setModifiedTime(new Date());
		
		note = notesRepository.save( note );
		LOGGER.debug("Created note {} successfully", note.getId());
		return note;
	}

	@Override
	public Note updateNote(final UUID id, final String content) throws ResourceNotFoundException {
		Note note = notesRepository.findOne(id);
		if( note == null) {
			throw new ResourceNotFoundException(" Note for the provided id does not exist");
		}
		note.setContent(content);
		note.setModifiedTime(new Date());
		return notesRepository.save( note );
	}


}
