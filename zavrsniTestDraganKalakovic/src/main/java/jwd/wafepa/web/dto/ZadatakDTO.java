package jwd.wafepa.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ZadatakDTO {
	
	private Long id;
	@NotBlank
	@Size(max = 40)
	private String ime;
	
	private String zaduzeni;
	@Min(0)
	@Max(20)
	private int bodovi;
	
	
	private Long sprintId;
	private String sprintNaziv;
	
	
	private Long stanjeId;
	private String stanjeNaziv;
	public ZadatakDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getZaduzeni() {
		return zaduzeni;
	}
	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}
	public int getBodovi() {
		return bodovi;
	}
	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}
	public Long getSprintId() {
		return sprintId;
	}
	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}
	public String getSprintNaziv() {
		return sprintNaziv;
	}
	public void setSprintNaziv(String sprintNaziv) {
		this.sprintNaziv = sprintNaziv;
	}
	public Long getStanjeId() {
		return stanjeId;
	}
	public void setStanjeId(Long stanjeId) {
		this.stanjeId = stanjeId;
	}
	public String getStanjeNaziv() {
		return stanjeNaziv;
	}
	public void setStanjeNaziv(String stanjeNaziv) {
		this.stanjeNaziv = stanjeNaziv;
	}
	
	

}
