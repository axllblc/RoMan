package fr.roman.controleurs.edition;

import fr.roman.RoManErreur;
import fr.roman.dao.DAOAdresse;
import fr.roman.dao.DAOClient;
import fr.roman.modeles.*;
import fr.roman.vues.edition.VueEdition;

public class CtrlEditionClient extends CtrlEdition<Client, Client.Champs> {

    // Les DAOs nécessaire pour le fonctionnement du contrôleur
    DAOClient daoClient;
    DAOAdresse daoAdresse;

    {
        try {
            daoClient = new DAOClient();
            daoAdresse = new DAOAdresse();
        } catch (Exception e) {
            RoManErreur.afficher(e);
        }
    }

    /**
     * Constructeur du contrôleur
     * @param Client La Client dans laquelle des stockés les informations de la vue
     *                 (vide si typeEdition = CREATION)
     * @param vueEdition La vue (d'édition) que la classe contrôle
     * @param typeEdition Le type de contrôleur : création ou modification
     * @param role Le rôle de l'utilisateur qui verra la vue (cf {@link Role})
     */
    public CtrlEditionClient(Client Client, VueEdition vueEdition,
                             TypeEdition typeEdition, Role role) {
        super(Client, vueEdition, typeEdition, role);
    }

    /**
     * Classe à implémenter qui construit les objets {@link TypeChamp} à mettre dans la vue,
     *  c.-à-d. les champs du formulaire (préremplis le cas échéant)
     */
    @Override
    public void chargerChamps() {
        int valeurInt;
        boolean valeurBool;

        // idClient
        TypeChamp idClient = new TypeChamp(LibelleChamp.TEXTFIELD);
        idClient.setRegex("\\d*");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            idClient.setValeur(String.valueOf(getModele().getIdClient()));
        }
        getChampsFormulaire().put(Client.Champs.idClient, idClient);

        // nom
        TypeChamp nom = new TypeChamp(LibelleChamp.TEXTFIELD);
        nom.setRegex(".{0,50}");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            nom.setValeur(getModele().getNom());
        }
        getChampsFormulaire().put(Client.Champs.nom, nom);

        // tel
        TypeChamp tel = new TypeChamp(LibelleChamp.TEXTFIELD);
        tel.setRegex("[+\\d]{0,10}");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            tel.setValeur(getModele().getTel());
        }
        getChampsFormulaire().put(Client.Champs.tel, tel);

        // siret
        TypeChamp siret = new TypeChamp(LibelleChamp.TEXTFIELD);
        siret.setRegex("[\\d]{0,14}");
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            siret.setValeur(getModele().getTel());
        }
        getChampsFormulaire().put(Client.Champs.siret, siret);

        // email
        TypeChamp email = new TypeChamp(LibelleChamp.TEXTFIELD);
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            email.setValeur(getModele().getEmail());
        }
        getChampsFormulaire().put(Client.Champs.email, email);

        // particulier
        valeurBool = false;
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            valeurBool = (getModele().isParticulier());
        }
        TypeChamp particulier = new TypeChamp(LibelleChamp.CHECKBOX);
        particulier.setValeurBool(valeurBool);
        getChampsFormulaire().put(Client.Champs.particulier, particulier);

        // Adresse
        valeurInt = 0;
        if(getTypeEdition() == TypeEdition.MODIFICATION){
            valeurInt = getModele().getAdresse().getIdAdresse();
        }
        TypeChamp adresse = new TypeChamp(LibelleChamp.SPINNERINT);
        adresse.setSpinnerInt(0, 9999999, valeurInt);
        adresse.setRegex("\\d{1,50}");
        getChampsFormulaire().put(Client.Champs.idAdresse, adresse);

        // TODO : création de la vue.
    }

    /**
     * Classe appelée par le bouton de validation du formulaire pour
     *  effectuer l'ajout ou la modification dans la base de données des champs renseignés/modifiés.
     * @return L'objet Client correspondant à ce qui a été ajouté dans la base de données
    */
    @Override
    public Client validerSaisie() throws Exception {

        // nom
        getModele().setNom(getChampsFormulaire().get(Client.Champs.nom).getValeur());

        // tel
        getModele().setTel(getChampsFormulaire().get(Client.Champs.tel).getValeur());

        // siret
        getModele().setSiret(getChampsFormulaire().get(Client.Champs.siret).getValeur());

        // email
        getModele().setEmail(getChampsFormulaire().get(Client.Champs.email).getValeur());

        // particulier
        getModele().setParticulier(getChampsFormulaire().get(Client.Champs.particulier)
                .isValeurBool());

        // idAdresse
        getModele().setAdresse(daoAdresse.findById(
                getChampsFormulaire().get(Client.Champs.idAdresse).getValeurInt()));

        Client client = null;
        switch (getTypeEdition()){
            case CREATION -> client = daoClient.insert(getModele());
            case MODIFICATION -> {  if(daoClient.update(getModele()))
                                        client = getModele();
                                 }

        }
        return client;
    }
}
