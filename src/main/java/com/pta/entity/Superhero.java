package com.pta.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "superheroes")
public class Superhero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	@JsonProperty(required=true)
	@NotEmpty
	private String name;
	private String pseudonym;
	private String publisher;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Powers", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "power")
	private Set<String> powers;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Allies", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "ally")
	private Set<String> allies;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date firstAppearance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Set<String> getPowers() {
		return powers;
	}

	public void setPowers(Set<String> powers) {
		this.powers = powers;
	}

	public Set<String> getAllies() {
		return allies;
	}

	public void setAllies(Set<String> allies) {
		this.allies = allies;
	}

	public Date getFirstAppearance() {
		return firstAppearance;
	}

	public void setFirstAppearance(Date firstAppearance) {
		this.firstAppearance = firstAppearance;
	}

	@Override
	public String toString() {
		return "Superhero [id=" + id + ", name=" + name + ", pseudonym=" + pseudonym + ", publisher=" + publisher
				+ ", powers=" + powers + ", allies=" + allies + ", firstAppearance=" + firstAppearance + "]";
	}

}
