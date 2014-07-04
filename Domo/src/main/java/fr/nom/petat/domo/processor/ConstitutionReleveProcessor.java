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
	 * D�termination automatique de l'�tat du moteur et ajout du nouvel �tat dans le relev�
	 * @param pReleve	Les relev�s de temp�ratures
	 * @param pExchange	L'exchange Camel
	 */
	public void ajoutMoteur(@Body RelevesBean pReleve, Exchange pExchange) {
		logger.debug("D�but ajoutMoteur");
		
		moteurService.determinationAutomatique(pReleve.getTemperatureCheminee().getTemperature(), 
											   pReleve.getVmc().getTemperatureInsufleCourt().getTemperature(), 
											   pReleve.getVmc().getTemperatureInsufleLong().getTemperature());
		
		// Renseignement de l'�tat du moteur pour sauvegarde
		pReleve.setMoteur(moteurService.getCopieMoteur());
		logger.debug("Fin ajoutMoteur");
	}
	
	/**
	 * Calcul du rendement de la VMC
	 * @param pReleve	Les relev�s de temp�rature
	 * @param pExchange	L'�change Camel
	 */
	public void calculRendementVmc(@Body RelevesBean pReleve, Exchange pExchange) {
		VmcBean vmc = pReleve.getVmc();
		
		// Calcul du rendement
		pReleve.getVmc().setRendementVmc(vmcService.calculerRendement(vmc.getTemperatureEntrant().getTemperature(), vmc.getTemperatureInsufle().getTemperature(), vmc.getTemperatureAspire().getTemperature()));
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
