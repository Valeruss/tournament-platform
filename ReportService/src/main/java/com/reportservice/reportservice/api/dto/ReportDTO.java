package com.reportservice.reportservice.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportDTO {
    private Integer tournament_id;
    private Integer violator_id;
    private String description;
    private String decision;
}
