package fr.roman.controleurs.edition;

import fr.roman.RoManErreur;
import fr.roman.dao.*;
import fr.roman.modeles.*;
import fr.roman.modeles.Adresse;
import fr.roman.vues.edition.LibelleChamp;
import fr.roman.vues.edition.TypeChamp;
import fr.roman.vues.edition.VueEdition;

public class CtrlEditionAdresse extends CtrlEdition<Adresse, Adresse.Champs> {

    // Les DAOs nécessaire pour le fonctionnement du contrôleur
    DAOAdresse daoAdresse;

    {
        try {
            daoAdresse = new DAOAdresse();
        } catch (Exception e) {
            RoManErreur.afficher(e);
        }
    }

    /**
     * Constructeur du contrôleur
     * @param adresse L'adresse dans laquelle les stockés les informations de la vue
     *                 (vide si typeEdition = CREATION)
     * @param vueEdition La vue (d'édition) que la classe contrôle
     * @param typeEdition Le type de contrôleur : création ou modification
     * @param role Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
     */
    public CtrlEditionAdresse(Adresse adresse, VueEdition vueEdition,
                              TypeEdition typeEdition, Role role) {
        super(adresse, vueEdition, typeEdition, role);
    }

    /**
     * Classe à implémenter qui construit les objets {@link TypeChamp} à mettre dans la vue,
     *  c.-à-d. les champs du formulaire (préremplis le cas échéant)
     */
    @Override
    public void chargerChamps() {
        double[] valeurDoubleTab1x2;
        int valeurInt;

        // idAdresse
        TypeChamp idAdresse = new TypeChamp(LibelleChamp.TEXTFIELD);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idAdresse.setValeur(String.valueOf(getModele().getIdAdresse()));
        }
        getChampsFormulaire().put(Adresse.Champs.idAdresse, idAdresse);

        // coordonneesGPS
        /*
        valeurDoubleTab1x2 = new double[]{};
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            valeurDoubleTab1x2 = getModele().getCoordonneesGPS();
        }
        TypeChamp coordonneesGPS = new TypeChamp(LibelleChamp.TEXTFIELD);
        coordonneesGPS.setDoubleTab1x2(valeurDoubleTab1x2);
        getChampsFormulaire().put(Adresse.Champs.coordonneesGPS, coordonneesGPS);
        */

        valeurDoubleTab1x2 = new double[]{};
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            valeurDoubleTab1x2 = getModele().getCoordonneesGPS();
        }
        TypeChamp coordonneesGPS = new TypeChamp(LibelleChamp.DOUBLESPINNERDOUBLE);
        coordonneesGPS.setDoubleSpinnerDouble(-180, 180, valeurDoubleTab1x2[0],valeurDoubleTab1x2[1]);
        getChampsFormulaire().put(Adresse.Champs.coordonneesGPS, coordonneesGPS);

        // libelle
        TypeChamp libelle = new TypeChamp(LibelleChamp.TEXTFIELD);
        libelle.setRegex(".{0,50}");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            libelle.setValeur(getModele().getLibelle());
        }
        getChampsFormulaire().put(Adresse.Champs.libelle, libelle);

        // numeroVoie
        valeurInt = 1;
        TypeChamp numeroVoie = new TypeChamp(LibelleChamp.SPINNERINT);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            valeurInt = getModele().getNumeroVoie();
            numeroVoie.setSpinnerInt(1, 10000, getModele().getNumeroVoie());
        }
        numeroVoie.setPlaceholder("en kg");
        numeroVoie.setSpinnerInt(1, 10000, valeurInt);
        getChampsFormulaire().put(Adresse.Champs.numeroVoie, numeroVoie);

        // complementNumero
        TypeChamp complementNumero = new TypeChamp(LibelleChamp.TEXTFIELD);
        complementNumero.setRegex(".{0,5}");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            complementNumero.setValeur(getModele().getComplementNumero());
        }
        getChampsFormulaire().put(Adresse.Champs.complementNumero, complementNumero);

        // voie
        TypeChamp voie = new TypeChamp(LibelleChamp.TEXTFIELD);
        voie.setRegex(".{0,100}");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            voie.setValeur(getModele().getVoie());
        }
        getChampsFormulaire().put(Adresse.Champs.voie, voie);

        // complementAdresse
        TypeChamp complementAdresse = new TypeChamp(LibelleChamp.TEXTFIELD);
        voie.setRegex(".{0,30}");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            complementAdresse.setValeur(getModele().getComplementAdresse());
        }
        getChampsFormulaire().put(Adresse.Champs.complementAdresse, complementAdresse);

        // codePostal
        valeurInt = 1;
        if(getTypeEdition() == TypeEdition.MODIFICATION) {
            valeurInt = getModele().getCodePostal();
        }
        TypeChamp codePostal = new TypeChamp(LibelleChamp.SPINNERINT);
        codePostal.setPlaceholder("en kg");
        codePostal.setSpinnerInt(0,99999, 0);
        codePostal.setValeurInt(valeurInt);
        getChampsFormulaire().put(Adresse.Champs.codePostal, codePostal);

        // ville
        TypeChamp ville = new TypeChamp(LibelleChamp.TEXTFIELD);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            ville.setValeur(getModele().getVille());
        }
        getChampsFormulaire().put(Adresse.Champs.ville, ville);
    }

    /**
     * Classe appelée par le bouton de validation du formulaire pour
     *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
     * @return L'objet Adresse correspondant à ce qui a été ajouté dans la base de données
    */
    @Override
    public Adresse validerSaisie() throws Exception {

        // libelle
        getModele().setLibelle(getChampsFormulaire().get(Adresse.Champs.libelle).getValeur());

        // coordonneesGPS
        getModele().setCoordonneesGPS(getChampsFormulaire().get(Adresse.Champs.coordonneesGPS).getValeurDoubleTab1x2());

        // numeroVoie
        getModele().setNumeroVoie(getChampsFormulaire().get(Adresse.Champs.numeroVoie).getValeurInt());

        // complementNumero
        getModele().setComplementNumero(getChampsFormulaire().get(Adresse.Champs.complementNumero).getValeur());

        // voie
        getModele().setVoie(getChampsFormulaire().get(Adresse.Champs.libelle).getValeur());

        // complementAdresse
        getModele().setComplementAdresse(getChampsFormulaire().get(Adresse.Champs.complementAdresse).getValeur());

        // codePostal
        getModele().setCodePostal(getChampsFormulaire().get(Adresse.Champs.codePostal).getValeurInt());

        Adresse adresse = null;
        switch (getTypeEdition()){
            case CREATION -> adresse = daoAdresse.insert(getModele());
            case MODIFICATION -> {  if(daoAdresse.update(getModele()))
                                        adresse = getModele();
                                 }
        }
        return adresse;
    }
}
