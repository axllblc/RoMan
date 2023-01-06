package fr.roman.controleurs.edition;

import fr.roman.modeles.ChampsModele;
import fr.roman.modeles.Modele;
import fr.roman.modeles.Role;
import fr.roman.modeles.TypeChamp;
import fr.roman.vues.edition.VueEdition;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe abstraite qui définit le contrôleur des vues d'édition (ajout ou modification de métiers)
 * @param <M> Un métier qui étends {@link Modele}
 * @param <C> Une énumération de ses champs, qui étends {@link ChampsModele}
 */
public abstract class CtrlEdition<M extends Modele, C extends Enum<?> & ChampsModele> {

    /**
     * Le type de controleur : création ou modification
     */
    private final TypeEdition typeEdition;
    /**
     * Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
     */
    private final Role role;
    /**
     * La vue (d'édition) que la classe contrôle
     */
    private final VueEdition vueEdition;
    /**
     * Le métier dans lequel des stockés les informations de la vue (vide si typeEdition = CREATION)
     */
    private M modele;
    /**
     * Carte des champs du formulaire de la vue (clé : nom d'un champ dans l'énum, valeur : un objet {@link TypeChamp}
     */
    Map<C, TypeChamp> champsFormulaire;

    /**
     * Constructeur du contrôleur
     * @param m Le métier dans lequel des stockés les informations de la vue (vide si typeEdition = CREATION)
     * @param vueEdition La vue (d'édition) que la classe contrôle
     * @param typeEdition Le type de contrôleur : création ou modification
     * @param role Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
     */
    CtrlEdition(M m, VueEdition vueEdition, TypeEdition typeEdition, Role role) {
        this.modele = m;
        this.vueEdition = vueEdition;
        this.typeEdition = typeEdition;
        this.role = role;

        vueEdition.setCtrl(this);

        champsFormulaire = new LinkedHashMap<>();
        /* L'implémentation LinkedHashMap a été choisie, car les nœuds sont dans l'ordre dans lequel
         * les champs ont été insérées. */

        chargerChamps();
        accesChamps();
    }

    /**
     * Classe à implémenter qui construit les objets {@link TypeChamp} à mettre dans la vue,
     *  c.-à-d. les champs du formulaire (préremplis le cas échéant)
     */
    abstract void chargerChamps();

    // accesChamps
    /**
     * Classe permettant de restreindre l'accessibilité (la possibilité de modifier)
     *  les champs du formulaire selon le {@link Role rôle} de l'utilisateur
    */
    void accesChamps(){
        switch (getRole()) {
            case ADMINISTRATEUR -> getChampsFormulaire().forEach((nom, typeChamp) ->
                    typeChamp.setDisable(!nom.isModifAdmin()));
            case PRODUCTEUR -> getChampsFormulaire().forEach((nom, typeChamp) ->
                    typeChamp.setDisable(!nom.isModifProd()));
            case ROOT -> getChampsFormulaire().forEach((nom, typeChamp) ->
                    typeChamp.setDisable(nom.isId()));
        }
    }

    /**
     * Classe abstraite appelée par le bouton de validation du formulaire pour
     *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
     * @return L'objet métier correspondant à ce qui a été ajouté dans la base de données
    */
    public abstract M validerSaisie() throws Exception;
    
    /**
     * Classe abstraite appelée par le bouton de retour du formulaire pour
     *  annuler la saisie.
     */
    public void annulerSaisie() {
    }
    public TypeEdition getTypeEdition() {
        return typeEdition;
    }

    public VueEdition getVueEdition() {
        return vueEdition;
    }

    public M getModele() {
        return modele;
    }

    public Role getRole() {
        return role;
    }
    public Map<C, TypeChamp> getChampsFormulaire() {
        return champsFormulaire;
    }
}
