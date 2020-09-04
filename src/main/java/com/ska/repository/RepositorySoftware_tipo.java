package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.Software_tipo;
@Repository
public interface RepositorySoftware_tipo extends JpaRepository<Software_tipo, Long>  {

}
