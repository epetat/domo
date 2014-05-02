package fr.nom.petat.domo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelevesBean {
	private Date dateReleve;
	private List<ReleveTemperatureBean> temperatures;
	private Double rendementVmc;
	private EtatMoteurBean moteur;
	
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
	 * @return the temperatures
	 */
	public List<ReleveTemperatureBean> getTemperatures() {
		if (temperatures == null) {
			temperatures = new ArrayList<ReleveTemperatureBean>();
		}
		return temperatures;
	}
	
	/**
	 * @param pTemperatures the temperatures to set
	 */
	public void setTemperatures(List<ReleveTemperatureBean> pTemperatures) {
		this.temperatures = pTemperatures;
	}
	
	/**
	 * 
	 * @param pReleveTemperatureBean La température d'une sonde
	 */
	public void addTemperature(ReleveTemperatureBean pReleveTemperatureBean) {
		int i = getTemperatures().indexOf(pReleveTemperatureBean);
		if (i != -1) {
			getTemperatures().get(i).setTemperature(pReleveTemperatureBean.getTemperature());
		} else {
			getTemperatures().add(pReleveTemperatureBean);
		}
	}
	
	/**
	 * @return the moteur
	 */
	public EtatMoteurBean getMoteur() {
		return moteur;
	}

	/**
	 * @param pMoteur the moteur to set
	 */
	public void setMoteur(EtatMoteurBean pMoteur) {
		this.moteur = pMoteur;
	}

	/**
	 * @return the rendementVmc
	 */
	public Double getRendementVmc() {
		return rendementVmc;
	}

	/**
	 * @param pRendementVmc the rendementVmc to set
	 */
	public void setRendementVmc(Double pRendementVmc) {
		this.rendementVmc = pRendementVmc;
	}
}
