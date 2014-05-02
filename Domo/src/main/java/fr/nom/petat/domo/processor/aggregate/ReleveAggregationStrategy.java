package fr.nom.petat.domo.processor.aggregate;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.log4j.Logger;

import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.bean.RelevesBean;

public class ReleveAggregationStrategy implements AggregationStrategy {
	public Logger logger = Logger.getLogger(ReleveAggregationStrategy.class);

	@Override
	public Exchange aggregate(Exchange pOldExchange, Exchange pNewExchange) {
		logger.debug("Début aggregate");
		ReleveTemperatureBean releveTemperatureBean = null;
		releveTemperatureBean = pNewExchange.getIn().getBody(ReleveTemperatureBean.class);

		if (pOldExchange == null) {
			RelevesBean releveBean = new RelevesBean();
			releveBean.setDateReleve(releveTemperatureBean.getDateReleve());
			releveBean.addTemperature(releveTemperatureBean);
			pNewExchange.getIn().setBody(releveBean);
			return pNewExchange;
		}
		
		pOldExchange.getIn().getBody(RelevesBean.class).addTemperature(releveTemperatureBean);
		logger.debug("fin aggregate");
		return pOldExchange;
	}

}
