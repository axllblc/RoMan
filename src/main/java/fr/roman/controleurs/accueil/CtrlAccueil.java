package fr.roman.controleurs.accueil;

import fr.roman.RoManErreur;
import fr.roman.controleurs.recherche.CtrlRechercheCommande;
import fr.roman.dao.DAOProducteur;
import fr.roman.modeles.ModuleApplication;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.VueIntegrable;
import fr.roman.vues.accueil.VueAccueil;
import fr.roman.vues.recherche.VueRechercheCommande;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Contrôleur correspondant à la vue <i>Accueil</i>. Celui-ci se charge de déterminer les modules
 * qui seront accessibles par l'utilisateur (matérialisés par des onglets dans la vue), selon son
 * rôle (<i>cf.</i> {@link fr.roman.modeles.Role}). Les vues et contrôleurs correspondants à ces
 * modules sont instanciés ici.
 *
 * @see fr.roman.vues.accueil.VueAccueil
 * @see ModuleApplication
 *
 * @author Axel Leblanc
 */
public class CtrlAccueil {
  private final VueAccueil vueAccueil;
  private final Utilisateur utilisateur;

  /**
   * Liste des modules accessibles par l'utilisateur (matérialisés par des onglets dans la vue
   * <i>Accueil</i>), associés à la vue qui leur correspond.
   */
  private final Map<ModuleApplication, VueIntegrable> mapModuleVue;

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

    mapModuleVue = new LinkedHashMap<>();
    /* L'implémentation LinkedHashMap a été choisie, car les éléments sont dans l'ordre dans lequel
     * les clés ont été insérées. */

    vueAccueil.setCtrl(this);

    try {
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
    } catch (Exception e) {
      RoManErreur.afficher(e);
      e.printStackTrace();
      System.exit(1);
    }

    vueAccueil.afficherInfosUtilisateur(utilisateur);
    vueAccueil.afficherModules(mapModuleVue);
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
    mapModuleVue.put(ModuleApplication.ADMINISTRATEURS, new VueIntegrable() {
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
    mapModuleVue.put(ModuleApplication.PRODUCTEURS, new VueIntegrable() {
      @Override
      public Node getNode() {
        return new Label("Gestion des producteurs (à implémenter)");
      }
    });

    // Gestion des clients
    // TODO à implémenter
    mapModuleVue.put(ModuleApplication.CLIENTS, new VueIntegrable() {
      @Override
      public Node getNode() {
        return new Label("Gestion des clients (à implémenter)");
      }
    });
  }

  /**
   * Instanciation des vues et contrôleurs des modules pour un producteur.
   */
  private void modulesProducteur() throws Exception {
    // Obtention du producteur correspondant à l'utilisateur
    DAOProducteur daoProducteur = new DAOProducteur();
    Producteur producteur = daoProducteur.find(utilisateur);

    // Tableau de bord
    // TODO à implémenter
    mapModuleVue.put(ModuleApplication.TABLEAU_DE_BORD, new VueIntegrable() {
      @Override
      public Node getNode() {
        return new Label("Tableau de bord (à implémenter)");
      }
    });

    // Gestion des commandes
    VueRechercheCommande rechercheCommande = new VueRechercheCommande();
    new CtrlRechercheCommande(producteur, rechercheCommande);
    mapModuleVue.put(ModuleApplication.COMMANDES, rechercheCommande);

    // Gestion des tournées
    // TODO à implémenter
    mapModuleVue.put(ModuleApplication.TOURNEES, new VueIntegrable() {
      @Override
      public Node getNode() {
        return new Label("Tournées (à implémenter)");
      }
    });

    // Gestion des véhicules
    // TODO à implémenter
    mapModuleVue.put(ModuleApplication.VEHICULES, new VueIntegrable() {
      @Override
      public Node getNode() {
        return new Label("Gestion des véhicules (à implémenter)");
      }
    });
  }
}
