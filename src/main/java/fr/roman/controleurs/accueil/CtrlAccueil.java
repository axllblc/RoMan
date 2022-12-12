package fr.roman.controleurs.accueil;

import fr.roman.modeles.ModuleAccueil;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.accueil.VueAccueil;
import fr.roman.vues.accueil.VueModuleAccueil;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;


/**
 * Contrôleur correspondant à la vue <i>Accueil</i>. Celui-ci se charge de déterminer les modules
 * qui seront accessibles par l'utilisateur (qui seront matérialisés par des onglets dans la vue),
 * selon son rôle (<i>cf.</i> {@link fr.roman.modeles.Role}). Les vues et contrôleurs de ces
 * modules sont instanciés ici.
 *
 * @see fr.roman.vues.accueil.VueAccueil
 * @see fr.roman.modeles.ModuleAccueil
 *
 * @author Axel Leblanc
 */
public class CtrlAccueil {
  private final VueAccueil vueAccueil;
  private final Utilisateur utilisateur;

  private final Map<ModuleAccueil, VueModuleAccueil> mapVuesAccueil;

  /**
   * Créer un contrôleur pour la vue Accueil.
   *
   * @see CtrlAccueil
   *
   * @param utilisateur Utilisateur actuellement connecté à l'application
   * @param vueAccueil Vue à gérer
   */
  public CtrlAccueil(Utilisateur utilisateur, VueAccueil vueAccueil) {
    this.utilisateur = utilisateur;
    this.vueAccueil = vueAccueil;

    mapVuesAccueil = new LinkedHashMap<>();
    /* L'implémentation LinkedHashMap a été choisie, car les éléments sont dans l'ordre dans lequel
     * les clés ont été insérées. */

    vueAccueil.setCtrl(this);

    // Instanciation des vues et contrôleurs
    switch (utilisateur.getRole()) {
      case ROOT:
        modulesRoot();
        // L'utilisateur ROOT hérite des privilèges de l'administrateur
      case ADMINISTRATEUR:
        modulesAdministrateur();
        break;
      case PRODUCTEUR:
        modulesProducteur();
        break;
      default: throw new RuntimeException("Le rôle de l'utilisateur courant n'est pas valide.");
    }

    vueAccueil.afficherInfosUtilisateur(utilisateur);
    vueAccueil.afficherModules(mapVuesAccueil);
  }

  /*
   * ⚠️ Les implémentations de l'interface VueModuleAccueil sont provisoires. Des classes seront
   * créées pour chaque vue.
   */

  /**
   * Instanciation des vues et contrôleurs des modules pour l'utilisateur ROOT.
   */
  private void modulesRoot() {
    // Gestion des administrateurs
    // TODO à implémenter
    mapVuesAccueil.put(ModuleAccueil.ADMINISTRATEURS, new VueModuleAccueil() {
      @Override
      public Node getNode() {
        return new Label("Gestion des administrateurs (à implémenter)");
      }
    });
  }

  /**
   * Instanciation des vues et contrôleurs des modules pour un administrateur.
   */
  private void modulesAdministrateur() {
    // Gestion des producteurs
    // TODO à implémenter
    mapVuesAccueil.put(ModuleAccueil.PRODUCTEURS, new VueModuleAccueil() {
      @Override
      public Node getNode() {
        return new Label("Gestion des producteurs (à implémenter)");
      }
    });

    // Gestion des clients
    // TODO à implémenter
    mapVuesAccueil.put(ModuleAccueil.CLIENTS, new VueModuleAccueil() {
      @Override
      public Node getNode() {
        return new Label("Gestion des clients (à implémenter)");
      }
    });
  }

  /**
   * Instanciation des vues et contrôleurs des modules pour un producteur.
   */
  private void modulesProducteur() {
    // Tableau de bord
    // TODO à implémenter
    mapVuesAccueil.put(ModuleAccueil.TABLEAU_DE_BORD, new VueModuleAccueil() {
      @Override
      public Node getNode() {
        return new Label("Tableau de bord (à implémenter)");
      }
    });

    // Gestion des commandes
    // TODO à implémenter
    mapVuesAccueil.put(ModuleAccueil.COMMANDES, new VueModuleAccueil() {
      @Override
      public Node getNode() {
        return new Label("Commandes (à implémenter)");
      }
    });

    // Gestion des tournées
    // TODO à implémenter
    mapVuesAccueil.put(ModuleAccueil.TOURNEES, new VueModuleAccueil() {
      @Override
      public Node getNode() {
        return new Label("Tournées (à implémenter)");
      }
    });
  }
}
