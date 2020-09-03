package com.vmaksym.ecosoft.repos;

import com.vmaksym.ecosoft.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {
    Teacher findTeacherByUserName(String userName);
}
