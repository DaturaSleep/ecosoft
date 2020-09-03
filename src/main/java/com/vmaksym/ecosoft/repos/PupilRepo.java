package com.vmaksym.ecosoft.repos;

import com.vmaksym.ecosoft.entities.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PupilRepo extends JpaRepository<Pupil, Integer> {
    Pupil findPupilByUserName(String userName);
}
