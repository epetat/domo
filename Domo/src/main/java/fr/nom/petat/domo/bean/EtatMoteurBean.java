package fr.nom.petat.domo.bean;


public class EtatMoteurBean {
	private int vitesse = 0;
	private boolean moteurActif = false;
	/**
	 * Constructeur par d�faut
	 */
	public EtatMoteurBean() {
		super();
	}
	/**
	 * Constructeur avec param�tres
	 * @param vitesse
	 * @param moteurActif
	 */
	public EtatMoteurBean(int vitesse, boolean moteurActif) {
		super();
		this.vitesse = vitesse;
		this.moteurActif = moteurActif;
	}
	/**
	 * @return the vitesse
	 */
	public int getVitesse() {
		return vitesse;
	}
	/**
	 * @param vitesse the vitesse to set
	 */
	public void setVitesse(int pVitesse) {
		this.vitesse = pVitesse;
	}
	/**
	 * @return the moteurActif
	 */
	public boolean isMoteurActif() {
		return moteurActif;
	}
	/**
	 * @param moteurActif the moteurActif to set
	 */
	public void setMoteurActif(boolean pMoteurActif) {
		this.moteurActif = pMoteurActif;
	}

}
