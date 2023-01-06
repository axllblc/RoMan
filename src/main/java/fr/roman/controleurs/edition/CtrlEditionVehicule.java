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
        TextField idVehicule = new TextField();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idVehicule.setText(String.valueOf(getModele().getIdVehicule()));
        }
        // getChampsFormulaire().put(Vehicule.Champs.idVehicule, idVehicule);

        // immatriculation
        TextField immatriculation = new TextField();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            immatriculation.setText(getModele().getImmatriculation());
        }
        immatriculation.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("[0-9A-Za-z]{0,7}")) {
                return null;
            } else {
                change.setText(change.getText().toUpperCase());
                return change;
            }
        }));
        // getChampsFormulaire().put(Vehicule.Champs.immatriculation, immatriculation);

        // poidsMax
        TextField poidsMax = new TextField();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            poidsMax.setText(String.valueOf(getModele().getPoidsMax()));
        }
        poidsMax.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d{0,5}")) {
                return null;
            } else {
                return change;
            }
        }));
        // getChampsFormulaire().put(Vehicule.Champs.poidsMax, poidsMax);

        // libelle
        TextField libelle = new TextField();
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            libelle.setText(getModele().getLibelle());
        }
        libelle.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches(".{0,50}")) {
                return null;
            } else {
                return change;
            }
        }));
        // getChampsFormulaire().put(Vehicule.Champs.libelle, libelle);

        // producteur
        TextField idProducteur = new TextField();
        idProducteur.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return change;
            }
        }));
        if(getTypeEdition() == TypeEdition.MODIFICATION || getRole() == Role.PRODUCTEUR){
            idProducteur.setText(String.valueOf(getModele().getProducteur().getIdProducteur()));
        }
        // getChampsFormulaire().put(Vehicule.Champs.idProducteur, idProducteur);

        // getVueEdition().definirChamps(getChampsFormulaire(), "Véhicule");
    }

    /**
     * Classe appelée par le bouton de validation du formulaire pour
     *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
     * @return L'objet métier correspondant à ce qui a été ajouté dans la base de données
    @Override
    public Vehicule validerSaisie() throws Exception {

        champsFormulaire = (Map<Vehicule.Champs, Node>) getVueEdition().getChamps();

        // immatriculation
        getModele().setImmatriculation(((TextField) champsFormulaire.get(Vehicule.Champs.immatriculation)).getText());

        // poids
        getModele().setPoidsMax(Integer.parseInt(((TextField) champsFormulaire.get(Vehicule.Champs.poidsMax)).getText()));

        // libelle
        getModele().setLibelle(((TextField) champsFormulaire.get(Vehicule.Champs.libelle)).getText());

        // idProducteur
        Producteur p = daoProducteur.findById(Integer.parseInt(((TextField) champsFormulaire
                                                     .get(Vehicule.Champs.idProducteur)).getText()));
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
    */
}
