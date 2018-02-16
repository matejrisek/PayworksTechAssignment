package com.pta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pta.entity.Superhero;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {
	
	Superhero findByName(String name);
}
