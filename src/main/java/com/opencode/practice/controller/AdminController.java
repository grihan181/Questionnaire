package com.opencode.practice.controller;

import com.opencode.practice.projection.Statistics;
import com.opencode.practice.exception.NoSuchCountExeption;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import com.opencode.practice.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Grihan , Artem
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<String> createQuestionary(@RequestBody Questionnaire questionary) {
        logger.info("Работа метода createQuestionary");
        adminService.addQuestionnaire(questionary);
        return new ResponseEntity<>("Анкета создалась", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<String> deleteQuestionaty(@PathVariable long id) {
        logger.info("Работа метода deleteQuestionaty");

        adminService.deleteQuestionnaireById(id);
        return new ResponseEntity<>("Анкета удалилась", HttpStatus.OK);

    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<String> editQuestionaty(@PathVariable long id, @RequestBody Questionnaire questionnaire) {
        logger.info("Работа метода editQuestionaty");
        adminService.editQuestionnaire(id, questionnaire);
        return new ResponseEntity<>("Анкета едит", HttpStatus.OK);

    }

    @GetMapping("getusers")
    @PreAuthorize("hasAuthority('developers:write')")
    public List<User> findAllUsers() {
        return adminService.findAllUsers();
    }

    @GetMapping("findUsersStatistics/{questionFirstId}/{questionSecondId}")
    @PreAuthorize("hasAuthority('developers:write')")
    public List<Statistics> findUsersStatistics(@PathVariable long questionFirstId, @PathVariable long questionSecondId) {
        return adminService.getUsersStatistics(questionFirstId, questionSecondId);
    }
}
