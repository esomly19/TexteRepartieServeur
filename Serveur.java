import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur {
	public static void main(String[] args) throws RemoteException, NotBoundException {

		Texte dis = new Texte();
		ServiceTexte rd = (ServiceTexte) UnicastRemoteObject.exportObject(dis, 0);
		Registry reg = LocateRegistry.getRegistry();
		reg.rebind("Phrases", rd);
		System.out.println("Lancement du serveur");
		System.out.println("Seveur en attente");
	

	}
	
	
}
