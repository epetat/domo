package fr.nom.petat.domo.ihm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.nom.petat.domo.ihm.model.Statistique;

@Controller
@RequestMapping("/statistique")
public class StatistiqueController {
	private final static String MODEL_ATTRIBUTE = "statistique";
	private final static String VUE_STATISTIQUE = "statistique";
	public Logger logger = Logger.getLogger(StatistiqueController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printStatistique(Model pModel) throws IOException, InterruptedException {
		logger.debug("Début printStatistique");
		Calendar calendar = Calendar.getInstance();
		Statistique statistique = new Statistique();
		statistique.setDateFin(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		statistique.setDateDebut(calendar.getTime());
		pModel.addAttribute(MODEL_ATTRIBUTE, statistique);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		pModel.addAttribute("dateDebut", sdf.format(statistique.getDateDebut()));
		pModel.addAttribute("dateFin", sdf.format(statistique.getDateFin()));
    	
        return VUE_STATISTIQUE;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String submit(Statistique pStatistique, BindingResult pResult, Model pModel) throws IOException, InterruptedException {
		logger.debug("Début submit");
		if (pResult.hasErrors()) {
			return "statistique";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		pModel.addAttribute("dateDebut", sdf.format(pStatistique.getDateDebut()));
		pModel.addAttribute("dateFin", sdf.format(pStatistique.getDateFin()));

		return VUE_STATISTIQUE;
	}
}