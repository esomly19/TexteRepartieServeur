import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Texte implements Serializable, ServiceTexte {

	ArrayList<ServiceDistributeur> ldis = new ArrayList<ServiceDistributeur>();
	ArrayList<Phrases> lph = new ArrayList<Phrases>();

	public ArrayList<Phrases> envoyerTexte(ArrayList<Phrases> txt, String mot) throws RemoteException {
		int nbservice = ldis.size();
		int nbphrases = txt.size();
		int taillePartie = (Integer) nbphrases / nbservice;
		int debut = 0;
		int fin = taillePartie;
		ArrayList<Phrases> lres = new ArrayList<Phrases>();

		for (ServiceDistributeur st : ldis) {
			ArrayList<Phrases> aTraiter = new ArrayList<Phrases>(txt.subList(debut, fin));
			ArrayList<Phrases> phr = st.traiterTexte(aTraiter, mot);
			debut = fin;
			fin  += fin;
			if (fin > nbphrases)
				fin = nbphrases;
			for (Phrases p : phr)
				lres.add(p);
		}
		return lres;
	}

	public void enregistrerNoeud(ServiceDistributeur st) throws RemoteException {
		this.ldis.add(st);
	}

	
}