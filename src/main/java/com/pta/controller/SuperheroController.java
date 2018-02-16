package com.pta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pta.entity.Superhero;
import com.pta.service.SuperheroService;

@Controller
@RequestMapping("/superheroes")
public class SuperheroController {

	@Autowired
	SuperheroService superheroService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Superhero>> listOfAllSuperheroes() {
		return new ResponseEntity<>(superheroService.getAllSuperheroes(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Superhero> updateSuperhero(@RequestBody Superhero superhero) {
		return new ResponseEntity<>(superheroService.createOrUpdateSuperhero(superhero), HttpStatus.OK);
	}

	@RequestMapping(path= "/{name}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Superhero> getSuperheroByName(@PathVariable String name) {
		return new ResponseEntity<>(superheroService.findSuperheroByName(name), HttpStatus.OK);
	}
	
}
