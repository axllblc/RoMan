package fr.roman.controleurs.accueil;

import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.accueil.TableauDeBordProducteur;

/**
 * Contrôleur de la vue <i>Tableau de bord</i> pour les producteurs.
 *
 * @see TableauDeBordProducteur
 */
public class CtrlTabBordProducteur {
  private Utilisateur utilisateur;
  private TableauDeBordProducteur vue;

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

    // TODO à implémenter
    // Instanciation du contrôleur du tableau pour les commandes
    // Instanciation et appel du DAO Commandes
    // Insertion des commandes non affectées à une tournée dans le tableau

    // Instanciation du contrôleur du tableau pour les tournées
    // Instanciation et appel du DAO Tournées
    // Insertion des tournées à venir
  }

  public void nouvelleCommande() {
    // TODO à implémenter
  }

  public void nouvelleTournee() {
    // TODO à implémenter
  }
}
