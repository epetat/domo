package fr.nom.petat.domo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

public class AcquisitionDSRoute extends RouteBuilder {
	public Logger logger = Logger.getLogger(AcquisitionDSRoute.class);
	
	@Override
	public void configure() throws Exception {
		logger.debug("Init AcquisitionDSRoute");
		
		from("timer:automate?period=60000")
		.beanRef("acquisitionDSProcessor", "constitutionReleves")
		.split(body())
			.to("direct:constitutionReleveRoute");
	}

}
