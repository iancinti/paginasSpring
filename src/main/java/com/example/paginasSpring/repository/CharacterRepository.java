package com.example.paginasSpring.repository;

import com.example.paginasSpring.model.Character;
import com.example.paginasSpring.model.CharacterPaginationRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Repository
public class CharacterRepository {

    private final RestTemplate template;

    @Autowired
    public CharacterRepository(RestTemplate template) {
        this.template = template;
    }

    public List<Character> getAll() {

        ResponseEntity<CharacterPaginationRest> characters = template.exchange(
                "https://rickandmortyapi.com/api/character",
                HttpMethod.GET,
                null,
                CharacterPaginationRest.class
        );

        return characters.getBody().getResults();
    }
}
