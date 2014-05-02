package fr.nom.petat.domo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import fr.nom.petat.domo.bean.EtatMoteurBean;
import fr.nom.petat.domo.bean.ReleveTemperatureBean;
import fr.nom.petat.domo.bean.RelevesBean;
import fr.nom.petat.domo.bean.TemperatureLoggerBean;

public class ReleveDao {
	public Logger logger = Logger.getLogger(ReleveDao.class);

	@Resource(name="jdbc/domo")
	private DataSource ds;
	
	/**
	 * Sauvegarde un ensemble de relevés
	 * @param pReleves	L'ensemble de relevé
	 * @param pExchange	L'échange Camel
	 */
	public void sauvegarde(RelevesBean pReleves) {
		logger.debug("Début sauvegarde");
		Connection laConnection;
		PreparedStatement ps;
		
		Double sondeCheminee = null;
		Double sondeMelangee = null;
		Double sondeVmcEntrant = null;
		Double sondeVmcSortant = null;
		Double sondeVmcAspire = null;
		Double sondeVmcInsufle = null;
		Double sondeInsufleCourt = null;
		Double sondeInsufleLong = null;
		for (ReleveTemperatureBean releveTemperatureBean : pReleves.getTemperatures()) {
			if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_CHEMINEE)) {
				sondeCheminee = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_MELANGE)) {
				sondeMelangee = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_ENTRANT)) {
				sondeVmcEntrant = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_SORTANT)) {
				sondeVmcSortant = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE)) {
				sondeVmcAspire = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_ASPIRE)) {
				sondeVmcInsufle = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE_COURT)) {
				sondeInsufleCourt = releveTemperatureBean.getTemperature();
			} else if (releveTemperatureBean.getTemperatureLogger().equals(TemperatureLoggerBean.SONDE_VMC_INSUFLE_LONG)) {
				sondeInsufleLong = releveTemperatureBean.getTemperature();
			}
		}

		try {
//			laConnection = ds.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
			laConnection = DriverManager.getConnection("jdbc:mysql://localhost/domo", "root", "***");
			StringBuffer sb = new StringBuffer("");
			sb.append("insert into domo_releve(date_releve, temperature_cheminee, temperature_melangee, ");
			sb.append("temperature_vmc_entrant, temperature_vmc_sortant, temperature_vmc_aspire, temperature_vmc_insufle,");
			sb.append("temperature_insufle_court, temperature_insufle_long, moteur_actif, vitesse_moteur) ");
			sb.append(" values(now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps = laConnection.prepareStatement(sb.toString());
			if (logger.isDebugEnabled()) {
				logger.debug(sb.toString());
				logger.debug("Température cheminée = " + sondeCheminee);
				logger.debug("Température mélangé = " + sondeMelangee);
				logger.debug("Température VMC entrant = " + sondeVmcEntrant);
				logger.debug("Température VMC sortant = " + sondeVmcSortant);
				logger.debug("Température VMC aspiré = " + sondeVmcAspire);
				logger.debug("Température VMC insuflé = " + sondeVmcInsufle);
				logger.debug("Température insuflé court = " + sondeInsufleCourt);
				logger.debug("Température insuflé long = " + sondeInsufleLong);
				if (pReleves.getMoteur() != null) {
					logger.debug("Moteur actif = " + pReleves.getMoteur().isMoteurActif());
					logger.debug("Vitesse moteur = " + pReleves.getMoteur().getVitesse());
				}
			}
			if (sondeCheminee != null) {
				ps.setDouble(1, sondeCheminee);
			} else {
				ps.setNull(1, Types.DOUBLE);
			}
			if (sondeMelangee != null) {
				ps.setDouble(2, sondeMelangee);
			} else {
				ps.setNull(2, Types.DOUBLE);
			}
			if (sondeVmcEntrant != null) {
				ps.setDouble(3, sondeVmcEntrant);
			} else {
				ps.setNull(3, Types.DOUBLE);
			}
			if (sondeVmcSortant != null) {
				ps.setDouble(4, sondeVmcSortant);
			} else {
				ps.setNull(4, Types.DOUBLE);
			}
			if (sondeVmcAspire != null) {
				ps.setDouble(5, sondeVmcAspire);
			} else {
				ps.setNull(5, Types.DOUBLE);
			}
			if (sondeVmcInsufle != null) {
				ps.setDouble(6, sondeVmcInsufle);
			} else {
				ps.setNull(6, Types.DOUBLE);
			}
			if (sondeInsufleCourt != null) {
				ps.setDouble(7, sondeInsufleCourt);
			} else {
				ps.setNull(7, Types.DOUBLE);
			}
			if (sondeInsufleLong != null) {
				ps.setDouble(8, sondeInsufleLong);
			} else {
				ps.setNull(8, Types.DOUBLE);
			}
			if (pReleves.getMoteur() != null) {
				ps.setBoolean(9, pReleves.getMoteur().isMoteurActif());
				ps.setInt(10, pReleves.getMoteur().getVitesse());
			} else {
				ps.setNull(9, Types.BOOLEAN);
				ps.setNull(10, Types.INTEGER);
			}
			ps.execute();
		} catch (Exception e) {
			logger.error("Erreur BDD", e);
		}
		logger.debug("fin sauvegarde");
	}
	
	/**
	 * Lire le dernier relevé
	 * @return Le dernier relevé
	 */
	public RelevesBean lireDernierReleve() {
		logger.debug("Début lireDernierReleve");
		Connection laConnection;
		PreparedStatement ps;
		ResultSet rs;
		RelevesBean releve = new RelevesBean();
		String[] sondes = {"temperature_cheminee", "temperature_melangee", "temperature_vmc_entrant", "temperature_vmc_sortant", "temperature_vmc_aspire", "temperature_vmc_insufle", "temperature_insufle_court", "temperature_insufle_long"};

		try {
//			laConnection = ds.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
			laConnection = DriverManager.getConnection("jdbc:mysql://localhost/domo", "root", "***");
			StringBuffer sb = new StringBuffer("");
			
			sb.append("select ");
			sb.append("		(select max(date_releve) from domo_releve) ");
			sb.append(getQuerySonde(sondes));
			sb.append(", moteur_actif, vitesse_moteur ");
			sb.append("from domo_releve ");
			sb.append("where date_releve = (select max(date_releve) from domo_releve) ");
			
			ps = laConnection.prepareStatement(sb.toString());
			if (logger.isDebugEnabled()) {
				logger.debug(sb.toString());
			}
			rs = ps.executeQuery();
			
			if (rs.next()) {
				releve.setDateReleve(rs.getTimestamp(1));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_CHEMINEE, rs.getDouble(2), rs.getTimestamp(1)));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_MELANGE, rs.getDouble(3), rs.getTimestamp(1)));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_VMC_ENTRANT, rs.getDouble(4), rs.getTimestamp(1)));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_VMC_SORTANT, rs.getDouble(5), rs.getTimestamp(1)));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_VMC_INSUFLE, rs.getDouble(6), rs.getTimestamp(1)));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_VMC_ASPIRE, rs.getDouble(7), rs.getTimestamp(1)));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_VMC_INSUFLE_COURT, rs.getDouble(8), rs.getTimestamp(1)));
				releve.addTemperature(new ReleveTemperatureBean(TemperatureLoggerBean.SONDE_VMC_INSUFLE_LONG, rs.getDouble(9), rs.getTimestamp(1)));
				releve.setMoteur(new EtatMoteurBean(rs.getInt(11), rs.getBoolean(10)));
			}
		
		} catch (Exception e) {
			logger.error("Erreur BDD", e);
		}
		
		logger.debug("Fin lireDernierReleve");
		return releve;
	}
	
	/**
	 * 
	 * @param pSondes
	 * @return
	 */
	public String getQuerySonde(String[] pSondes) {
		StringBuffer sb = new StringBuffer("");
		
		for (String sonde : pSondes) {
			sb.append(", (select ").append(sonde); 
			sb.append("	  from domo_releve ");
			sb.append("	  where date_releve >= addtime(now(), '-0:5') ");
			sb.append("         and ").append(sonde).append(" is not null ");
			sb.append("	  order by date_releve desc ");
			sb.append("	  limit 1) ");
		}
		
		return sb.toString();
	}

	/**
	 * Recherche les températures entre 2 dates
	 * @param pDateDebut	La date de début de recherche
	 * @param pDateFin		La date de fin de recherche
	 * @return Les températures
	 */
	public TimeSeriesCollection getTemperatures(Date pDateDebut, Date pDateFin) {
		logger.debug("Début getTemperatures");
		Connection laConnection;
		PreparedStatement ps;
		ResultSet rs;
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		try {
//			laConnection = ds.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
			laConnection = DriverManager.getConnection("jdbc:mysql://localhost/domo", "root", "***");
			StringBuffer sb = new StringBuffer("");
			sb.append("select date_releve, temperature_cheminee, temperature_melangee ");
			sb.append("from domo_releve ");
			if (pDateDebut != null) {
				sb.append("where date_releve >= ? ");
				if (pDateFin != null) {
					sb.append("and date_releve < ? ");
				}
			} else {
				if (pDateFin != null) {
					sb.append("where date_releve < ? ");
				}
			}
			ps = laConnection.prepareStatement(sb.toString());
			if (logger.isDebugEnabled()) {
				logger.debug(sb.toString());
				logger.debug("Date de début = " + pDateDebut);
				logger.debug("Date de fin = " + pDateFin);
			}
			if (pDateDebut != null) {
				ps.setTimestamp(1, new Timestamp(pDateDebut.getTime()));
				if (pDateFin != null) {
					ps.setTimestamp(2, new Timestamp(pDateFin.getTime()));
				}
			} else {
				if (pDateFin != null) {
					ps.setTimestamp(1, new Timestamp(pDateFin.getTime()));
				}
			}
			rs = ps.executeQuery();
			
			TimeSeries series1 = new TimeSeries("TemperatureCheminee");
			TimeSeries series2 = new TimeSeries("TemperatureMelangee");
			while (rs.next()) {
				series1.addOrUpdate(new Minute(rs.getTimestamp(1)), rs.getDouble(2));
				series2.addOrUpdate(new Minute(rs.getTimestamp(1)), rs.getDouble(3));
			}

			dataset.addSeries(series1);
			dataset.addSeries(series2);
		} catch (Exception e) {
			logger.error("Erreur BDD", e);
		}
		
		logger.debug("Fin getTemperatures");
		return dataset;
	}

	/**
	 * @return the ds
	 */
	public DataSource getDs() {
		return ds;
	}

	/**
	 * @param pDs the ds to set
	 */
	public void setDs(DataSource pDs) {
		this.ds = pDs;
	}
}
