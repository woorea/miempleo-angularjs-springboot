package com.woorea.miempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woorea.miempleo.domain.JobPosition;

public interface JobPositionRepository extends JpaRepository<JobPosition, String> { 

}
