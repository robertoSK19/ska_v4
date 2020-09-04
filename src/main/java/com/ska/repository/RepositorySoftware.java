package com.ska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ska.entity.Software;
@Repository
public interface RepositorySoftware extends JpaRepository<Software, Long>{

}
