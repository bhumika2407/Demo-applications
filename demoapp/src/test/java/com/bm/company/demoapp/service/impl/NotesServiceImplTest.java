package com.bm.company.demoapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bm.company.demoapp.dao.NotesRepository;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Note;

class NotesServiceImplTest {
	
	
	
	@InjectMocks
	NotesServiceImpl notesService;
	
	@Mock
	NotesRepository notesRepository;
	
	private Note note;
	private UUID id;
	private UUID invalidId;
	private String content;
	
	@BeforeEach
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		id =  UUID.fromString("86eacaa0-9e5e-4558-950b-a7892d35f05c");
		invalidId = UUID.fromString("86eacaa0-9e5e-4558-950b-a7892d35f05e");
		content = "This is a test note";
		note = new Note();
		note.setContent(content);
		note.setStatus(Boolean.TRUE);
		note.setCreationTime(new Date());
		note.setModifiedTime(new Date());
	}
	
	
	@Test
	@DisplayName("Test update note")
	void testUpdateNote() {
		when(notesRepository.findById(id)).thenReturn( Optional.of( note ));
		when(notesRepository.findById(invalidId)).thenReturn( Optional.empty());
		when(notesRepository.save(any(Note.class))).thenReturn( note );
		
		assertAll( () -> assertEquals(note, notesService.updateNote(id, content), " failed for updating valid note"),
				() -> assertThrows(ResourceNotFoundException.class, () -> notesService.updateNote(invalidId, content), "failed for updating not existing note"));
		
	}

}
