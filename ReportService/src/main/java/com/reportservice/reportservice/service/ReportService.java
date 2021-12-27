package com.reportservice.reportservice.service;

import com.reportservice.reportservice.repo.ReportRepo;
import com.reportservice.reportservice.repo.model.Report;
import lombok.RequiredArgsConstructor;
//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import viewObject.ResponseTemplate;
import viewObject.Tournament;
import viewObject.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepo reportRepo;

//    private final RestTemplate restTemplate;

    public List<Report> fetchAll() {return reportRepo.findAll(); }

    public Report fetchById(long id) throws  IllegalArgumentException {
        final Optional<Report> maybeReport = reportRepo.findById(id);

        if (maybeReport.isEmpty()) throw new IllegalArgumentException("Report not found");
        else return maybeReport.get();
    }

    public long create(Integer tournament_id, Integer violator_id, String description, String decision) {
        final Report report = new Report(tournament_id, violator_id, description, decision);
        final Report savedReport = reportRepo.save(report);

        return savedReport.getId();
    }

    public void update(long id, Integer tournament_id, Integer violator_id, String description, String decision)
        throws IllegalArgumentException {
        final Optional<Report> maybeReport = reportRepo.findById(id);

        if (maybeReport.isEmpty()) throw new IllegalArgumentException("Report not found");

        final Report report = maybeReport.get();
        if (tournament_id != null) report.setTournament_id(tournament_id);
        if (violator_id != null) report.setViolator_id(violator_id);
        if (description != null && !description.isBlank()) report.setDescription(description);
        if (decision != null && !decision.isBlank()) report.setDecision(decision);

        reportRepo.save(report);
    }

    public void delete(long id) { reportRepo.deleteById(id); }


    public ResponseTemplate getReportWithInfo(long id) {
        ResponseTemplate viewObject = new ResponseTemplate();
        final Report report;
        final Optional<Report> maybeReport = reportRepo.findById(id);
        if(maybeReport.isEmpty()) throw new IllegalArgumentException("Report not found");
        else {

            RestTemplate restTemplate = new RestTemplate();

            report = maybeReport.get();

            String userURL = "http://localhost:8081/users/";
            final User user = restTemplate.getForObject(userURL + report.getViolator_id(), User.class);

            String tournamentURL = "http://localhost:8080/tournaments/";
            final Tournament tournament = restTemplate.getForObject(tournamentURL + report.getTournament_id(), Tournament.class);

            viewObject.setUser(user);
            viewObject.setTournament(tournament);
            viewObject.setReport(report);
            return viewObject;
        }
    }
}
