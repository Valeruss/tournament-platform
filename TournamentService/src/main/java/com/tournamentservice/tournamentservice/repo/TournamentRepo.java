package com.tournamentservice.tournamentservice.repo;

import com.tournamentservice.tournamentservice.repo.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Long> {

}
