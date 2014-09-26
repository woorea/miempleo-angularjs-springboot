package com.woorea.miempleo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExternalizedConfigurationTest {
	
	@Autowired
	private Environment environment;

	@Value("${mytestproperty}")
	private String myTestProperty;
	
	@Value("${miempleo.title}")
	private String title;
	
	@Test
	public void test() {
		Assert.assertEquals("10", myTestProperty);
		Assert.assertEquals("10", environment.getProperty("mytestproperty"));
		
		Assert.assertEquals("Mi empleo", title);
		Assert.assertEquals("Mi empleo", environment.getProperty("miempleo.title"));
		
	}
	
}
