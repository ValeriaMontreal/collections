/* TP1 de Valeriya Popenko
* # matricule 1014631*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Bdd implements TestInterface {

  private Map<String, Collection<Jeu>> laBase;

  public Bdd() {
    laBase = new LinkedHashMap<>();
  }

  public void addJeu(Jeu unJeu) {
    Collection<Jeu> jeuCollection = laBase.getOrDefault(unJeu.getFabricant(), new ArrayList<>());
    jeuCollection.add(unJeu);

    laBase.put(unJeu.getFabricant(), jeuCollection);
  }

  public Jeu getJeu(String titre, String fabricant) {
    if (titre == null || fabricant == null || titre.equals("") || fabricant.equals("")) {
      return null;
    }

    Collection<Jeu> jeuCollection = laBase.get(fabricant);
    if (jeuCollection == null || jeuCollection.size() == 0) {
      return null;
    }

    return 
    		jeuCollection
                    .stream()
    		        .filter(item -> item.getTitre().equalsIgnoreCase(titre))
                    .findFirst()
                    .orElse(null);
  }

  public void addBdd(String nomFile) {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(nomFile));
      String line = reader.readLine();

      while (line != null) {
        String t[] = line.split(";"); 

        String fabricant = t[0];
        String titre = t[1];
        String cote = t[2];

        String[] consoles = t[3].split(",");    //la serie des consoles, separees par des virgules

        Jeu nouv = new Jeu(fabricant, titre, cote, List.of(consoles));

        Collection<Jeu> fabricantJeuList = laBase.getOrDefault(nouv.getFabricant(), new ArrayList<>());
        fabricantJeuList.add(nouv);
        laBase.put(nouv.getFabricant(), fabricantJeuList);

        line = reader.readLine();
      }
      reader.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadBdd(String nomFile)
  {
    addBdd(nomFile);
  }

  public ArrayList<Jeu> chercheConsole(String console) {
    List<Jeu> jeuList = new ArrayList<>();

    laBase.values().forEach(jeuList::addAll);

    return jeuList
        .stream()
        .filter(item -> item.getConsole().contains(console))
        .collect(Collectors
        .toCollection(ArrayList::new));
  }

  public Collection<Jeu> getJeuxFabricant(String fabricant)
  {
  		return laBase.get(fabricant);
  }

  public void saveBdd(String nomFichier) {

 		Path fileName = Path.of(nomFichier);
		StringBuilder content  = new StringBuilder();
    List<Jeu> jeuList = new ArrayList<>();

		laBase.values().forEach(jeuList::addAll);

		jeuList.forEach(item -> {
		  String format = "%s;%s;%s;%s\n";

      content.append(String.format(format, item.getFabricant(), item.getTitre(), item.getCote(), String.join(",", item.getConsole())));
    });
		try {
			Files.writeString(fileName, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
EXECUTION:

Les infos sur les Sims 5 : 

EA	The Sims 5	M
==========
PS4
XONE


Apres le load, les infos sur les Sims 5 : 

EA	The Sims 5	M
==========
PS4
XONE

Les infos sur NHL 20 : 

EA	NHL 2020	E
========
PS4
WIIU
XONE
PC

Les infos sur Vampyr : 

FOCUS	Vampyr	PG
======
PS4
XONE
PC


Apres le addBdd, les infos sur les Sims 5 : 

EA	The Sims 5	M
==========
PS4
XONE

Les infos sur NHL 20 : 

EA	NHL 2020	E
========
PS4
WIIU
XONE
PC

Les infos sur Vampyr : 

FOCUS	Vampyr	PG
======
PS4
XONE
PC


Les jeux disponibles sur la SWITCH sont :

UBISOFT	Just Dance 2020	E
===============
SWITCH


UBISOFT	Trials Rising	T
=============
SWITCH


UBISOFT	Gods & Monsters	RP
===============
MAC
SWITCH


FOCUS	Vampyr	PG
======
MAC
SWITCH


NINTENDO	Animal Crossing New Horizons	E
============================
SWITCH


Les jeux de UBISOFT

UBISOFT	Just Dance 2020	E
===============
PS4
WIIU
XONE


UBISOFT	Trials Rising	T
=============
PS4
WIIU
XONE


UBISOFT	ANO 1800	E
========
PC


UBISOFT	Ghost Recon Breakpoint	M
======================
PS4
XONE
PC


UBISOFT	Gods & Monsters	RP
===============
PS4
XONE
PC


UBISOFT	Just Dance 2020	E
===============
SWITCH


UBISOFT	Trials Rising	T
=============
SWITCH


UBISOFT	Gods & Monsters	RP
===============
MAC
SWITCH
*/