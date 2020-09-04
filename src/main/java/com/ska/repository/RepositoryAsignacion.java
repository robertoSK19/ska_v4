package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.Asignacion;
@Repository
public interface RepositoryAsignacion extends JpaRepository<Asignacion, Long>{

}
