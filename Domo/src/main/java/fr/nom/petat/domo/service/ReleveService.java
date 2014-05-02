package fr.nom.petat.domo.service;

import fr.nom.petat.domo.bean.RelevesBean;
import fr.nom.petat.domo.dao.ReleveDao;

public class ReleveService {
	private static RelevesBean DERNIER_RELEVE = null;
	
	public void sauvegarde(RelevesBean pReleves) {
		ReleveDao releveDao = new ReleveDao();
		releveDao.sauvegarde(pReleves);
		
		DERNIER_RELEVE = pReleves;
	}
	
	/**
	 * Obtenir le dernier relevé
	 * @return Le relevé
	 */
	public RelevesBean dernierReleve() {
		RelevesBean dernierReleve = DERNIER_RELEVE;
		if (dernierReleve == null) {
			ReleveDao releveDao = new ReleveDao();
			dernierReleve = releveDao.lireDernierReleve();
		}
		return dernierReleve;
	}
}
