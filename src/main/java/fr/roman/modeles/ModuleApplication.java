package fr.roman.modeles;

/**
 * Énumération des modules de l'application. Chaque module est défini par un titre et une
 * description.
 *
 * @author Axel Leblanc
 */
public enum ModuleApplication {
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
   * Définir un module, caractérisé par un titre et une description.
   *
   * @param titre Titre du module
   * @param description Description du module
   */
  ModuleApplication(String titre, String description) {
    this.titre = titre;
    this.description = description;
  }

  /**
   * Retourne le titre du module.
   *
   * @return Titre du module
   */
  public String getTitre() {
    return titre;
  }

  /**
   * Retourne la description du module.
   *
   * @return Description du module
   */
  public String getDescription() {
    return description;
  }
}
