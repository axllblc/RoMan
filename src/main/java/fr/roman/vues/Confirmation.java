package fr.roman.vues;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

/**
 * Classe permettant l'affichage d'une boîte de dialogue de confirmation.
 */
public class Confirmation {
  /**
   * Afficher une boîte de dialogue de confirmation. Celle-ci est modale (l'utilisateur ne peut
   * pas interagir avec les autres fenêtres de l'application tant qu'il n'a pas cliqué sur le
   * bouton de confirmation ou celui d'annulation).
   *
   * @param message Message à afficher dans la boîte de dialogue
   * @return {@code true} si l'utilisateur a cliqué sur le bouton de confirmation, {@code false}
   *         sinon.
   */
  public static boolean afficher(String message) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.initModality(Modality.APPLICATION_MODAL);
    alert.setContentText(message);
    Optional<ButtonType> buttonType = alert.showAndWait();
    return buttonType.isPresent() && buttonType.get().equals(ButtonType.OK);
  }
}
