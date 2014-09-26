package com.woorea.miempleo.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.woorea.miempleo.Application;
import com.woorea.miempleo.domain.JobPosition;
import com.woorea.miempleo.service.JobPositionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class JobServiceTest {

	@Autowired
	private JobPositionService service;
	
	@Test
	public void clear() {
		service.findAll(0,  10);
	}
	
}
