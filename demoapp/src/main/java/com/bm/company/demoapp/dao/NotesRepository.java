package com.bm.company.demoapp.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.company.demoapp.models.Note;

public interface NotesRepository extends JpaRepository<Note, UUID>{
	
}
