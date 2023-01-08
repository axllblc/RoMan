package fr.roman.controleurs.actions;

import fr.roman.RoManErreur;
import fr.roman.controleurs.edition.CtrlEditionCommande;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.dao.DAOProducteur;
import fr.roman.modeles.Commande;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.edition.VueEdition;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Classe regroupant les actions possibles pour la gestion des commandes.
 */
public abstract class ActionsCommandes {
    public static void creerCommande(Utilisateur u){
        try {
            Commande commande = new Commande();
            if (u.getRole() == Role.PRODUCTEUR) {
                DAOProducteur daoProducteur = new DAOProducteur();
                LinkedHashMap<Producteur.Champs, String> critere = new LinkedHashMap<>();
                critere.put(Producteur.Champs.idUtilisateur, String.valueOf(u.getIdUtilisateur()));
                ArrayList<Producteur> prod = daoProducteur.find(critere);
                if (!prod.isEmpty()){
                    Producteur producteur = prod.get(0);
                    commande.setProducteur(producteur);
                }
                else {
                    throw new Exception(new Throwable("Le producteur est inconnu"));
                }
            }
            VueEdition vue = new VueEdition(TypeEdition.CREATION);
            CtrlEditionCommande ctrl = new CtrlEditionCommande(commande, vue, TypeEdition.CREATION, u.getRole());
            vue.show();
        } catch (Exception e) {
            RoManErreur.afficher(e);
        }
    }
    public static void modifierCommande(Commande commande, Role role){
        VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
        CtrlEditionCommande ctrl = new CtrlEditionCommande(commande, vue, TypeEdition.MODIFICATION, role);
        vue.show();
    }

}
