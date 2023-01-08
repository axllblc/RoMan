package fr.roman.controleurs.actions;

import fr.roman.RoManErreur;
import fr.roman.controleurs.edition.CtrlEditionTournee;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.dao.DAOProducteur;
import fr.roman.modeles.Tournee;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import fr.roman.vues.edition.VueEdition;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Classe regroupant les actions possibles pour la gestion des tournées.
 */
public abstract class ActionsTournees {
    public static void creerTournee(Utilisateur u){
        try {
            Tournee Tournee = new Tournee();
            if (u.getRole() == Role.PRODUCTEUR) {
                DAOProducteur daoProducteur = new DAOProducteur();
                LinkedHashMap<Producteur.Champs, String> critere = new LinkedHashMap<>();
                critere.put(Producteur.Champs.idUtilisateur, String.valueOf(u.getIdUtilisateur()));
                ArrayList<Producteur> prod = daoProducteur.find(critere);
                if (!prod.isEmpty()){
                    Producteur producteur = prod.get(0);
                    Tournee.setProducteur(producteur);
                }
                else {
                    throw new Exception(new Throwable("Le producteur est inconnu"));
                }
            }
            VueEdition vue = new VueEdition(TypeEdition.CREATION);
            CtrlEditionTournee ctrl = new CtrlEditionTournee(Tournee, vue, TypeEdition.CREATION, u.getRole());
            vue.show();
        } catch (Exception e) {
            RoManErreur.afficher(e);
        }
    }
    public static void modifierTournee(Tournee Tournee, Role role){
        VueEdition vue = new VueEdition(TypeEdition.MODIFICATION);
        CtrlEditionTournee ctrl = new CtrlEditionTournee(Tournee, vue, TypeEdition.MODIFICATION, role);
        vue.show();
    }
}
