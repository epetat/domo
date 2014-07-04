package fr.nom.petat.domo.ihm.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.nom.petat.domo.bean.RelevesBean;
import fr.nom.petat.domo.service.MoteurService;
import fr.nom.petat.domo.service.ReleveService;

@Controller
@RequestMapping("/supervision")
public class SupervisionController {
	public Logger logger = Logger.getLogger(SupervisionController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printSupervision(ModelMap model) throws IOException, InterruptedException {
		logger.debug("Début printSupervision");
		ReleveService releveService = new ReleveService();
		RelevesBean relevesBean = releveService.dernierReleve();
		
		model.addAttribute("releves", relevesBean);
		
        return "supervision";
	}
	
	@RequestMapping("/demarrer")
    public String demarrer(ModelMap model) throws IOException, InterruptedException {
		logger.debug("Début demarrer");
        MoteurService moteurService = new MoteurService();
        moteurService.demarrer();
        
        return printSupervision(model);
    }

	@RequestMapping("/arreter")
    public String arreter(ModelMap model) throws IOException, InterruptedException {
		logger.debug("Début arreter");
        MoteurService moteurService = new MoteurService();
        moteurService.arreter();
        
        return printSupervision(model);
    }

	@RequestMapping("/diminuer")
    public String diminuer(ModelMap model) throws IOException, InterruptedException {
		logger.debug("Début diminuer");
        MoteurService moteurService = new MoteurService();
        moteurService.diminuerVitesse(10);

    	return printSupervision(model);
    }

	@RequestMapping("/augmenter")
    public String augmenter(ModelMap model) throws IOException, InterruptedException {
		logger.debug("Début augmenter");
        MoteurService moteurService = new MoteurService();
        moteurService.augmenterVitesse(10);
    	
        return printSupervision(model);
    }

	@RequestMapping("/actualiser")
    public String actualiser(Integer vitesseSaisie, ModelMap model) throws IOException, InterruptedException {
		logger.debug("Début actualiser");
        MoteurService moteurService = new MoteurService();
        try {
        	moteurService.setVitesse(vitesseSaisie);
        } catch (Exception e) {
        	logger.error("Erreur de mise à jour de la vitesse", e);
        }
    	
        return printSupervision(model);
    }
}