package com.woorea.miempleo.rest;

import java.net.URI;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.woorea.miempleo.Application;
import com.woorea.miempleo.domain.JobPosition;
import com.woorea.miempleo.rest.dto.JobPositionPagedList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class JobPositionControllerTest {

	private TestRestTemplate rest = new TestRestTemplate();
	
	@Before
	public void clear() {
		
		JobPositionPagedList pagedList = rest.getForObject("http://localhost:8080/v1/jobs", JobPositionPagedList.class);
		for(JobPosition jPosition : pagedList.getList()) {
			rest.delete("http://localhost:8080/v1/jobs/{jobPositionId}", jPosition.getId());
		}
		
	}
	
	@Test
	public void testCreateAndShow() {
		
		JobPosition jPosition = new JobPosition();
		jPosition.setTitle("Java Developer");
		
		URI location = rest.postForLocation("http://localhost:8080/v1/jobs", jPosition);
		
		Assert.assertNotNull(location);
		
		jPosition = rest.getForObject(location, JobPosition.class);
		Assert.assertNotNull(jPosition);
		Assert.assertNotNull(jPosition.getId());
		Assert.assertEquals(jPosition.getTitle(), "Java Developer");
		
	}
	
	@Test
	public void testCreateAndUpdateAndShow() {
		
		JobPosition jPosition = new JobPosition();
		jPosition.setTitle("Java Developer");
		
		URI location = rest.postForLocation("http://localhost:8080/v1/jobs", jPosition);
		
		jPosition = rest.getForObject(location, JobPosition.class);
		jPosition.setTitle("AngularJS Developer");
		
		rest.put(location, jPosition);
		
		jPosition = rest.getForObject(location, JobPosition.class);
		Assert.assertEquals(jPosition.getTitle(), "AngularJS Developer");
		
	}
	
}
