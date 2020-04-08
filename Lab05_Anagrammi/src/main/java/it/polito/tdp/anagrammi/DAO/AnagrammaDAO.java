package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammaDAO {

	/*
	 * inutile : salvarsi tutte le righe del db in una lista, 
	 * quello che mi serve lo
	 * cerco con query
	 */

	public boolean isCorrect(String anagramma) {
		String sql = "select * from parola where nome=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);

			ResultSet rs = st.executeQuery();

			if (rs.next()) { // se c'è
				conn.close();
				return true;
			} else {
				conn.close();
				return false;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

}
