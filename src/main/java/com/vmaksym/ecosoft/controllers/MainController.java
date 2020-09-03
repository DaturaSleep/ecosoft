package com.vmaksym.ecosoft.controllers;

import com.vmaksym.ecosoft.entities.ApplicationUser;
import com.vmaksym.ecosoft.repos.PupilRepo;
import com.vmaksym.ecosoft.repos.TeacherRepo;
import com.vmaksym.ecosoft.security.jwtUtil.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PupilRepo pupilRepo;

    @Autowired
    TeacherRepo teacherRepo;

    /**
     * Accessible by the ADMIN and the Teacher.
     * Used to obtain personal information of the teacher and his pupils.
     * (When logged as ADMIN will retrieve a blank form)
     *
     * @param token - jwt token to obtain UserDetail
     * @return logged Techer form
     */
    @GetMapping(value = "/teacher")
    public ApplicationUser teacherPoint(@RequestHeader(value = "Authorization") String token) {
        return teacherRepo.findTeacherByUserName(extractUserNameFromToken(token));
    }

    /**
     * Accessible by the ADMIN and the PUPIL.
     * Used to obtain personal information of the pupil.
     * (When logged as ADMIN will retrieve a blank form)
     *
     * @param token - jwt token to obtain UserDetail
     * @return logged Pupil form
     */
    @GetMapping(value = "/pupil")
    public ApplicationUser getPupilInfo(@RequestHeader(value = "Authorization") String token) {
        return pupilRepo.findPupilByUserName(extractUserNameFromToken(token));
    }

    /**
     * Accessible only by the ADMIN.
     * Used to find info about pupil by his ID
     *
     * @param id - Pupil id
     * @return - JSON form of the pupil
     */
    @GetMapping(value = "/pupil/{id}")
    public ApplicationUser getPupilInfoById(@PathVariable Integer id) {
        return pupilRepo.findById(id).get();
    }

    /**
     * Accessible only by the ADMIN.
     * Used to find info about teacher by his ID
     *
     * @param id - Teacher id
     * @return - JSON form of the teacher
     */
    @GetMapping(value = "/teacher/{id}")
    public ApplicationUser getTeacherInfoById(@PathVariable Integer id) {
        return teacherRepo.findById(id).get();
    }


    private String extractUserNameFromToken(String token) {
        return jwtUtil.extractUsername(token.replaceFirst("Bearer ", ""));
    }
}
