package com.bm.company.demoapp.service;

import java.util.UUID;

import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Note;

public interface NotesService {
	
	Note createNote(String content);
	
	Note updateNote(UUID id, String content) throws ResourceNotFoundException;

}
