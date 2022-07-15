package com.opencode.practice.controller;

import com.opencode.practice.projection.Statistics;
import com.opencode.practice.exception.NoSuchCountExeption;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import com.opencode.practice.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createQuestionary(@RequestBody Questionnaire questionary) {
        logger.info("Работа метода createQuestionary");
        adminService.addQuestionnaire(questionary);
        throw new NoSuchCountExeption("Анкета создалась");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteQuestionaty(@PathVariable long id) {
        logger.info("Работа метода deleteQuestionaty");

        adminService.deleteQuestionnaireById(id);
        throw new NoSuchCountExeption("Анкета удалилась");

    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void editQuestionaty(@PathVariable long id, @RequestBody Questionnaire questionnaire) {
        logger.info("Работа метода editQuestionaty");
        adminService.editQuestionnaire(id, questionnaire);
        throw new NoSuchCountExeption("Анкета едит");

    }

    @GetMapping("getUsers")
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
