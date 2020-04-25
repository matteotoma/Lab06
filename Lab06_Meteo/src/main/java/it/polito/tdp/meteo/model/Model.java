package it.polito.tdp.meteo.model;

import java.util.*;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	private MeteoDAO dao;
	private List<Citta> leCitta;
	
	private double costoBest;
	private List<Citta> sequenzaBest;

	public Model() {
		dao = new MeteoDAO();
		leCitta = dao.getLeCitta();
		
		costoBest = Double.MAX_VALUE;
		sequenzaBest = null;
	}

	public Double getUmiditaMedia(int mese, Citta c) {
		return dao.getUmiditaMedia(mese, c);
	}
	

	public List<Citta> trovaSequenza(int mese) {
		List<Citta> parziale = new ArrayList<>();
		for(Citta c: leCitta) {
			c.setRilevamenti(dao.getRilevamentiPrimiGiorni(mese, 15, c.getNome()));
		}
		cerca(parziale, 0);
		System.out.println(costoBest);
		return sequenzaBest;
	}
	
	private void cerca(List<Citta> parziale, int livello) {
		// Caso terminale
		if(livello == NUMERO_GIORNI_TOTALI) {
			double costo = calcolaCosto(parziale);
			if(costo<costoBest) {
				costoBest = costo;
				sequenzaBest = new ArrayList<>(parziale);
			}
			return;
		}
		
		// Generazione sottoproblemi
		for(Citta c: leCitta) {
			if(aggiungiCittaValida(parziale, c)) {
				parziale.add(c);
				cerca(parziale, livello+1);
				parziale.remove(parziale.size()-1);
			}
		}
	}

	private boolean aggiungiCittaValida(List<Citta> parziale, Citta prova) {
		// Controllo giorni massimi
		int contatore = 0;
		for(Citta c: parziale)
			if(c.equals(prova))
				contatore++;
		if(contatore>=NUMERO_GIORNI_CITTA_MAX)
			return false;
		
		// Controllo giorni minimi
		if(parziale.size()==0)
			return true;
		if(parziale.size()==1 || parziale.size()==2)   // Almeno tre giorni consecutivi, non posso cambiare
			return parziale.get(parziale.size()-1).equals(prova);
		if(parziale.get(parziale.size()-1).equals(prova))   // Dopo i tre giorni consecutivi posso rimanere
			return true;
		if(parziale.get(parziale.size()-1).equals(parziale.get(parziale.size()-2)) && 
		   parziale.get(parziale.size()-2).equals(parziale.get(parziale.size()-3))) // Almeno tre giorni consecutivi
			return true;
		
		return false;
	}

	private double calcolaCosto(List<Citta> parziale) {
		double costo = 0.0;
		for(int giorno=0; giorno<NUMERO_GIORNI_TOTALI; giorno++) {
			double umidita = parziale.get(giorno).getRilevamenti().get(giorno).getUmidita();
			costo += umidita;
			if(giorno!=0 && !(parziale.get(giorno).equals(parziale.get(giorno-1))))
					costo += COST;
		}
		return costo;
	}

	public List<Citta> getcLeCitta() {
		return leCitta;
	}

}