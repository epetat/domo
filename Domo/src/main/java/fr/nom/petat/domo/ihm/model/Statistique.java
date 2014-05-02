package fr.nom.petat.domo.ihm.model;

import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class Statistique {
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@Past
	private Date dateDebut = null;

	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date dateFin = null;
	
	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	
	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	
	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
}
