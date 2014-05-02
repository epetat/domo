package fr.nom.petat.domo.service;

public class VmcService {
	/**
	 * Calcule le rendement de la VMC
	 * @param pTemperatureEntrant	La temp�rature de l'air venant de l'ext�rieur
	 * @param pTemperatureInsufle	La temp�rature de l'air insufl� dans la maison
	 * @param pTemperatureAspire	La temp�rature de l'air aspir� dans la maison
	 * @return	Le rendement
	 */
	public Double calculerRendement(Double pTemperatureEntrant, 
									Double pTemperatureInsufle, 
									Double pTemperatureAspire) {
		Double rendement = null;
		if (pTemperatureAspire != null && pTemperatureEntrant != null && pTemperatureInsufle != null) {
			rendement = 100 * (pTemperatureInsufle - pTemperatureEntrant) / (pTemperatureAspire - pTemperatureEntrant);
		}
		return rendement;
	}
}
