package fr.nom.petat.domo.bean;

import java.util.Date;

public class RelevesBean {
	private Date dateReleve;
	private ReleveTemperatureBean temperatureCheminee;
	private ReleveTemperatureBean temperatureInterieur;
	private VmcBean vmc;
	private MoteurBean moteur;
	
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
	 * @return the moteur
	 */
	public MoteurBean getMoteur() {
		return moteur;
	}

	/**
	 * @param pMoteur the moteur to set
	 */
	public void setMoteur(MoteurBean pMoteur) {
		this.moteur = pMoteur;
	}

	/**
	 * @return the vmc
	 */
	public VmcBean getVmc() {
		if (vmc == null) {
			vmc = new VmcBean();
		}
		return vmc;
	}

	/**
	 * @param vmc the vmc to set
	 */
	public void setVmc(VmcBean vmc) {
		this.vmc = vmc;
	}

	/**
	 * @return the temperatureCheminee
	 */
	public ReleveTemperatureBean getTemperatureCheminee() {
		return temperatureCheminee;
	}

	/**
	 * @param temperatureCheminee the temperatureCheminee to set
	 */
	public void setTemperatureCheminee(ReleveTemperatureBean temperatureCheminee) {
		this.temperatureCheminee = temperatureCheminee;
	}

	/**
	 * @return the temperatureInterieur
	 */
	public ReleveTemperatureBean getTemperatureInterieur() {
		return temperatureInterieur;
	}

	/**
	 * @param temperatureInterieur the temperatureInterieur to set
	 */
	public void setTemperatureInterieur(ReleveTemperatureBean temperatureInterieur) {
		this.temperatureInterieur = temperatureInterieur;
	}
}
