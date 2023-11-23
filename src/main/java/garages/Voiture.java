package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void entreAuGarage(Garage g) throws Exception {
		for (Stationnement st : myStationnements) {
			if (st.estEnCours()) {
				throw new Exception("La voiture est déjà dans un garage.");
			}
		}
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	public void sortDuGarage() throws Exception {
		for(Stationnement st : myStationnements) {
			if (st.estEnCours()) {
				st.terminer();
				return;
			}
		}
		throw new Exception("La voiture n'est pas dans un garage");
	}

	public Set<Garage> garagesVisites() {
		Set<Garage> garagesVisites = new LinkedHashSet<>();
		for (Stationnement s : myStationnements) {
			garagesVisites.add(s.getGarage());
		}
		return (garagesVisites);
	}

	public boolean estDansUnGarage() {
		for (Stationnement st : myStationnements) {
			if (st.estEnCours()) {
				return true;
			}
		}
		return false;
	}

	public void imprimeStationnements(PrintStream out) {
		for (Garage garage : garagesVisites()) {
			out.append(garage.toString() + ":" + "\n");
			for (Stationnement stationnement: myStationnements) {
				if (stationnement.getGarage() == garage){
					out.append(" " + stationnement.toString() + "\n");
				}
			}
		}
	}
}