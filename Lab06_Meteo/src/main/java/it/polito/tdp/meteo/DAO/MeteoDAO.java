package it.polito.tdp.meteo.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore db\n");
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		final String sql = "SELECT Data, Umidita, Localita "
						  +"FROM situazione "
						  +"WHERE Data>=? AND Data<=? AND Localita=? "
						  +"ORDER BY data ASC";
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
		
		Date data1;
		Date data2;
		String s1 = "2013-"+mese+"-01";
		String s2 = "2013-"+mese+"-31";
		data1 = Date.valueOf(s1);
		data2 = Date.valueOf(s2);
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, data1);
			st.setDate(2, data2);		
			st.setString(3, localita);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore db\n");
		}
	}
	
	public List<Rilevamento> getRilevamentiPrimiGiorni(int mese, int giorni, String localita) {
		final String sql = "SELECT Data, Umidita, Localita "
						  +"FROM situazione "
						  +"WHERE Data>=? AND Data<=? AND Localita=? "
						  +"ORDER BY data ASC";
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
		
		Date data1;
		Date data2;
		String s1 = "2013-"+mese+"-01";
		String s2 = "2013-"+mese+"-"+giorni;
		data1 = Date.valueOf(s1);
		data2 = Date.valueOf(s2);
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, data1);
			st.setDate(2, data2);
			st.setString(3, localita);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore db\n");
		}
	}

	public List<Citta> getLeCitta() {

		final String sql = "SELECT DISTINCT Localita FROM situazione ORDER BY Localita ASC";

		List<Citta> result = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				Citta c = new Citta(rs.getString("Localita"));
				result.add(c);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			throw new RuntimeException("Errore db\n");
		}
	}

	public Double getUmiditaMedia(int mese, Citta c) {
		final String sql = "SELECT AVG(Umidita) AS m "
				 		   +"FROM situazione "
				 		   +"WHERE Data>=? AND Data<=? AND Localita=? "
				 		   +"ORDER BY data ASC";

		Date data1;
		Date data2;
		String s1 = "2013-"+mese+"-01";
		String s2 = "2013-"+mese+"-"+"31";
		data1 = Date.valueOf(s1);
		data2 = Date.valueOf(s2);
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, data1);
			st.setDate(2, data2);
			st.setString(3, c.getNome());

			ResultSet rs = st.executeQuery();
			rs.next();
			Double media = rs.getDouble("m");

			conn.close();
			return media;

		} catch (SQLException e) {
			throw new RuntimeException("Errore db\n");
		}
	}

}
