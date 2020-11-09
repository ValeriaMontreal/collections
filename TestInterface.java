/* TP1 de Valeriya Popenko
*  # matricule 1014631*/

import java.util.*;
/**
	Pour la correction du devoir, nous allons nous bat�r
	un programme de test qui va contenir la m�thode main
	� ex�cuter.  L'interface sert � garantir la syntaxe
	commune pour tous les travaux.

 */


public interface TestInterface {
	
   public void addJeu(Jeu unJeu);

	public Jeu getJeu(String titre, String fabricant);

	public void addBdd(String nomFile);

	public void loadBdd(String nomFile);

	public ArrayList<Jeu> chercheConsole(String console);

	public Collection<Jeu> getJeuxFabricant(String fabricant);

	public void saveBdd(String nomFichier);

}