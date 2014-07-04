package fr.nom.petat.domo.bean;

public class VmcBean {
	private Double rendementVmc;
	private ReleveTemperatureBean temperatureEntrant;
	private ReleveTemperatureBean temperatureSortant;
	private ReleveTemperatureBean temperatureInsufle;
	private ReleveTemperatureBean temperatureInsufleCourt;
	private ReleveTemperatureBean temperatureInsufleLong;
	private ReleveTemperatureBean temperatureAspire;
	
	public Double getRendementVmc() {
		return rendementVmc;
	}
	public void setRendementVmc(Double rendementVmc) {
		this.rendementVmc = rendementVmc;
	}
	public ReleveTemperatureBean getTemperatureEntrant() {
		return temperatureEntrant;
	}
	public void setTemperatureEntrant(ReleveTemperatureBean temperatureEntrant) {
		this.temperatureEntrant = temperatureEntrant;
	}
	public ReleveTemperatureBean getTemperatureSortant() {
		return temperatureSortant;
	}
	public void setTemperatureSortant(ReleveTemperatureBean temperatureSortant) {
		this.temperatureSortant = temperatureSortant;
	}
	public ReleveTemperatureBean getTemperatureInsufle() {
		return temperatureInsufle;
	}
	public void setTemperatureInsufle(ReleveTemperatureBean temperatureInsufle) {
		this.temperatureInsufle = temperatureInsufle;
	}
	public ReleveTemperatureBean getTemperatureAspire() {
		return temperatureAspire;
	}
	public void setTemperatureAspire(ReleveTemperatureBean temperatureAspire) {
		this.temperatureAspire = temperatureAspire;
	}
	/**
	 * @return the temperatureInsufleCourt
	 */
	public ReleveTemperatureBean getTemperatureInsufleCourt() {
		return temperatureInsufleCourt;
	}
	/**
	 * @param temperatureInsufleCourt the temperatureInsufleCourt to set
	 */
	public void setTemperatureInsufleCourt(
			ReleveTemperatureBean temperatureInsufleCourt) {
		this.temperatureInsufleCourt = temperatureInsufleCourt;
	}
	/**
	 * @return the temperatureInsufleLong
	 */
	public ReleveTemperatureBean getTemperatureInsufleLong() {
		return temperatureInsufleLong;
	}
	/**
	 * @param temperatureInsufleLong the temperatureInsufleLong to set
	 */
	public void setTemperatureInsufleLong(
			ReleveTemperatureBean temperatureInsufleLong) {
		this.temperatureInsufleLong = temperatureInsufleLong;
	}
}
