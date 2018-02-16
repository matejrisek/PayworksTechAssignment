package com.pta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.pta.entity.Superhero;
import com.pta.repository.SuperheroRepository;

@Service
public class SuperheroService {

	@Autowired
	SuperheroRepository superheroRepository;

	public List<Superhero> getAllSuperheroes() {
		return superheroRepository.findAll();
	}
	
	public Superhero findSuperheroByName(String name) {
		return superheroRepository.findByName(name);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Superhero createOrUpdateSuperhero(Superhero superhero) {
		Superhero existingSuperhero = superheroRepository.findByName(superhero.getName());
		if (existingSuperhero != null) {
			superhero.setId(existingSuperhero.getId());
		}
		return superheroRepository.save(superhero);
	}
	
}
