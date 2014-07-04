package fr.nom.petat.domo.processor;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.bean.RelevesBean;
import fr.nom.petat.domo.bean.TemperatureLoggerBean;
import fr.nom.petat.domo.bean.VmcBean;
import fr.nom.petat.domo.service.MoteurService;
import fr.nom.petat.domo.service.ReleveService;
import fr.nom.petat.domo.service.VmcService;

public class ConstitutionReleveProcessor {
	public Logger logger = Logger.getLogger(ConstitutionReleveProcessor.class);
	
	@Autowired
	private MoteurService moteurService = null;
	@Autowired
	private ReleveService releveService = null;
	@Autowired
	private VmcService vmcService = null;

	/**
	 * Détermination automatique de l'état du moteur et ajout du nouvel état dans le relevé
	 * @param pReleve	Les relevés de températures
	 * @param pExchange	L'exchange Camel
	 */
	public void ajoutMoteur(@Body RelevesBean pReleve, Exchange pExchange) {
		logger.debug("Début ajoutMoteur");
		
		moteurService.determinationAutomatique(pReleve.getTemperatureCheminee().getTemperature(), 
											   pReleve.getVmc().getTemperatureInsufleCourt().getTemperature(), 
											   pReleve.getVmc().getTemperatureInsufleLong().getTemperature());
		
		// Renseignement de l'état du moteur pour sauvegarde
		pReleve.setMoteur(moteurService.getCopieMoteur());
		logger.debug("Fin ajoutMoteur");
	}
	
	/**
	 * Calcul du rendement de la VMC
	 * @param pReleve	Les relevés de température
	 * @param pExchange	L'échange Camel
	 */
	public void calculRendementVmc(@Body RelevesBean pReleve, Exchange pExchange) {
		VmcBean vmc = pReleve.getVmc();
		
		// Calcul du rendement
		pReleve.getVmc().setRendementVmc(vmcService.calculerRendement(vmc.getTemperatureEntrant().getTemperature(), vmc.getTemperatureInsufle().getTemperature(), vmc.getTemperatureAspire().getTemperature()));
	}
	
	/**
	 * Sauvegarde des relevés de température et de l'état du moteur
	 * @param pReleves	Les relevés
	 * @param pExchange	L'échange Camel
	 */
	public void sauvegarde(@Body RelevesBean pReleves, Exchange pExchange) {
		logger.debug("Début sauvegarde");
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
