package fr.roman.vues.edition;

import fr.roman.RoManErreur;
import fr.roman.controleurs.edition.CtrlEdition;
import fr.roman.controleurs.edition.TypeEdition;
import fr.roman.modeles.ChampsModele;
import fr.roman.modeles.TypeChamp;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.CalendarTimeTextField;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
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
     * Liste des champs du formulaire de la vue (clé : nom d'un champ dans l'énum,
     *                                           valeur : un objet {@link Node}.
     */
    private final Map<String, Node> composants = new HashMap<>();

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
        // création des composants graphique
        //signalCtrlEdition(signal);
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

        btnValider.setOnAction((event) -> {
            try {
                ctrl.validerSaisie();
            } catch (Exception e) {
                RoManErreur.afficher(e);
            }
        });
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
     * @param label le nom du métier qui va être édité
     */
    public void definirChamps(String label) {
        this.titre.setText(this.typeEdition.libelle + " : " + label);
        this.composants.forEach((nom, noeud) -> formulaire.getChildren().addAll(new Label(nom),noeud));
    }

    /**
     * Retourne la {@link Scene} contenant la vue d'édition
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Méthode qui permet de gérer les différentes méthodes de création d'élément graphique.
     *
     * @param signal des composants demandés par le contrôleur.
     */
    public void signalCtrlEdition(Map<? extends ChampsModele, TypeChamp> signal) {
        signal.forEach((c,t) -> {
            switch (t.getLibelle()) {
                case TEXTFIELD ->
                    // création d'un "textField".
                        this.composants.put(c.toString(), textField(t));
                case SPINNERDOUBLE ->
                    // création d'un "spinner" de Double.
                        this.composants.put(c.toString(), spinnerDouble(t));
                case SPINNERINT ->
                    // création d'un "spinner" de Integer.
                        this.composants.put(c.toString(), spinnerInteger(t));
                case CALENDEARTIMETEXTFIELD ->
                    // création d'un "calendarTimeTextField".
                        this.composants.put(c.toString(), calendarTimeTextField(t));
                case CALENDEARTEXTFIELD ->
                    // création d'un "calendarTextField".
                        this.composants.put(c.toString(), calendarTextField(t));
                case CHECKBOX ->
                    // création d'une "checkbox"
                        this.composants.put(c.toString(), checkBox(t));
                default -> throw new RuntimeException("L'élément graphique" + t + " est inconnue.");
            }
        });
    }

    private Node textField(TypeChamp t) {
        TextField resultat = new TextField();
        resultat.setDisable(t.isDiseble());
        resultat.setDisable(true);
        if(t.getRegex() == null) {
            resultat.setTextFormatter(new TextFormatter<>(change -> {
                if (!change.getControlNewText().matches(t.getRegex())) {
                    return null;
                } else {
                    return change;
                }
            }));
        }
        if(typeEdition == TypeEdition.MODIFICATION){
            resultat.setText(t.getValeur());
        }
        return resultat;
    }

    private Node spinnerDouble(TypeChamp t) {
        Spinner<Double> resultat = new Spinner<>();
        resultat.setDisable(t.isDiseble());
        resultat.setEditable(true);
        resultat.setValueFactory(new SpinnerValueFactory
            .DoubleSpinnerValueFactory(t.getMinDouble(), t.getMaxDouble(), t.getInitDouble()));
        if(t.getRegex() != null){
            resultat.getEditor().setTextFormatter(new TextFormatter<>(change -> {
                if (!change.getControlNewText().matches(t.getRegex())) {
                    return null;
                } else {
                    return change;
                }
            }));
        }
        resultat.setTooltip(new Tooltip(t.getPlaceholder()));
        return resultat;
    }
    private Node spinnerInteger(TypeChamp t) {
        Spinner<Integer> resultat = new Spinner<>();
        resultat.setDisable(t.isDiseble());
        resultat.setEditable(true);
        resultat.setValueFactory(new SpinnerValueFactory
            .IntegerSpinnerValueFactory(t.getMinInt(), t.getMaxInt(), t.getInitInt()));
        if(t.getRegex()!= null){
            resultat.getEditor().setTextFormatter(new TextFormatter<>(change -> {
                if (!change.getControlNewText().matches(t.getRegex())) {
                    return null;
                } else {
                    return change;
                }
            }));
        }
        return resultat;
    }

    private Node calendarTimeTextField(TypeChamp t) {
        CalendarTimeTextField resultat = new CalendarTimeTextField();
        resultat.setDisable(t.isDiseble());
        resultat.setLocale(Locale.FRANCE);
        resultat.setMinuteStep(15);
        if(!(t.getRegex().isEmpty())){
            resultat.setDateFormat(new SimpleDateFormat(t.getRegex()));
        }
        if(!(t.getCalendar().getAvailableCalendarTypes().isEmpty())){
            resultat.setCalendar(t.getCalendar());
        }
        return resultat;
    }

    public Node calendarTextField(TypeChamp t) {
        CalendarTextField resultat = new CalendarTextField();
        resultat.setDisable(t.isDiseble());
        resultat.setShowTime(true);
        resultat.autosize();
        resultat.setLocale(Locale.FRANCE);
        resultat.setDateFormat(new SimpleDateFormat(t.getRegex()));
        resultat.setAllowNull(true);
        if(!(t.getCalendar().getAvailableCalendarTypes().isEmpty())){
            resultat.setCalendar(t.getCalendar());
        }
        return resultat;
    }
    private Node checkBox(TypeChamp t) {
        CheckBox resultat = new CheckBox();
        resultat.setDisable(t.isDiseble());
        resultat.setSelected(t.isValeurBool());
        return resultat;
    }
}
