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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class JobRepositoryTest {

	@Autowired
	private JobPositionRepository repository;
	
	@Before
	public void clear() {
		repository.deleteAll();
	}
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0, repository.findAll().size());
	}
	
	@Test
	public void testSave() {
		JobPosition jPosition = new JobPosition();
		jPosition.setTitle("Java Developer");
		jPosition = repository.save(jPosition);
		Assert.assertNotNull(jPosition.getId());
	}
	
	
	@Test
	public void testUpdate() {
		JobPosition jPosition = new JobPosition();
		jPosition.setTitle("Java Developer");
		jPosition = repository.save(jPosition);
		jPosition.setTitle("AngularJS Developer");
		repository.save(jPosition);
		List<JobPosition> jPositions = repository.findAll();
		Assert.assertEquals(1, jPositions.size());
		Assert.assertEquals("AngularJS Developer", jPositions.get(0).getTitle());
	}
	
}
