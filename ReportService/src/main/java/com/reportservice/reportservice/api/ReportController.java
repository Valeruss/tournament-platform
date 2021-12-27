package com.reportservice.reportservice.api;

import com.reportservice.reportservice.repo.model.Report;
import com.reportservice.reportservice.api.dto.ReportDTO;
import com.reportservice.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viewObject.ResponseTemplate;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public final class ReportController {

    public final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Report>> index() {
        final List<Report> reports = reportService.fetchAll();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> show(@PathVariable long id) {
        try {
            final Report report = reportService.fetchById(id);
            return ResponseEntity.ok(report);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReportDTO report) {
        final Integer tournament_id = report.getTournament_id();
        final Integer violator_id = report.getViolator_id();
        final String description = report.getDescription();
        final String decision = report.getDecision();

        final long id = reportService.create(tournament_id, violator_id, description, decision);
        final String location = String.format("/reports/%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody ReportDTO report) {
        final Integer tournament_id = report.getTournament_id();
        final Integer violator_id = report.getViolator_id();
        final String description = report.getDescription();
        final String decision = report.getDecision();

        try {
            reportService.update(id, tournament_id, violator_id, description, decision);
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reportService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view/{id}")
    public ResponseTemplate getReportWithInfo(@PathVariable long id) {
        return reportService.getReportWithInfo(id);
    }
}
