package fr.nom.petat.domo.processor;

import java.util.Calendar;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.log4j.Logger;

import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.bean.TemperatureLoggerBean;

public class AcquisitionArexxProcessor {
	public Logger logger = Logger.getLogger(AcquisitionArexxProcessor.class);

	/**
	 * Extraction des propri�t�s pr�sentes dans le body sous la forme "cle=valeur"
	 * et placement de la valeur dans le header du m�me nom que la propri�t�
	 * @param pBody		Le body contenant les propri�t�s
	 * @param pExchange	L'exchange Camel
	 */
	public void extractProprietes(@Body String pBody, Exchange pExchange) {
		logger.debug("D�but extractProprietes");
		String[] proprietes = pBody.split("&");
		for (String proprieteStr : proprietes) {
			if (proprieteStr.indexOf("=") > 0) {
				String[] propriete = proprieteStr.split("=");
				pExchange.getIn().setHeader(propriete[0], propriete[1]);
			}
		}
		logger.debug("Fin extractProprietes");
	}
	
	public void constitutionReleve(@Header("time") String pTime,
								   @Header("v") String pValeur,
								   @Header("id") String pId,
								   Exchange pExchange) {
		logger.debug("D�but constitutionReleve");
		logger.debug("Time = " + pTime);
		logger.debug("Valeur = " + pValeur);
		logger.debug("ID = " + pId);
		ReleveTemperatureBean releveTemperatureBean = new ReleveTemperatureBean();
		// D�termination du logger
		releveTemperatureBean.setTemperatureLogger(TemperatureLoggerBean.getTemperatureLogger(pId));
		// Calcul de la date du releve
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		releveTemperatureBean.setDateReleve(calendar.getTime());
		// Temp�rature relev�e
		releveTemperatureBean.setTemperature(Double.valueOf(pValeur.replaceAll("%2E", ".")));
		
		pExchange.getIn().setBody(releveTemperatureBean);
		logger.debug("Fin constitutionReleve");
	}
}
