package com.woorea.miempleo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.woorea.miempleo.domain.JobPosition;
import com.woorea.miempleo.rest.dto.JobPositionPagedList;
import com.woorea.miempleo.service.JobPositionService;

@RestController
@RequestMapping("/v1/jobs")
public class JobPositionController {
	
	@Autowired
	private JobPositionService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public JobPositionPagedList list() {
		
		return service.findAll(0, 10);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public HttpEntity<Void> save(@RequestBody JobPosition jobPosition) {
		jobPosition = service.save(jobPosition);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{jobPositionId}").buildAndExpand(jobPosition.getId()).encode().toUri());
		return new HttpEntity<>(headers);
		
	}
	
	@RequestMapping(value = "/{jobPositionId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public JobPosition show(@PathVariable String jobPositionId) {
		return service.findOne(jobPositionId);
	}
	
	@RequestMapping(value = "/{jobPositionId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable String jobPositionId, @RequestBody JobPosition jobPosition) {
		jobPosition.setId(jobPositionId);
		service.update(jobPosition);
	}
	
	@RequestMapping(value = "/{jobPositionId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String jobPositionId) {
		service.delete(jobPositionId);
	}
	
}
