package com.tournamentservice.tournamentservice.service;

import com.tournamentservice.tournamentservice.repo.TournamentRepo;
import com.tournamentservice.tournamentservice.repo.model.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class TournamentService {

    private final TournamentRepo tournamentRepo;

//    private final String

    public List<Tournament> fetchAll() {
        return tournamentRepo.findAll();
    }

    public Tournament fetchById(long id) throws IllegalArgumentException {
        final Optional<Tournament> maybeTournament = tournamentRepo.findById(id);

        if(maybeTournament.isEmpty()) throw new IllegalArgumentException("Tournament not found");
        else return maybeTournament.get();
    }

    public long create(String name, String description, Integer size) {
        final Tournament tournament = new Tournament(name, description, size);
        final Tournament savedTournament = tournamentRepo.save(tournament);

        return savedTournament.getId();
    }

    public void update(long id, String name, String description, Integer size) throws IllegalArgumentException {
        final Optional<Tournament> maybeTournament = tournamentRepo.findById(id);

        if (maybeTournament.isEmpty()) throw new IllegalArgumentException("Tournament not found");

        final Tournament tournament = maybeTournament.get();
        if (name != null && !name.isBlank()) tournament.setName(name);
        if (description != null && !description.isBlank()) tournament.setDescription(description);
        if (size != null) tournament.setSize(size);

        tournamentRepo.save(tournament);
    }

    public void delete(long id) {
        tournamentRepo.deleteById(id);
    }
}
