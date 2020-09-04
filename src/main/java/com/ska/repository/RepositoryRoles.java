package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.Roles;
@Repository
public interface RepositoryRoles extends JpaRepository<Roles, Long>{

}
