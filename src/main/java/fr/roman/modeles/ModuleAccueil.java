package fr.roman.modeles;

/**
 * Énumération des modules accessibles depuis l'accueil de l'application. Chaque onglet est défini
 * par un titre et une description.
 *
 * @author Axel Leblanc
 */
public enum ModuleAccueil {
  TABLEAU_DE_BORD("Tableau de bord", "Visualiser les commandes et tournées à venir."),
  COMMANDES("Commandes", "Créer, modifier ou supprimer des commandes."),
  TOURNEES("Tournées", "Créer, modifier ou supprimer des tournées."),
  PRODUCTEURS("Producteurs", "Gérer les producteurs inscrits au programme et leurs informations."),
  CLIENTS("Clients", "Gérer les clients inscrits au programme et leurs informations."),
  ADMINISTRATEURS("Gestion des administrateurs",
      "Ajouter, modifier ou supprimer des administrateurs.");

  private final String titre;
  private final String description;

  /**
   * Définir un module pour l'accueil de l'application, caractérisé par un titre et une description.
   *
   * @param titre Titre de l'onglet
   * @param description Description de l'onglet
   */
  ModuleAccueil(String titre, String description) {
    this.titre = titre;
    this.description = description;
  }

  public String getTitre() {
    return titre;
  }

  public String getDescription() {
    return description;
  }
}
