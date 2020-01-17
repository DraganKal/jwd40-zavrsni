package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Model za entitet sprint

@Entity
public class Sprint {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String ime;
	@Column
	private int ukupnoBodova;
	
	@OneToMany(mappedBy="sprint", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Zadatak> zadaci = new ArrayList<>();
	
	public Sprint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sprint(String ime, int ukupnoBodova) {
		super();
		this.ime = ime;
		this.ukupnoBodova = ukupnoBodova;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getUkupnoBodova() {
		return ukupnoBodova;
	}
	public void setUkupnoBodova(int ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
	}
	public List<Zadatak> getZadaci() {
		return zadaci;
	}
	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}
	public void addZadatak(Zadatak zadatak) {
		if(zadatak.getSprint() != this) {
			zadatak.setSprint(this);
		}
		zadaci.add(zadatak);
	}
	

}
