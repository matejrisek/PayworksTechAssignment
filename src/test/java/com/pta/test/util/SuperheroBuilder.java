package com.pta.test.util;

import java.util.Date;
import java.util.Set;

import com.pta.entity.Superhero;

public class SuperheroBuilder {

	private String name;
	private String pseudonym;
	private String publisher;
	private Set<String> powers;
	private Set<String> allies;
	private Date firstAppearance;
	
	public SuperheroBuilder(String name) {
		this.name = name;
	}
	
	public SuperheroBuilder pseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
		return this;
	}
	
	public SuperheroBuilder publisher(String publisher) {
		this.publisher = publisher;
		return this;
	}
	
	public SuperheroBuilder powers(Set<String> powers) {
		this.powers = powers;
		return this;
	}
	
	public SuperheroBuilder allies(Set<String> allies) {
		this.allies = allies;
		return this;
	}
	
	public SuperheroBuilder firstAppearance(Date firstAppearance) {
		this.firstAppearance = firstAppearance;
		return this;
	}
	
	public Superhero build() {
		Superhero superhero = new Superhero();
		superhero.setName(this.name);
		superhero.setPseudonym(this.pseudonym);
		superhero.setPublisher(this.publisher);
		superhero.setPowers(this.powers);
		superhero.setAllies(this.allies);
		superhero.setFirstAppearance(this.firstAppearance);
		return superhero;
	}
}
