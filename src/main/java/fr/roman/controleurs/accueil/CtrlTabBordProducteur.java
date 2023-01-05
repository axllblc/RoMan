package fr.roman.controleurs.accueil;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOCommande;
import fr.roman.dao.DAOProducteur;
import fr.roman.dao.DAOTournee;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Tournee;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.accueil.TableauDeBordProducteur;
import java.util.ArrayList;
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
    // Récupération des commandes (non livrées)
    DAOCommande daoCommande = new DAOCommande();
    LinkedHashMap<Commande.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Commande.Champs.idProducteur, String.valueOf(producteur.getId()));
    criteres.put(Commande.Champs.dateLivraison, null);
    ArrayList<Commande> commandes = daoCommande.find(criteres);

    // Ajout des commandes au tableau
    vue.getTableauCommandes().setContenu(commandes);
  }

  /**
   * Remplissage du tableau des tournées.
   */
  private void tableauTournees() throws Exception {
    // Récupération des tournées
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

  public void nouvelleCommande() {
    // TODO à implémenter
  }

  public void nouvelleTournee() {
    // TODO à implémenter
  }
}
