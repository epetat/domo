package fr.nom.petat.domo.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.data.xy.XYDataset;

import fr.nom.petat.domo.bean.TemperatureLoggerBean;
import fr.nom.petat.domo.bean.TemperatureLoggerBean.TypeSonde;
import fr.nom.petat.domo.dao.ReleveDao;

public class TemperatureService {
	/**
	 * Lecture de la température sur une sonde
	 * @param pTemperatureLoggerBean	La sonde
	 * @return La température
	 */
	public Double lireTemperature(TemperatureLoggerBean pTemperatureLoggerBean) throws IOException, InterruptedException, SecurityException {
		Double temp_c = null;
		
		if (TypeSonde.DS18B20.equals(pTemperatureLoggerBean.getTypeSonde())) {
			Runtime.getRuntime().exec("modprobe w1-gpio");
			Runtime.getRuntime().exec("modprobe w1-therm");
			
			String baseDir = "/sys/bus/w1/devices/";
			String deviceFolder = baseDir + pTemperatureLoggerBean.getDevice();
			String deviceFile = deviceFolder + "/w1_slave";
			
			File file = new File(deviceFile);
			
			ArrayList<String> lines = lireFichier(file);
			
			while (!(lines.get(0).trim().substring(lines.get(0).trim().length() - 3, lines.get(0).trim().length())).equals("YES")) {
				Thread.sleep(200);
				lines = lireFichier(file);
			}
			int equalsPos = lines.get(1).indexOf("t=");
			if (equalsPos != -1) {
				String tempString = lines.get(1).substring(equalsPos + 2);
				temp_c = Float.parseFloat(tempString) / 1000.0;
			}
		}
		
		return temp_c;
	}
	
	/**
	 * Lecture du contenu d'un fichier
	 * @param pFile	Le fichier
	 * @return Le contenu du fichier
	 */
	private ArrayList<String> lireFichier(File pFile) throws IOException {
		FileInputStream fis = new FileInputStream(pFile);
		DataInputStream dis = new DataInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		String strLine = null;
		ArrayList<String> lines = new ArrayList<String>();
		
		while ((strLine = br.readLine()) != null) {
			lines.add(strLine);
		}
		dis.close();
		
		return lines;
	}
	
	/**
	 * Recherche les températures entre 2 dates
	 * @param pDateDebut	La date de début de recherche
	 * @param pDateFin		La date de fin de recherche
	 * @return Les températures
	 */
	public XYDataset getTemperatures(Date pDateDebut, Date pDateFin) {
		ReleveDao releveDao = new ReleveDao();
		return releveDao.getTemperatures(pDateDebut, pDateFin);
	}
}
