package fr.roman.controleurs.edition;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOClient;
import fr.roman.dao.DAOVehicule;
import fr.roman.dao.DAOProducteur;
import fr.roman.modeles.*;
import fr.roman.vues.edition.VueEdition;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.*;

public class CtrlEditionVehicule extends CtrlEdition<Vehicule, Vehicule.Champs> {

    // Les DAOs nécessaire pour le fonctionnement du contrôleur
    DAOVehicule daoVehicule;
    DAOProducteur daoProducteur;
    DAOClient daoClient;

    {
        try {
            daoVehicule = new DAOVehicule();
            daoClient = new DAOClient();
            daoProducteur = new DAOProducteur();
        } catch (Exception e) {
            RoManErreur.afficher(e);
        }
    }

    /**
     * Constructeur du contrôleur
     * @param Vehicule Le Vehicule dans laquelle des stockés les informations de la vue
     *                 (si role = producteur, on inclut déjà l'instance du producteur,
     *                 même pour typeEdition = CREATION)
     * @param vueEdition La vue (d'édition) que la classe contrôle
     * @param typeEdition Le type de contrôleur : création ou modification
     * @param role Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
     */
    public CtrlEditionVehicule(Vehicule Vehicule, VueEdition vueEdition, TypeEdition typeEdition, Role role) {
        super(Vehicule, vueEdition, typeEdition, role);
    }

    /**
     * Classe à implémenter qui construit les objets {@link Node} à mettre dans la vue,
     *  c.-à-d. les champs du formulaire (préremplis le cas échéant)
     */
    @Override
    public void chargerChamps() {
        // idVehicule
        TypeChamp idVehicule = new TypeChamp(LibelleChamp.TEXTFIELD);
        idVehicule.setRegex("\\d*");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idVehicule.setValeur(String.valueOf(getModele().getIdVehicule()));
        }
        getChampsFormulaire().put(Vehicule.Champs.idVehicule, idVehicule);

        // immatriculation
        TypeChamp immatriculation = new TypeChamp(LibelleChamp.TEXTFIELD);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            immatriculation.setValeur(getModele().getImmatriculation());
        }
        immatriculation.setRegex("[0-9A-Za-z]{0,7}");
        getChampsFormulaire().put(Vehicule.Champs.immatriculation, immatriculation);

        // poidsMax
        TypeChamp poidsMax = new TypeChamp(LibelleChamp.SPINNERINT);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            poidsMax.setValeurInt(getModele().getPoidsMax());
            poidsMax.setSpinnerInt(1,10000,getModele().getPoidsMax());
        }
        poidsMax.setRegex("\\d{0,5}");
        getChampsFormulaire().put(Vehicule.Champs.poidsMax, poidsMax);

        // libelle
        TypeChamp libelle = new TypeChamp(LibelleChamp.TEXTFIELD);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            libelle.setValeur(getModele().getLibelle());
        }
        libelle.setRegex(".{0,50}");
        getChampsFormulaire().put(Vehicule.Champs.libelle, libelle);

        // producteur
        TypeChamp idProducteur = new TypeChamp(LibelleChamp.SPINNERINT);
        int idPro = 0;
        if(getTypeEdition() == TypeEdition.MODIFICATION && getModele().getProducteur() != null){
            idPro = getModele().getProducteur().getIdProducteur();
        }
        getChampsFormulaire().put(Vehicule.Champs.idProducteur, idProducteur);

        // TODO : création de la vue.
    }

    /**
     * Classe appelée par le bouton de validation du formulaire pour
     *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
     * @return L'objet métier correspondant à ce qui a été ajouté dans la base de données
     */
    @Override
    public Vehicule validerSaisie() throws Exception {

        // immatriculation
        getModele().setImmatriculation(champsFormulaire.get(Vehicule.Champs.immatriculation).getValeur());

        // poids
        getModele().setPoidsMax(champsFormulaire.get(Vehicule.Champs.poidsMax).getValeurInt());

        // libelle
        getModele().setLibelle(champsFormulaire.get(Vehicule.Champs.libelle).getValeur());

        // idProducteur
        Producteur p = daoProducteur.findById(champsFormulaire.get(Vehicule.Champs.idProducteur).getValeurInt());
        getModele().setProducteur(p);

        Vehicule Vehicule = null;
        switch (getTypeEdition()){
            case CREATION -> Vehicule = daoVehicule.insert(getModele());
            case MODIFICATION -> {  if(daoVehicule.update(getModele()))
                                        Vehicule = getModele();
                                 }

        }
        return Vehicule;
    }
}
