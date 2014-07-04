package fr.nom.petat.domo.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TemperatureLoggerBean {
	private static List<TemperatureLoggerBean> listeTemperatureLogger = null;
	
	public static TemperatureLoggerBean SONDE_CHEMINEE 			= new TemperatureLoggerBean("Cheminée", TypeSonde.DS18B20, "28-00000520709c");
	public static TemperatureLoggerBean SONDE_INTERIEUR 		= new TemperatureLoggerBean("Intérieur", TypeSonde.DS18B20, "28-0000052104cf");
	public static TemperatureLoggerBean SONDE_VMC_ENTRANT 		= new TemperatureLoggerBean("VMC entrant", TypeSonde.AREXX, "8515");
	public static TemperatureLoggerBean SONDE_VMC_SORTANT 		= new TemperatureLoggerBean("VMC sortant", TypeSonde.AREXX, "8444");
	public static TemperatureLoggerBean SONDE_VMC_INSUFLE 		= new TemperatureLoggerBean("VMC insuflé", TypeSonde.AREXX, "8345");
	public static TemperatureLoggerBean SONDE_VMC_ASPIRE 		= new TemperatureLoggerBean("VMC aspiré", TypeSonde.AREXX, "8592");
	public static TemperatureLoggerBean SONDE_VMC_INSUFLE_COURT = new TemperatureLoggerBean("VMC insuflé court", TypeSonde.AREXX, "11503");
	public static TemperatureLoggerBean SONDE_VMC_INSUFLE_LONG 	= new TemperatureLoggerBean("VMC insuflé long", TypeSonde.AREXX, "11800");
	
	public static enum TypeSonde {
		DS18B20,
		AREXX
	}
	
	/**
	 * Nom de la sonde
	 */
	private String nom;
	/**
	 * Type de sonde
	 */
	private TypeSonde typeSonde;
	/**
	 * Référence de l'équipement
	 */
	private String device;
	
	public TemperatureLoggerBean(String pNom, TypeSonde pTypeSonde, String pDevice) {
		nom = pNom;
		typeSonde = pTypeSonde;
		device = pDevice;
		getListeTemperatureLogger().add(this);
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param pNom the nom to set
	 */
	public void setNom(String pNom) {
		this.nom = pNom;
	}

	/**
	 * @return the typeSonde
	 */
	public TypeSonde getTypeSonde() {
		return typeSonde;
	}

	/**
	 * @param pTypeSonde the typeSonde to set
	 */
	public void setTypeSonde(TypeSonde pTypeSonde) {
		this.typeSonde = pTypeSonde;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param pDevice the device to set
	 */
	public void setDevice(String pDevice) {
		this.device = pDevice;
	}

	/**
	 * Recherche un logger en fonction de son identifiant device
	 * @param pDevice	Le device
	 * @return Le logger de température
	 */
	public static TemperatureLoggerBean getTemperatureLogger(String pDevice) {
		TemperatureLoggerBean temperatureLoggerBean = null;
		Iterator<TemperatureLoggerBean> iterator = getListeTemperatureLogger().iterator();
		while (temperatureLoggerBean == null && iterator.hasNext()) {
			TemperatureLoggerBean temperatureLoggerBean2 = iterator.next();
			if (temperatureLoggerBean2.device.equals(pDevice)) {
				temperatureLoggerBean = temperatureLoggerBean2;
			}
		}
		return temperatureLoggerBean;
	}

	/**
	 * @return the listeTemperatureLogger
	 */
	public static List<TemperatureLoggerBean> getListeTemperatureLogger() {
		if (listeTemperatureLogger == null) {
			listeTemperatureLogger = new ArrayList<TemperatureLoggerBean>();
		}
		return listeTemperatureLogger;
	}
}
