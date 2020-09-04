package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.Accesorio;
@Repository
public interface RepositoryAccesorio extends JpaRepository<Accesorio, Long>{

}
