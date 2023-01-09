package fr.roman.controleurs.recherche;

import fr.roman.RoManErreur;
import fr.roman.controleurs.actions.ActionsCommandes;
import fr.roman.dao.DAOCommande;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.vues.composants.BoutonAction;
import fr.roman.vues.recherche.VueRecherche;
import fr.roman.vues.recherche.VueRechercheCommande;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Contrôleur de la vue de recherche de commandes.
 *
 * @see VueRecherche
 * @see VueRechercheCommande
 * @see Commande
 *
 * @author Axel Leblanc
 */
public class CtrlRechercheCommande extends CtrlRecherche<Commande> {
  private final Producteur producteur;
  private List<Commande> commandes;

  /**
   * Créer un contrôleur pour la vue de recherche de commandes, pour le producteur passé en
   * paramètre.
   *
   * @param producteur Producteur actuellement connecté
   * @param vue Vue correspondante
   */
  public CtrlRechercheCommande(Producteur producteur, VueRecherche<Commande> vue) {
    super(vue);

    this.producteur = producteur;

    vue.getTableau().autoriserSelectionMultiple(true);
  }

  /**
   * Définir les boutons d'action qui seront disponibles (boutons en bas de la vue et dans le
   * menu contextuel du tableau), et les fonctions de callback exécutées lorsqu'ils sont actionnés.
   */
  protected void definirBoutonsActions() {
    BoutonAction modifier = new BoutonAction("Modifier", () ->
        ActionsCommandes.modifierCommande(
            vue.getTableau().getSelectionSimple(), Role.PRODUCTEUR
        )
    );
    BoutonAction supprimer = new BoutonAction("Supprimer", () -> {
      List<Commande> selection = vue.getTableau().getSelectionMultiple();
      if (ActionsCommandes.supprimer(selection)) {
        vue.getTableau().supprimer(selection);
      }
    });
    BoutonAction nouveau = new BoutonAction("Nouvelle commande", () ->
        ActionsCommandes.creerCommande(producteur, Role.PRODUCTEUR)
    );
    BoutonAction afficher = new BoutonAction("Afficher la commande", () ->
            ActionsCommandes.afficherCommande(vue.getTableau()
                    .getSelectionSimple(), producteur.getUtilisateur())
    );

    // Définition des boutons affichés en bas de la vue
    vue.setBoutons(List.of(afficher, modifier, supprimer, nouveau));

    // Définition des entrées du menu contextuel du tableau
    vue.getTableau().setMenu(List.of(afficher, modifier, supprimer));
  }

  @Override
  public void actualiserVue() {
    try {
      // Obtention des commandes du producteur
      DAOCommande daoCommande = new DAOCommande();
      LinkedHashMap<Commande.Champs, String> criteres = new LinkedHashMap<>();
      criteres.put(Commande.Champs.idProducteur, String.valueOf(producteur.getId()));
      commandes = daoCommande.find(criteres);
      vue.getTableau().setContenu(commandes);

      definirBoutonsActions();
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
      System.exit(1);
    }
  }
}
