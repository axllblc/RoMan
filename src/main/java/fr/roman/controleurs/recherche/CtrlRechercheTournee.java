package fr.roman.controleurs.recherche;

import fr.roman.RoManErreur;
import fr.roman.controleurs.actions.ActionsTournees;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Tournee;
import fr.roman.vues.composants.BoutonAction;
import fr.roman.vues.recherche.VueRecherche;
import fr.roman.vues.recherche.VueRechercheTournee;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Contrôleur de la vue de recherche de commandes.
 *
 * @see VueRecherche
 * @see VueRechercheTournee
 * @see Tournee
 *
 * @author Axel Leblanc
 */
public class CtrlRechercheTournee extends CtrlRecherche<Tournee> {
  private final Producteur producteur;
  private List<Tournee> tournees;

  /**
   * Créer un contrôleur pour la vue de recherche des tournées, pour le producteur passé en
   * paramètre.
   *
   * @param producteur Producteur actuellement connecté
   * @param vue Vue correspondante
   */
  public CtrlRechercheTournee(Producteur producteur, VueRecherche<Tournee> vue) {
    super(vue);

    this.producteur = producteur;
  }

  /**
   * Définir les boutons d'action qui seront disponibles (boutons en bas de la vue et dans le
   * menu contextuel du tableau), et les fonctions de callback exécutées lorsqu'ils sont actionnés.
   */
  protected void definirBoutonsActions() {
    BoutonAction modifier = new BoutonAction("Modifier la tournée", () ->
        ActionsTournees.modifierTournee(
            vue.getTableau().getSelectionSimple(), Role.PRODUCTEUR
        )
    );
    BoutonAction supprimer = new BoutonAction("Supprimer", () -> {
      Tournee selection = vue.getTableau().getSelectionSimple();
      if (ActionsTournees.supprimer(selection)) {
        vue.getTableau().supprimer(selection);
      }
    });
    BoutonAction nouveau = new BoutonAction("Nouvelle tournée", () ->
        ActionsTournees.creerTournee(producteur, Role.PRODUCTEUR)
    );
    BoutonAction afficher = new BoutonAction("Afficher la tournée", () ->
            ActionsTournees.afficherTournee(vue.getTableau()
                    .getSelectionSimple(), producteur.getUtilisateur()));

    // Définition des boutons affichés en bas de la vue
    vue.setBoutons(List.of(afficher, modifier, supprimer, nouveau));

    // Définition des entrées du menu contextuel du tableau
    vue.getTableau().setMenu(List.of(afficher, modifier, supprimer));
  }

  @Override
  public void actualiserVue() {
    try {
      // Obtention des tournées du producteur
      DAOTournee daoTournee = new DAOTournee();
      LinkedHashMap<Tournee.Champs, String> criteres = new LinkedHashMap<>();
      criteres.put(Tournee.Champs.idProducteur, String.valueOf(producteur.getId()));
      tournees = daoTournee.find(criteres);
      vue.getTableau().setContenu(tournees);

      definirBoutonsActions();
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
      System.exit(1);
    }
  }
}
