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
	private List<String> provvisorio;
	

	public void anagrammi(String parola) {
		dao= new AnagrammaDAO();  //CREO
		
		provvisorio= new LinkedList<>();
		provvisorio.add(""); //un elemento
		
		List<Character> disponibili = new LinkedList<>();

		anagrammiC = new HashSet<>();
		anagrammiE = new HashSet<>();
		

		for (int i = 0; i < parola.length(); i++) {
			disponibili.add(parola.charAt(i));
		}

		this.ricorsiva(provvisorio, 0, disponibili);
		

	}
	

	private void ricorsiva(List<String> parziale, Integer livello, List<Character> disponibili) {

		if (disponibili.size() == 0) {
			

			if (dao.isCorrect(parziale.get(parziale.size()-1))) { // se non la creo dao.isCorrect -> eccezione perchè dao = null

				this.anagrammiC.add(parziale.get(parziale.size()-1));
				return;

			} else {
				this.anagrammiE.add(parziale.get(parziale.size()-1));
				return;

			}

		}

		for (Character c : disponibili) {
			parziale.add(parziale.get(parziale.size()-1)+c);
			

			List<Character> disponibiliTemp = new LinkedList<>(disponibili);
			disponibiliTemp.remove(c);

			ricorsiva(parziale, livello + 1, disponibiliTemp);
			
			parziale.remove(parziale.size()-1);

		}

	}

	public Set<String> getAnagrammiC() {
		return anagrammiC;
	}

	public Set<String> getAnagrammiE() {
		return anagrammiE;
	}
	
	
	
	

}
