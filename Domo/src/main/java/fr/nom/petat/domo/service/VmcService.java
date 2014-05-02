package fr.nom.petat.domo.service;

public class VmcService {
	/**
	 * Calcule le rendement de la VMC
	 * @param pTemperatureEntrant	La température de l'air venant de l'extérieur
	 * @param pTemperatureInsufle	La température de l'air insuflé dans la maison
	 * @param pTemperatureAspire	La température de l'air aspiré dans la maison
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
