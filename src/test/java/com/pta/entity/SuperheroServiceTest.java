package com.pta.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pta.service.SuperheroService;
import com.pta.test.util.SuperheroBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperheroServiceTest {

	@Autowired
	private SuperheroService superheroService;

	@Before
	public void init() {
		// Load some test data into in-memory DB
		createSuperman();
	}

	private void createSuperman() {
		// @formatter:off
		Superhero superman = new SuperheroBuilder("Superman")
				.publisher("DC")
				.powers(new HashSet<String>(Arrays.asList("flight", "super strength", "super speed")))
				.allies(Collections.singleton("Batman"))
				.build();
		// @formatter:on
		superheroService.createOrUpdateSuperhero(superman);
	}

	@Test
	public void shouldReturnSupermanWhenFindSuperheroByNameSuperman() {
		Superhero superhero = superheroService.findSuperheroByName("Superman");
		assertEquals("Superman", superhero.getName());
	}
	
	@Test
	public void shouldReturnBatmanBeforeChangedAlliesAndAquamanWhenChangedAllies() {
		Superhero superhero = superheroService.findSuperheroByName("Superman");
		assertThat(superhero.getAllies(), CoreMatchers.hasItems("Batman"));
		
		superhero.setAllies(Collections.singleton("Aquaman"));
		superheroService.createOrUpdateSuperhero(superhero);

		superhero = superheroService.findSuperheroByName("Superman");
		assertThat(superhero.getAllies(), CoreMatchers.hasItems("Aquaman"));
	}
	
}
