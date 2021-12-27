package com.tournamentservice.tournamentservice.api;

import com.tournamentservice.tournamentservice.repo.model.Tournament;
import com.tournamentservice.tournamentservice.api.dto.TournamentDTO;
import com.tournamentservice.tournamentservice.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments")
public final class TrnController {

//    @Bean
//    public RestTemplate getRestTemplate() { return new RestTemplate(); }
//
//    @Autowired
//    private RestTemplate restTemplate;
//
////    @GetMapping("/users/{user_id}")
//    private String getUserName(final long user_id) {
//        ResponseEntity<String> userNick = null;
//        try {
//            userNick = restTemplate.exchange(
//                    String.format("http://localhost:8081/users/{user_id}", user_id),
//                    HttpMethod.GET,
//                    null,
//                    String.class);
//            return userNick.getBody();
//
//        } catch (final HttpClientErrorException.NotFound e) {
//            return null;
//        }
//    }


    public final TournamentService tournamentService;

    // ==

//    @GetMapping("/users/{user_id}")
//    public ResponseEntity<List<Tournament>> getUser(@PathVariable long user_id) {
//        final List<Tournament> tournaments = tournamentService.fetchById(user_id);
//    }
    // ==

    @GetMapping
    public ResponseEntity<List<Tournament>> index() {
        final List<Tournament> tournaments = tournamentService.fetchAll();
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> show(@PathVariable long id) {
        try {
            final Tournament tournament = tournamentService.fetchById(id);
            return ResponseEntity.ok(tournament);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TournamentDTO tournament) {
        final String name = tournament.getName();
        final String description = tournament.getDescription();
        final Integer size = tournament.getSize();
        final long id = tournamentService.create(name, description, size);
        final String location = String.format("/tournaments/%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody TournamentDTO tournament) {
        final String name = tournament.getName();
        final String description = tournament.getDescription();
        final Integer size = tournament.getSize();

        try {
            tournamentService.update(id, name, description, size);
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
