package fr.nom.petat.domo.bean;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

public class MoteurControllerBean {
	private GpioPinDigitalOutput moteur = null;
	private static final int GPIO_VITESSE = RaspiPin.GPIO_05.getAddress();
	private MoteurBean etatCourant = new MoteurBean();
	private int statusVitesse = -1;
	
	private static MoteurControllerBean INSTANCE = null;
	
	private MoteurControllerBean() {}
	
	public static synchronized MoteurControllerBean getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MoteurControllerBean();
		}
		return INSTANCE;
	}
	
	private GpioPinDigitalOutput getMoteur() {
		if (moteur == null) {
			moteur = GpioFactory.getInstance().provisionDigitalOutputPin(RaspiPin.GPIO_04, "Moteur");
		}
		return moteur;
	}
	
	/**
	 * Démarrer le moteur
	 */
	public void demarrer() {
		getMoteur().low();
		etatCourant.setMoteurActif(true);
	}

	/**
	 * Arreter le moteur
	 */
	public void arreter() {
		getMoteur().high();
		etatCourant.setMoteurActif(false);
	}

	public void setVitesse(int pValeur) {
		if (statusVitesse != 0) {
			statusVitesse = SoftPwm.softPwmCreate(GPIO_VITESSE, 0, 100);
		}

		etatCourant.setVitesse(pValeur);
		if (etatCourant.getVitesse() > 100) {
			etatCourant.setVitesse(100);
		} else if (etatCourant.getVitesse() < 0) {
			etatCourant.setVitesse(0);
		}

		SoftPwm.softPwmWrite(GPIO_VITESSE, etatCourant.getVitesse());
	}
	
	public void augmenterVitesse(int pValeur) {
		if (statusVitesse != 0) {
			statusVitesse = SoftPwm.softPwmCreate(GPIO_VITESSE, 0, 100);
		}

		etatCourant.setVitesse(etatCourant.getVitesse() + pValeur);
		if (etatCourant.getVitesse() > 100) {
			etatCourant.setVitesse(100);
		}

		SoftPwm.softPwmWrite(GPIO_VITESSE, etatCourant.getVitesse());
	}
	
	public void diminuerVitesse(int pValeur) {
		if (statusVitesse != 0) {
			statusVitesse = SoftPwm.softPwmCreate(GPIO_VITESSE, 0, 100);
		}

		etatCourant.setVitesse(etatCourant.getVitesse() - pValeur);
		if (etatCourant.getVitesse() < 0) {
			etatCourant.setVitesse(100);
		}

		SoftPwm.softPwmWrite(GPIO_VITESSE, etatCourant.getVitesse());
	}

	/**
	 * @return the etatCourant
	 */
	public MoteurBean getEtatCourant() {
		return etatCourant;
	}

	/**
	 * @param pEtatCourant the etatCourant to set
	 */
	public void setEtatCourant(MoteurBean pEtatCourant) {
		this.etatCourant = pEtatCourant;
	}
}
