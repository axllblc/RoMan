package fr.roman.controleurs.accueil;

import fr.roman.RoManErreur;
import fr.roman.controleurs.actions.ActionsCommandes;
import fr.roman.dao.DAOCommande;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Tournee;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.accueil.TableauDeBordProducteur;
import fr.roman.vues.composants.BoutonAction;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Contrôleur de la vue <i>Tableau de bord</i> pour les producteurs.
 *
 * @see TableauDeBordProducteur
 */
public class CtrlTabBordProducteur {
  private Producteur producteur;
  private final Utilisateur utilisateur;
  private final TableauDeBordProducteur vue;

  /**
   * Initialiser le contrôleur de la vue <i>Tableau de bord</i> pour un utilisateur ayant le rôle
   * {@link Role#PRODUCTEUR}.
   *
   * <p><b>Précondition :</b> {@code utilisateur.getRole() == Role.PRODUCTEUR}</p>
   *
   * @param utilisateur Utilisateur actuellement connecté à l'application
   * @param vue Vue <i>Tableau de bord</i> à gérer.
   *
   * @see TableauDeBordProducteur
   */
  public CtrlTabBordProducteur(Utilisateur utilisateur, TableauDeBordProducteur vue) {
    this.utilisateur = utilisateur;
    this.vue = vue;

    vue.setCtrl(this);

    try {
      // Récupération du producteur lié à l'utilisateur actuel
      DAOProducteur daoProducteur = new DAOProducteur();
      producteur = daoProducteur.find(utilisateur);

      if (producteur == null) {
        throw new RuntimeException("Le compte utilisateur n'est pas lié à un producteur.");
      }

      // Remplissage des tableaux
      tableauCommandes();
      tableauTournees();

      // Activation de la sélection multiple
      vue.getTableauTournees().autoriserSelectionMultiple(true);
      vue.getTableauCommandes().autoriserSelectionMultiple(true);

      // Menus contextuels des tableaux
      menuCommandes();
      menuTournees();
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Remplissage du tableau des commandes.
   */
  private void tableauCommandes() throws Exception {
    // Récupération des commandes
    DAOCommande daoCommande = new DAOCommande();
    LinkedHashMap<Commande.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Commande.Champs.idProducteur, String.valueOf(producteur.getId()));
    List<Commande> commandes = daoCommande.find(criteres);

    // Ne conserver que les commandes non livrées (le DAO ne permet pas ce filtrage)
    commandes = commandes.stream()
        .filter(commande -> commande.getDateLivraison() == null)
        .toList();

    // Ajout des commandes au tableau
    vue.getTableauCommandes().setContenu(commandes);
  }

  /**
   * Remplissage du tableau des tournées.
   */
  private void tableauTournees() throws Exception {
    // Récupération des tournées (non livrées)
    DAOTournee daoTournee = new DAOTournee();
    LinkedHashMap<Tournee.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Tournee.Champs.idProducteur, String.valueOf(producteur.getId()));
    List<Tournee> tournees = daoTournee.find(criteres);

    // Ne conserver que les tournées à venir (le DAO ne permet pas ce filtrage)
    tournees = tournees.stream()
        .filter(tournee -> tournee.getHoraireDebut().after(Calendar.getInstance()))
        .toList();

    // Ajout des tournées au tableau
    vue.getTableauTournees().setContenu(tournees);
  }

  /*
   * Les actions du menu contextuel du tableau des commandes ne seront pas les mêmes que pour
   * celles du menu du tableau des tournées.
   */

  /**
   * Ajout du menu contextuel du tableau des commandes.
   */
  private void menuCommandes() {
    BoutonAction modifier = new BoutonAction("Modifier la commande", () -> {
      // TODO à implémenter
    });
    BoutonAction supprimer = new BoutonAction("Supprimer", () -> {
      List<Commande> selection = vue.getTableauCommandes().getSelectionMultiple();
      if (ActionsCommandes.supprimer(selection)) {
        vue.getTableauCommandes().supprimer(selection);
      }
    });

    vue.getTableauCommandes().setMenu(List.of(modifier, supprimer));
  }

  /**
   * Ajout du menu contextuel du tableau des tournées.
   */
  private void menuTournees() {
    BoutonAction modifier = new BoutonAction("Modifier la tournée", () -> {
      // TODO à implémenter
    });
    BoutonAction supprimer = new BoutonAction("Supprimer", () -> {
      // TODO à implémenter
    });

    vue.getTableauTournees().setMenu(List.of(modifier, supprimer));
  }

  public void nouvelleCommande() {
    // TODO à implémenter
  }

  public void nouvelleTournee() {
    // TODO à implémenter
  }
}
