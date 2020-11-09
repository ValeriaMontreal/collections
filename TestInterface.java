import java.util.*;


public interface TestInterface {
	
   public void addJeu(Jeu unJeu);

	public Jeu getJeu(String titre, String fabricant);

	public void addBdd(String nomFile);

	public void loadBdd(String nomFile);

	public ArrayList<Jeu> chercheConsole(String console);

	public Collection<Jeu> getJeuxFabricant(String fabricant);

	public void saveBdd(String nomFichier);

}
