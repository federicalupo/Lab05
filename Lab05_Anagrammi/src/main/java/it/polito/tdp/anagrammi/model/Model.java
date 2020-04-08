package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.DAO.AnagrammaDAO;

public class Model {

	private Set<String> anagrammiC; 
	private Set<String> anagrammiE;
	private AnagrammaDAO dao;
	

	public void anagrammi(String parola) {
		dao= new AnagrammaDAO();  //CREO
		
		List<Character> disponibili = new LinkedList<>();

		anagrammiC = new HashSet<>();
		anagrammiE = new HashSet<>();
		

		for (int i = 0; i < parola.length(); i++) {
			disponibili.add(parola.charAt(i));
		}

		this.ricorsiva("", 0, disponibili);
		

	}
	

	private void ricorsiva(String parziale, Integer livello, List<Character> disponibili) {

		if (disponibili.size() == 0) {
			

			if (dao.isCorrect(parziale)) { // se non la creo dao.isCorrect -> eccezione perchè dao = null

				this.anagrammiC.add(parziale);
				return;

			} else {
				this.anagrammiE.add(parziale);
				return;

			}

		}

		for (Character c : disponibili) {
			String temp = parziale + c;

			List<Character> disponibiliTemp = new LinkedList<>(disponibili);
			disponibiliTemp.remove(c);

			ricorsiva(temp, livello + 1, disponibiliTemp);

		}

	}

	public Set<String> getAnagrammiC() {
		return anagrammiC;
	}

	public Set<String> getAnagrammiE() {
		return anagrammiE;
	}
	
	
	
	

}
