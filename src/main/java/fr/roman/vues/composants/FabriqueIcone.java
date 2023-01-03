package fr.roman.vues.composants;

import javafx.scene.image.ImageView;

/**
 * <b>Fabrique d'icônes :</b> crée un objet {@link ImageView} à partir d'un élément de
 * {@link Icone} et définit la taille à l'affichage (facultatif).
 *
 * @see Icone
 * @see ImageView
 *
 * @author Axel Leblanc
 */
public class FabriqueIcone {
  /**
   * Crée un objet {@link ImageView} à partir d'un élément de {@link Icone}.
   *
   * @param icone Un élément de {@link Icone}
   * @return Un objet {@link ImageView}
   * 
   * @see #get(Icone, double) 
   */
  public static ImageView get(Icone icone) {
    return new ImageView(icone.getImage());
  }

  /**
   * Crée un objet {@link ImageView} à partir d'un élément de {@link Icone} et définit sa taille 
   * à l'affichage.
   *
   * @param icone Un élément de {@link Icone}
   * @param taille La taille de l'objet {@link ImageView} retourné
   * @return Un objet {@link ImageView}
   * 
   * @see #get(Icone) 
   */
  public static ImageView get(Icone icone, double taille) {
    ImageView iv = get(icone);
    
    iv.setFitWidth(taille);
    iv.setPreserveRatio(true);
    iv.setSmooth(true);
    iv.setCache(true);
    
    return iv;
  }
}
