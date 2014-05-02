package fr.nom.petat.domo.ihm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.nom.petat.domo.chart.JFreeChartTemperature;

@Controller
@RequestMapping("/charts")
public class ChartTemperatureController {
	public Logger logger = Logger.getLogger(ChartTemperatureController.class);

	@RequestMapping(value = "/temperature", method = RequestMethod.GET)
	public void drawTemperature(@RequestParam(value="dateDebut") String pDateDebut, 
								@RequestParam(value="dateFin") String pDateFin, 
								HttpServletResponse pResponse) {
		logger.debug("Début printSupervision");
		logger.debug("Date de début = " + pDateDebut);
		logger.debug("Date de fin = " + pDateFin);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		pResponse.setContentType("image/png");
		
		try {
			Date dateDebut = null;
			try {
				dateDebut = sdf.parse(pDateDebut);
			} catch (Exception e) {}
			Date dateFin = null;
			try {
				dateFin = sdf.parse(pDateFin);
			} catch (Exception e) {}
			JFreeChartTemperature.createCombinedChart(dateDebut, dateFin, pResponse.getOutputStream());
		} catch (IOException e) {
			logger.error("Erreur génération graphique", e);
		}
		logger.debug("Fin printSupervision");
	}
	
}
