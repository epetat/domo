package fr.nom.petat.domo.processor;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.bean.RelevesBean;
import fr.nom.petat.domo.bean.TemperatureLoggerBean;
import fr.nom.petat.domo.service.MoteurService;
import fr.nom.petat.domo.service.ReleveService;
import fr.nom.petat.domo.service.VmcService;

public class ConstitutionReleveProcessor {
	public Logger logger = Logger.getLogger(ConstitutionReleveProcessor.class);
	
	private MoteurService moteurService = null;
	private ReleveService releveService = null;
	private VmcService vmcService = null;

	/**
	 * D�termination automatique de l'�tat du moteur et ajout du nouvel �tat dans le relev�
	 * @param pReleve	Les relev�s de temp�ratures
	 * @param pExchange	L'exchange Camel
	 */
	public void ajoutMoteur(@Body RelevesBean pReleve, Exchange pExchange) {
		logger.debug("D�but ajoutMoteur");
		Double sondeCheminee = null;
		Double sondeVmcInsufleCourt = null;
		Double sondeVmcInsufleLong = null;
		
		// R�cup�ration de temp�ratures
		for (ReleveTemperatureBean releveTemperatureBean : pReleve.getTemperatures()) {
			if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_CHEMINEE)) {
				sondeCheminee = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE_COURT)) {
				sondeVmcInsufleCourt = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE_LONG)) {
				sondeVmcInsufleLong = releveTemperatureBean.getTemperature();
			}
		}
		
		moteurService.determinationAutomatique(sondeCheminee, sondeVmcInsufleCourt, sondeVmcInsufleLong);
		
		// Renseignement de l'�tat du moteur pour sauvegarde
		pReleve.setMoteur(moteurService.getCopieEtatMoteur());
		logger.debug("Fin ajoutMoteur");
	}
	
	/**
	 * Calcul du rendement de la VMC
	 * @param pReleve	Les relev�s de temp�rature
	 * @param pExchange	L'�change Camel
	 */
	public void calculRendementVmc(@Body RelevesBean pReleve, Exchange pExchange) {
		Double temperatureVmcEntrant = null;
		Double temperatureVmcAspire = null;
		Double temperatureVmcInsufle = null;
		
		// R�cup�ration de temp�ratures
		for (ReleveTemperatureBean releveTemperatureBean : pReleve.getTemperatures()) {
			if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_ENTRANT)) {
				temperatureVmcEntrant = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_ASPIRE)) {
				temperatureVmcAspire = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE)) {
				temperatureVmcInsufle = releveTemperatureBean.getTemperature();
			}
		}
		
		// Calcul du rendement
		pReleve.setRendementVmc(vmcService.calculerRendement(temperatureVmcEntrant, temperatureVmcInsufle, temperatureVmcAspire));
	}
	
	/**
	 * Sauvegarde des relev�s de temp�rature et de l'�tat du moteur
	 * @param pReleves	Les relev�s
	 * @param pExchange	L'�change Camel
	 */
	public void sauvegarde(@Body RelevesBean pReleves, Exchange pExchange) {
		logger.debug("D�but sauvegarde");
		releveService.sauvegarde(pReleves);
		logger.debug("fin sauvegarde");
	}

	/**
	 * @param pMoteurService the moteurService to set
	 */
	public void setMoteurService(MoteurService pMoteurService) {
		this.moteurService = pMoteurService;
	}

	/**
	 * @param pReleveService the releveService to set
	 */
	public void setReleveService(ReleveService pReleveService) {
		this.releveService = pReleveService;
	}

	/**
	 * @param pVmcService the vmcService to set
	 */
	public void setVmcService(VmcService pVmcService) {
		this.vmcService = pVmcService;
	}
}
