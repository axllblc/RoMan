package fr.roman.controleurs.authentification;

import fr.roman.RoManErreur;
import fr.roman.controleurs.accueil.CtrlAccueil;
import fr.roman.controleurs.comptes.OutilsMotDePasse;
import fr.roman.dao.DAOUtilisateur;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.accueil.VueAccueil;
import fr.roman.vues.authentification.VueAuthentification;
import java.util.Arrays;
import java.util.Base64;

/**
 * Contrôleur de la partie Authentification.
 */
public class CtrlAuthentification {
  /**
   * Instance de VueAuthentification.
   */
  private final VueAuthentification vueAuthentification;

  /**
   * Constructeur de la classe qui nous permettra de faire les traitements de la partie
   * authentification et de gérer sa logique.
   *
   * @param vueAuthentification Instance de la vue authentification.
   */
  public CtrlAuthentification(VueAuthentification vueAuthentification) {
    this.vueAuthentification = vueAuthentification;
  }

  /**
   * Verifier si l'utilisateur est un utilisateur valide.
   *
   * <ul>
   *   <li>S'il est valide on renvoie vers l'accueil</li>
   *   <li>S'il ne l'est pas on affiche un message d'erreur</li>
   * </ul>
   */
  public void verify() {
    // Récupération du nom d'utilisateur et du mot de passe saisis dans la vue
    String nomUtilisateur = vueAuthentification.getNomUtilisateur();
    String mdp = vueAuthentification.getPassword();

    Utilisateur u = authentification(nomUtilisateur, mdp);

    if (u == null) {
      this.vueAuthentification.erreurSaisie();
    } else {
      // Instanciation et affichage de l'accueil
      VueAccueil vueAccueil = new VueAccueil();
      new CtrlAccueil(u, vueAccueil);
      vueAuthentification.close();
      vueAccueil.show();
    }
  }

  /**
   * Authentifier l'utilisateur.
   *
   * @param nomUtilisateur Le nom d'utilisateur
   * @param mdp Le mot de passe renseigné (non chiffré)
   * @return Un objet utilisateur correspondant au nom d'utilisateur.
   *         Renvoie null si le nom d'utilisateur et/ou le mot de passe est incorrect.
   */
  public static Utilisateur authentification(String nomUtilisateur, String mdp) {
    try {
      DAOUtilisateur daoU = new DAOUtilisateur();
      Utilisateur u = daoU.findByNomUtilisateur(nomUtilisateur);

      if (u != null) {
        // Si le nom d'utilisateur existe
        /* Note : on utilise Base64 pour convertir le mot de passe (chaine de caractère) en tableau
           d'octets */
        if (Arrays.equals(
            OutilsMotDePasse.chiffrerMDP(mdp, u.getSel()),
            Base64.getDecoder().decode(u.getMdp()))
            ) {
          // Et si le mot de passe est correct, on retourne l'objet Utilisateur
          return u;
        }
      }
      //Sinon on ne renvoie rien
      return null;
    } catch (Exception e) {
      // En cas d'erreur pour le processus d'authentification, on ne renvoie rien
      RoManErreur.afficher(e);
      e.printStackTrace();
      return null;
    }
  }
}
