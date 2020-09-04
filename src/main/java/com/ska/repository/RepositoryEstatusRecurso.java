package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.EstatusRecurso;
@Repository
public interface RepositoryEstatusRecurso extends JpaRepository<EstatusRecurso, Long>{

}
