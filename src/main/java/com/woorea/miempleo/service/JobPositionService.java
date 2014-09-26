package com.woorea.miempleo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woorea.miempleo.domain.JobPosition;
import com.woorea.miempleo.repository.JobPositionRepository;
import com.woorea.miempleo.rest.dto.JobPositionPagedList;

@Service
public class JobPositionService {

	@Autowired
	private JobPositionRepository repositiory;

	@Transactional
	public JobPosition save(JobPosition jobPosition) {
		return repositiory.save(jobPosition);
	}

	@Transactional(readOnly = true)
	public JobPositionPagedList findAll(int pageNumber, int size) {
		Page<JobPosition> page = repositiory.findAll(new PageRequest(pageNumber, size));
		JobPositionPagedList jobPositionPagedList = new JobPositionPagedList();
		jobPositionPagedList.setList(page.getContent());
		jobPositionPagedList.setTotal(page.getTotalElements());
		return jobPositionPagedList;
	}

	@Transactional(readOnly = true)
	public JobPosition findOne(String jobPositionId) {
		return repositiory.findOne(jobPositionId);
	}

	@Transactional
	public void update(JobPosition jobPosition) {
		repositiory.save(jobPosition);
		
	}
	
	@Transactional
	public void delete(String id) {
		repositiory.delete(id);
		
	}
	
}
