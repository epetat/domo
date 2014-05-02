package fr.nom.petat.domo.service;

import org.apache.log4j.Logger;

import fr.nom.petat.domo.bean.EtatMoteurBean;
import fr.nom.petat.domo.bean.MoteurControllerBean;

public class MoteurService {
	public Logger logger = Logger.getLogger(MoteurService.class);

	public void demarrer() {
		logger.debug("Début demarrer");
		logger.debug("Etat actif = " + MoteurControllerBean.getInstance().getEtatCourant().isMoteurActif());
		if (!MoteurControllerBean.getInstance().getEtatCourant().isMoteurActif()) {
			MoteurControllerBean.getInstance().demarrer();
		}
		logger.debug("Fin demarrer");
	}
	
	public void arreter() {
		logger.debug("Début arreter");
		logger.debug("Etat actif = " + MoteurControllerBean.getInstance().getEtatCourant().isMoteurActif());
		if (MoteurControllerBean.getInstance().getEtatCourant().isMoteurActif()) {
			MoteurControllerBean.getInstance().arreter();
		}
		logger.debug("Fin arreter");
	}
	
	public void augmenterVitesse(int pValeur) {
		logger.debug("Début augmenterVitesse : Valeur = " + pValeur);
		MoteurControllerBean.getInstance().augmenterVitesse(pValeur);
		if (MoteurControllerBean.getInstance().getEtatCourant().getVitesse() > 0) {
			demarrer();
		}
		logger.debug("Fin augmenterVitesse");
	}

	public void diminuerVitesse(int pValeur) {
		logger.debug("Début diminuerVitesse : Valeur = " + pValeur);
		MoteurControllerBean.getInstance().diminuerVitesse(pValeur);
		if (MoteurControllerBean.getInstance().getEtatCourant().getVitesse() == 0) {
			arreter();
		}
		logger.debug("Fin diminuerVitesse");
	}
	
	public void setVitesse(int pValeur) {
		MoteurControllerBean.getInstance().setVitesse(pValeur);
		if (MoteurControllerBean.getInstance().getEtatCourant().getVitesse() == 0) {
			arreter();
		} else {
			demarrer();
		}
	}
	
	public int getVitesse() {
		return MoteurControllerBean.getInstance().getEtatCourant().getVitesse();
	}

	public boolean isMoteurActif() {
		return MoteurControllerBean.getInstance().getEtatCourant().isMoteurActif();
	}
	
	public EtatMoteurBean getCopieEtatMoteur() {
		EtatMoteurBean etatMoteur = new EtatMoteurBean(MoteurControllerBean.getInstance().getEtatCourant().getVitesse(), 
													   MoteurControllerBean.getInstance().getEtatCourant().isMoteurActif());
		return etatMoteur;
	}
	
	/**
	 * Détermination automatique de l'état du moteur à partir des températures
	 * @param pTemeratureCheminee		La température de la cheminée
	 * @param pTemperatureInsufleCourt	La température d'insuflation par le circuit court
	 * @param pTemperatureInsufleLong	La température d'insuflation par le circuit long
	 */
	public void determinationAutomatique(Double pTemeratureCheminee, Double pTemperatureInsufleCourt, Double pTemperatureInsufleLong) {
		// Calcul de la température minimum d'insuflation
		Double sondeVmcInsufleMini = null;
		if (pTemperatureInsufleCourt != null && pTemperatureInsufleLong != null) {
			sondeVmcInsufleMini = Math.min(pTemperatureInsufleCourt, pTemperatureInsufleLong);
		} else if (pTemperatureInsufleCourt != null) {
			sondeVmcInsufleMini = pTemperatureInsufleCourt;
		} else if (pTemperatureInsufleLong != null) {
			sondeVmcInsufleMini = pTemperatureInsufleLong;
		}
		
		// Détermination de l'état du moteur
		if (pTemeratureCheminee != null && sondeVmcInsufleMini != null) {
			if (pTemeratureCheminee.doubleValue() > 20) {
				logger.info("Température cheminée > 20°");
				if (22 - sondeVmcInsufleMini.doubleValue() > .5) {
					logger.info("Augmentation de la vitesse");
					demarrer();
					augmenterVitesse(1);
				} else if (22 - sondeVmcInsufleMini.doubleValue() < -.5) {
					logger.info("Diminution de la vitesse");
					demarrer();
					diminuerVitesse(1);
				}
			} else {
				logger.info("Température cheminée <= 20°");
				arreter();
			}
		}
	}
}
