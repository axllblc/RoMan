package fr.roman.vues.composants;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

/**
 * Classe permettant la création de boutons définis par un label, un logo <i>(facultatif)</i> et
 * une fonction de callback exécutée lors du clic sur ce bouton. Elle permet également de créer des
 * entrées de menu contextuel.
 *
 * @author Axel Leblanc
 */
public class BoutonAction {
  /**
   * Label affiché sur le bouton.
   */
  private final String label;
  /**
   * Logo affiché sur le bouton.
   */
  private ImageView logo;
  /**
   * Fonction de callback exécutée lors du clic sur le bouton.
   */
  private final Callback action;

  /**
   * Définir le label et l'action du bouton à créer.
   *
   * @param label Texte affiché sur le bouton
   * @param action Fonction de callback appelée lors du clic sur le bouton
   */
  public BoutonAction(String label, Callback action) {
    this.label = label;
    this.action = action;
  }

  /**
   * Définir le label, le logo et l'action du bouton à créer.
   *
   * @param label Texte affiché sur le bouton
   * @param urlLogo URL du logo à afficher sur le bouton
   * @param action Fonction de callback appelée lors du clic sur le bouton
   */
  public BoutonAction(String label, String urlLogo, Callback action) {
    this.label = label;
    this.logo = new ImageView(urlLogo);
    this.action = action;
  }

  /**
   * Instancier un objet {@link Button} et lui définir son label, son logo (le cas échéant) et sa
   * fonction de callback.
   *
   * @return Objet {@link Button}
   *
   * @see #asMenuItem()
   */
  public Button asButton() {
    Button button = new Button(label);

    if (logo != null) {
      button.setGraphic(logo);
    }

    button.setOnAction(event -> action.call());

    return button;
  }

  /**
   * Instancier un objet {@link MenuItem} et lui définir son label, son logo (le cas échéant) et sa
   * fonction de callback.
   *
   * @return Objet {@link MenuItem}
   *
   * @see #asButton()
   */
  public MenuItem asMenuItem() {
    MenuItem menuItem = new MenuItem(label);

    if (logo != null) {
      menuItem.setGraphic(logo);
    }

    menuItem.setOnAction(event -> action.call());

    return menuItem;
  }

  /**
   * Interface fonctionnelle définissant la fonction de callback exécutée lors du clic sur le
   * bouton.
   */
  @FunctionalInterface
  public interface Callback {
    /**
     * Fonction de callback exécutée lors du clic sur le bouton.
     */
    void call();
  }
}
