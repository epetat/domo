package fr.nom.petat.domo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.processor.aggregate.ReleveAggregationStrategy;

public class ConstitutionReleveRoute extends RouteBuilder {
	public Logger logger = Logger.getLogger(ConstitutionReleveRoute.class);
	
	@Override
	public void configure() throws Exception {
		logger.debug("Init ConstitutionReleveRoute");
		
		from("direct:constitutionReleveRoute")
		.convertBodyTo(ReleveTemperatureBean.class)
		.setHeader("dateReleve", simple("${body.dateReleve}"))
		.aggregate(header("dateReleve"), new ReleveAggregationStrategy()).completionTimeout(70000)
		.beanRef("constitutionReleveProcessor", "ajoutMoteur")
		.beanRef("constitutionReleveProcessor", "calculRendementVmc")
		.beanRef("constitutionReleveProcessor", "sauvegarde");
	}

}
