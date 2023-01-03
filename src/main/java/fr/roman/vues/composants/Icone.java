package fr.roman.vues.composants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Énumération des icônes utilisées dans les vues de l'application.
 *
 * <p>La méthode {@link #getImage()} permet d'obtenir un objet {@link Image} JavaFX.</p>
 * <p>La classe {@link FabriqueIcone} permet de créer un objet {@link ImageView} et d'ajuster la
 * taille de l'image à l'affichage.</p>
 *
 * @see Image
 * @see FabriqueIcone
 *
 * @author Axel Leblanc
 */
public enum Icone {
  COMPTE("file:src/main/resources/icons/account_circle_24.png"),
  ATTENTION("file:src/main/resources/icons/warning_24.png"),
  OK("file:src/main/resources/icons/ok_24.png");

  private final Image image;

  /**
   * Constructeur d'un élément de l'énumération {@link Icone}. Il prend en paramètre l'URL de
   * l'image.
   *
   * @param url URL de l'image
   */
  Icone(String url) {
    this.image = new Image(url);
  }

  /**
   * Retourne un objet {@link Image} JavaFX.
   *
   * @return Objet {@link Image}
   */
  public Image getImage() {
    return image;
  }
}
