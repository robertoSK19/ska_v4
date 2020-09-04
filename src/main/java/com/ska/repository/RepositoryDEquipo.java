package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.DEquipo;
@Repository
public interface RepositoryDEquipo extends JpaRepository<DEquipo, Long>{

}
