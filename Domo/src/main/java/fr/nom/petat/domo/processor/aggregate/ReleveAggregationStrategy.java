package fr.nom.petat.domo.processor.aggregate;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.log4j.Logger;

import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.bean.RelevesBean;
import fr.nom.petat.domo.bean.TemperatureLoggerBean;

public class ReleveAggregationStrategy implements AggregationStrategy {
	public Logger logger = Logger.getLogger(ReleveAggregationStrategy.class);

	@Override
	public Exchange aggregate(Exchange pOldExchange, Exchange pNewExchange) {
		logger.debug("D�but aggregate");
		ReleveTemperatureBean releveTemperatureBean = null;
		releveTemperatureBean = pNewExchange.getIn().getBody(ReleveTemperatureBean.class);

		if (pOldExchange == null) {
			RelevesBean releveBean = new RelevesBean();
			releveBean.setDateReleve(releveTemperatureBean.getDateReleve());
			ajouterTemperature(releveBean, releveTemperatureBean);
			pNewExchange.getIn().setBody(releveBean);
			return pNewExchange;
		}
		
		ajouterTemperature(pOldExchange.getIn().getBody(RelevesBean.class), releveTemperatureBean);
		logger.debug("fin aggregate");
		return pOldExchange;
	}

	/**
	 * Ajoute une temp�rature au bean de relev�
	 * @param pRelevesBean				Le bean de relev�
	 * @param pReleveTemperatureBean	La temp�rature
	 */
	private void ajouterTemperature(RelevesBean pRelevesBean, ReleveTemperatureBean pReleveTemperatureBean) {
		if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_CHEMINEE)) {
			pRelevesBean.setTemperatureCheminee(pReleveTemperatureBean);
		} else if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_INTERIEUR)) {
			pRelevesBean.setTemperatureInterieur(pReleveTemperatureBean);
		} else if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_ASPIRE)) {
			pRelevesBean.getVmc().setTemperatureAspire(pReleveTemperatureBean);
		} else if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_ENTRANT)) {
			pRelevesBean.getVmc().setTemperatureEntrant(pReleveTemperatureBean);
		} else if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE)) {
			pRelevesBean.getVmc().setTemperatureInsufle(pReleveTemperatureBean);
		} else if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE_COURT)) {
			pRelevesBean.getVmc().setTemperatureInsufleCourt(pReleveTemperatureBean);
		} else if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE_LONG)) {
			pRelevesBean.getVmc().setTemperatureInsufleLong(pReleveTemperatureBean);
		} else if (pReleveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_SORTANT)) {
			pRelevesBean.getVmc().setTemperatureSortant(pReleveTemperatureBean);
		}
	}

}
