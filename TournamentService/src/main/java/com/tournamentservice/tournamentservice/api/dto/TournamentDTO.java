package com.tournamentservice.tournamentservice.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class TournamentDTO {
    private String name;
    private String description;
    private Integer size;
}
