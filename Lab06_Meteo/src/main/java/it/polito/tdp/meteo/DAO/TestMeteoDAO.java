package it.polito.tdp.meteo.DAO;

import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class TestMeteoDAO {

	public static void main(String[] args) {
		
		MeteoDAO dao = new MeteoDAO();

		List<Rilevamento> list = dao.getAllRilevamenti();

		// STAMPA: localita, giorno, mese, anno, umidita (%)
		for (Rilevamento r : list) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		
		System.out.println("---------------------------------------------");
		List<Rilevamento> list1 = dao.getAllRilevamentiLocalitaMese(1, "Genova");
		for (Rilevamento r : list1) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(1, "Genova"));
//		
		System.out.println("---------------------------------------------");
		List<Rilevamento> list2 = dao.getAllRilevamentiLocalitaMese(1, "Milano");
		for (Rilevamento r : list2) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Milano"));
//		
		System.out.println("---------------------------------------------");
		List<Rilevamento> list3 = dao.getAllRilevamentiLocalitaMese(1, "Torino");
		for (Rilevamento r : list3) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Torino"));
		
		System.out.println("---------------------------------------------");
		List<Rilevamento> list4 = dao.getRilevamentiPrimiGiorni(5, 15, "Torino");
		for (Rilevamento r : list4) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		
		System.out.println("---------------------------------------------");
		List<Rilevamento> list5 = dao.getRilevamentiPrimiGiorni(5, 15, "Genova");
		for (Rilevamento r : list5) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		
		System.out.println("---------------------------------------------");
		List<Rilevamento> list6 = dao.getRilevamentiPrimiGiorni(5, 15, "Milano");
		for (Rilevamento r : list6) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		
		System.out.println("---------------------------------------------");
		List<Citta> list7 = dao.getLeCitta();
		for(Citta c: list7)
			System.out.println(c);

	}

}
