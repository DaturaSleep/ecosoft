package com.vmaksym.ecosoft.repos;

import com.vmaksym.ecosoft.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, Integer> {

    ApplicationUser findApplicationUserByUserName(String userName);
}
