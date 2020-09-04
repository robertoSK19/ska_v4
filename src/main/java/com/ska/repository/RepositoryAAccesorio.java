package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.AAccesorio;
@Repository
public interface RepositoryAAccesorio extends JpaRepository<AAccesorio, Long>{

}
