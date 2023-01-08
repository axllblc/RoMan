package fr.roman.vues.composants;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

/**
 * {@link TableColumn} prévu pour afficher des icônes.
 *
 * @param <S> Le type d'objets contenus dans le tableau {@link TableView}
 *            {@code (i.e. S == TableView<S>)}
 * @param <T> Le type d'objets contenus dans la colonne {@link TableColumn}
 *
 * @author Axel Leblanc
 */
public class IconTableColumn<S, T> extends TableColumn<S, T> {
  /**
   * Taille des icônes affichées dans le tableau : 16 pixels.
   */
  public static final double TAILLE_ICONE = 16;
  /**
   * Largeur de la colonne : 20 pixels.
   */
  public static final double TAILLE_COLONNE = 20;

  /**
   * Constructeur prenant en paramètre une chaîne de caractères affichée sous forme d'infobulle,
   * sur l'en-tête de la colonne.
   *
   * @param tooltip Texte de l'infobulle affichée sur l'en-tête de la colonne
   */
  public IconTableColumn(String tooltip) {
    super();

    setPrefWidth(TAILLE_COLONNE);
    setResizable(false);
    setReorderable(false);

    // Affichage de l'infobulle
    Label label = new Label();
    label.setPrefWidth(TAILLE_COLONNE);
    label.setTooltip(new Tooltip(tooltip));
    setGraphic(label);
  }
}
