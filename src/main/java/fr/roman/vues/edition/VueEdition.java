package fr.roman.vues.edition;

import fr.roman.controleurs.edition.CtrlEdition;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.modeles.ChampsModele;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * Classe qui définit la vue pour les contrôleurs d'édition (ajout ou modification de métiers)
 */
public class VueEdition {
    /**
     * Contrôleur de la vue <i>Edition</i>.
     */
    private CtrlEdition ctrl = null;
    /**
     * Le type de la vue : création ou modification
     */
    private TypeEdition typeEdition;
    /**
     * La scène contenant les éléments de la vue
     */
    private Scene scene;
    /**
     * Le titre de la vue.
     */
    private final Label titre;

    /**
     * Conteneur des champs du formulaire.
     */
    private final VBox formulaire;
    /**
     * Conteneur principal de la scène.
     */
    private final BorderPane conteneur;
    /**
     * Conteneur du bas de la vue.
     */
    private final GridPane footer;
    /**
     * Bouton pour valider la saisie
     */
    private final Button btnValider;
    /**
     * Bouton de retour pour annuler la saisie
     */
    private final Button btnRetour;
    /**
     * Carte des champs du formulaire de la vue (clé : nom d'un champ dans l'énum,
     *                                           valeur : un objet {@link Node}.
     */
    private Map<? extends ChampsModele, Node> champsFormulaire;

    /**
     * Construire la vue d'<i>édition</i>.
     */
    public VueEdition(TypeEdition typeEdition) {

        // Définition des éléments de la vue
        this.typeEdition = typeEdition;
        titre = new Label();
        conteneur = new BorderPane();
        formulaire = new VBox();
        footer = new GridPane();
        btnValider = new Button("Valider");
        btnRetour = new Button("Retour");
        structureEdition();
    }

    /**
     * Établir la structure de base de la vue d'<i>édition</i> : créer une {@link Scene} et lui
     * ajouter des éléments (titre, bouton de validation de saisie sans les champs).
     */
    private void structureEdition() {
        scene = new Scene(conteneur);
        // Placement des éléments
        conteneur.setTop(titre);
        conteneur.setCenter(formulaire);
        conteneur.setBottom(footer);
        footer.add(btnValider, 0, 0);
        footer.add(btnRetour, 1, 0);

        btnValider.setOnAction((event) -> ctrl.validerSaisie());
        btnRetour.setOnAction((event) -> ctrl.annulerSaisie());

        // Marges, espacement, alignement
        conteneur.setPadding(new Insets(15));
    }

    /**
     * Affectation du contrôleur de la vue d'édition.
     *
     * @param ctrl Contrôleur à affecter
     */
    public void setCtrl(CtrlEdition ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Définir et ajouter les champs du formulaire qui seront affichés
     *
     * @param champsFormulaire Carte des champs du formulaire de la vue
     *                         (clé : nom d'un champ dans l'énum,
     *                         valeur : un objet {@link Node})
     * @param label le nom du métier qui va être édité
     */
    public void definirChamps(Map <? extends ChampsModele, Node> champsFormulaire, String label) {
        this.champsFormulaire = champsFormulaire;
        this.titre.setText(this.typeEdition.libelle + " : " + label);
        this.champsFormulaire.forEach((nom, noeud) -> formulaire.getChildren().addAll(new Label(nom.toString()),noeud));
    }

    /**
     * Retourne la {@link Scene} contenant la vue d'édition
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retourner la carte des champs du formulaire de la vue (clé : nom d'un champ dans l'énum,
     *                         valeur : un objet {@link Node})
     */
    public Map <? extends ChampsModele, Node> getChamps() {
        return this.champsFormulaire;
    }
}