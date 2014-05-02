package fr.nom.petat.domo.ihm.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.nom.petat.domo.bean.RelevesBean;
import fr.nom.petat.domo.service.MoteurService;
import fr.nom.petat.domo.service.ReleveService;

@Controller
@RequestMapping("/supervision")
public class SupervisionController {
	public Logger logger = Logger.getLogger(SupervisionController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printSupervision(ModelMap model) throws IOException, InterruptedException {
		logger.debug("D�but printSupervision");
		ReleveService releveService = new ReleveService();
		RelevesBean relevesBean = releveService.dernierReleve();
		
		model.addAttribute("releves", relevesBean);
		
        return "supervision";
	}
	
    public String demarrer(ModelMap model) throws IOException, InterruptedException {
		logger.debug("D�but demarrer");
        MoteurService moteurService = new MoteurService();
        moteurService.demarrer();
        
        return printSupervision(model);
    }

    public String arreter(ModelMap model) throws IOException, InterruptedException {
		logger.debug("D�but arreter");
        MoteurService moteurService = new MoteurService();
        moteurService.arreter();
        
        return printSupervision(model);
    }

    public String diminuer(ModelMap model) throws IOException, InterruptedException {
		logger.debug("D�but diminuer");
        MoteurService moteurService = new MoteurService();
        moteurService.diminuerVitesse(10);

    	return printSupervision(model);
    }

    public String augmenter(ModelMap model) throws IOException, InterruptedException {
		logger.debug("D�but augmenter");
        MoteurService moteurService = new MoteurService();
        moteurService.augmenterVitesse(10);
    	
        return printSupervision(model);
    }

    public String actualiser(Integer vitesseSaisie, ModelMap model) throws IOException, InterruptedException {
		logger.debug("D�but actualiser");
        MoteurService moteurService = new MoteurService();
        try {
        	moteurService.setVitesse(vitesseSaisie);
        } catch (Exception e) {
        	logger.error("Erreur de mise � jour de la vitesse", e);
        }
    	
        return printSupervision(model);
    }

	@RequestMapping(method = RequestMethod.POST)
    public String submit(@RequestParam(value="action", required=false) String action, 
    					 @RequestParam(value="vitesseSaisie", required=false) Integer vitesseSaisie, 
    					 ModelMap model) throws IOException, InterruptedException {
		logger.debug("D�but submit");
		logger.debug("Action = " + action);
		if ("demarrer".equals(action)) {
			return demarrer(model);
		} else if ("arreter".equals(action)) {
			return arreter(model);
		} else if ("diminuer".equals(action)) {
			return diminuer(model);
		} else if ("augmenter".equals(action)) {
			return augmenter(model);
		} else if ("actualiser".equals(action)) {
			return actualiser(vitesseSaisie, model);
		} else {
			return printSupervision(model);
		}
	}
}