package fr.nom.petat.domo.bean;

import org.jfree.data.xy.IntervalXYDataset;

public class SeriesTemperatureBean {
	private IntervalXYDataset temperatureCheminee;
	private IntervalXYDataset temperatureMelangee;
	/**
	 * @return the temperatureCheminee
	 */
	public IntervalXYDataset getTemperatureCheminee() {
		return temperatureCheminee;
	}
	/**
	 * @param temperatureCheminee the temperatureCheminee to set
	 */
	public void setTemperatureCheminee(IntervalXYDataset temperatureCheminee) {
		this.temperatureCheminee = temperatureCheminee;
	}
	/**
	 * @return the temperatureMelangee
	 */
	public IntervalXYDataset getTemperatureMelangee() {
		return temperatureMelangee;
	}
	/**
	 * @param temperatureMelangee the temperatureMelangee to set
	 */
	public void setTemperatureMelangee(IntervalXYDataset temperatureMelangee) {
		this.temperatureMelangee = temperatureMelangee;
	}
}
