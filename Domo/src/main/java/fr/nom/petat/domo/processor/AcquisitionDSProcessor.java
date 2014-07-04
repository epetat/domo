package fr.nom.petat.domo.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.bean.TemperatureLoggerBean;
import fr.nom.petat.domo.service.TemperatureService;

public class AcquisitionDSProcessor {
	public Logger logger = Logger.getLogger(AcquisitionDSProcessor.class);
	
	@Autowired
	private TemperatureService temperatureService = null;
	
	/**
	 * Constitution des beans de relev�
	 * @param pExchange	L'�change Camel
	 */
	public void constitutionReleves(Exchange pExchange) {
		logger.debug("D�but constitutionReleve");
		List<ReleveTemperatureBean> listeReleveTemperatureBean = new ArrayList<ReleveTemperatureBean>();
		ReleveTemperatureBean releveTemperatureBean = null;
		Double sondeCheminee = null;
		Double sondeMelangee = null;
		
		// Obtention des temp�ratures
		try {
			sondeCheminee = temperatureService.lireTemperature(TemperatureLoggerBean.SONDE_CHEMINEE);
		} catch (Exception e) {
			logger.error("Impossible d'obtenir la temp�rature de la chemin�e",e);
		}
		try {
			sondeMelangee = temperatureService.lireTemperature(TemperatureLoggerBean.SONDE_INTERIEUR);
		} catch (Exception e) {
			logger.error("Impossible d'obtenir la temp�rature m�lang�e",e);
		}
		
		/**
		 * SONDE CHEMINEE
		 */
		releveTemperatureBean = new ReleveTemperatureBean();
		
		// Calcul de la date du releve
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		releveTemperatureBean.setDateReleve(calendar.getTime());
		
		// Temp�rature relev�e
		releveTemperatureBean.setTemperature(Double.valueOf(sondeCheminee));
		
		// Logger
		releveTemperatureBean.setTemperatureLogger(TemperatureLoggerBean.SONDE_CHEMINEE);
		
		listeReleveTemperatureBean.add(releveTemperatureBean);
		
		/**
		 * SONDE MELANGEE
		 */
		releveTemperatureBean = new ReleveTemperatureBean();
		
		// Calcul de la date du releve
		releveTemperatureBean.setDateReleve(calendar.getTime());
		
		// Temp�rature relev�e
		releveTemperatureBean.setTemperature(Double.valueOf(sondeMelangee));
		
		// Logger
		releveTemperatureBean.setTemperatureLogger(TemperatureLoggerBean.SONDE_INTERIEUR);
		
		listeReleveTemperatureBean.add(releveTemperatureBean);
		
		pExchange.getIn().setBody(listeReleveTemperatureBean);
		logger.debug("Fin constitutionReleve");
	}

	/**
	 * @param pTemperatureService the temperatureService to set
	 */
	public void setTemperatureService(TemperatureService pTemperatureService) {
		this.temperatureService = pTemperatureService;
	}
}
