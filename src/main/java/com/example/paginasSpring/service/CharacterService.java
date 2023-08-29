package com.example.paginasSpring.service;

import com.example.paginasSpring.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.paginasSpring.model.Character;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository repository;

    @Autowired
    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public List<Character> getAll() {
        return repository.getAll();
    }
}
