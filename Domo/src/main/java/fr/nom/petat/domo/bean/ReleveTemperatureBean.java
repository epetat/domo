package fr.nom.petat.domo.bean;

import java.util.Date;

public class ReleveTemperatureBean {
	/**
	 * Le logger de température
	 */
	private TemperatureLoggerBean temperatureLogger;
	
	/**
	 * Température
	 */
	private Double temperature;
	
	/**
	 * Date du relevé
	 */
	private Date dateReleve;
	
	/**
	 * Constructeur par défaut
	 */
	public ReleveTemperatureBean() {
		super();
	}

	/**
	 * Constructeur avec paramètres
	 * @param temperatureLogger
	 * @param temperature
	 * @param dateReleve
	 */
	public ReleveTemperatureBean(TemperatureLoggerBean pTemperatureLogger,
								 Double pTemperature, 
								 Date pDateReleve) {
		super();
		this.temperatureLogger = pTemperatureLogger;
		this.temperature = pTemperature;
		this.dateReleve = pDateReleve;
	}

	/**
	 * @return the temperature
	 */
	public Double getTemperature() {
		return temperature;
	}

	/**
	 * @param pTemperature the temperature to set
	 */
	public void setTemperature(Double pTemperature) {
		this.temperature = pTemperature;
	}

	/**
	 * @return the dateReleve
	 */
	public Date getDateReleve() {
		return dateReleve;
	}

	/**
	 * @param pDateReleve the dateReleve to set
	 */
	public void setDateReleve(Date pDateReleve) {
		this.dateReleve = pDateReleve;
	}

	/**
	 * @return the temperatureLogger
	 */
	public TemperatureLoggerBean getTemperatureLogger() {
		return temperatureLogger;
	}

	/**
	 * @param pTemperatureLogger the temperatureLogger to set
	 */
	public void setTemperatureLogger(TemperatureLoggerBean pTemperatureLogger) {
		this.temperatureLogger = pTemperatureLogger;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((temperatureLogger == null) ? 0 : temperatureLogger
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReleveTemperatureBean other = (ReleveTemperatureBean) obj;
		if (temperatureLogger == null) {
			if (other.temperatureLogger != null)
				return false;
		} else if (!temperatureLogger.equals(other.temperatureLogger))
			return false;
		return true;
	}
	
}
