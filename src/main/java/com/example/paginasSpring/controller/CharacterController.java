package com.example.paginasSpring.controller;

import com.example.paginasSpring.model.Character;
import com.example.paginasSpring.service.CharacterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final Logger logger = LogManager.getLogger(CharacterController.class);
    private final CharacterService service;

    @Autowired
    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Character> getAllCharacters(){
        logger.info("Obteniendo personajes de Rick y Morty");
        List<Character> characters = service.getAll();
        logger.info("Obteniendo personajes de Rick y Morty");
        return characters;
    }
}
