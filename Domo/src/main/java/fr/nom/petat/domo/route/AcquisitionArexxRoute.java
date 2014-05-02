package fr.nom.petat.domo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

public class AcquisitionArexxRoute extends RouteBuilder {
	public Logger logger = Logger.getLogger(AcquisitionArexxRoute.class);
	
	@Override
	public void configure() throws Exception {
		logger.debug("Init AcquisitionArexxRoute");
		
		onException(Exception.class)
		.to("log:fr.nom.petat.domo.route.AcquisitionArexxRoute?level=ERROR&showHeaders=true");
		
		from("jetty:http://0.0.0.0:49161")
		.to("log:fr.nom.petat.domo.route.AcquisitionArexxRoute?level=DEBUG&showHeaders=true")
		.beanRef("acquisitionArexxProcessor", "extractProprietes")
		.beanRef("acquisitionArexxProcessor", "constitutionReleve")
		.to("direct:constitutionReleveRoute");
	}

}
