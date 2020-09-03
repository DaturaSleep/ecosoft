package com.vmaksym.ecosoft.repos;

import com.vmaksym.ecosoft.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
}
