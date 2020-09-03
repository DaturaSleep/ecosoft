package com.vmaksym.ecosoft;

import com.vmaksym.ecosoft.entities.Admin;
import com.vmaksym.ecosoft.entities.Pupil;
import com.vmaksym.ecosoft.entities.Teacher;
import com.vmaksym.ecosoft.repos.AdminRepo;
import com.vmaksym.ecosoft.repos.PupilRepo;
import com.vmaksym.ecosoft.repos.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class EcosoftApplication {

    @Autowired
    PupilRepo pupilRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    AdminRepo adminRepo;


    public static void main(String[] args) {
        SpringApplication.run(EcosoftApplication.class, args);
    }

    @PostConstruct
    public void initTestUsers() {

        Admin admin = new Admin(1, "admin", "admin");
        adminRepo.save(admin);

        Pupil pupil1 = new Pupil(2, "pupil", "pupil");
        pupilRepo.save(pupil1);

        Teacher teacher = new Teacher(3, "teacher", "teacher");
        teacher.addPupil(pupil1);
        teacherRepo.save(teacher);

        pupilRepo.save(pupil1);

        teacherRepo.save(teacher);

        Pupil pupil2 = new Pupil(4, "pupil2", "pupil2");
        pupilRepo.save(pupil2);
    }

}
