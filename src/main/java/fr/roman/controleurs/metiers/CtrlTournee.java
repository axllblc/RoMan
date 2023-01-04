package fr.roman.controleurs.metiers;

import fr.roman.modeles.Tournee;
import fr.roman.vues.metiers.VueTournee;

public class CtrlTournee {

  /**
   * Instance de VueTournee.
   */
  private final VueTournee vueTournee;

  public CtrlTournee(VueTournee vueTournee) {
    this.vueTournee = vueTournee;
  }

  public void removeTournee(Tournee tournee) {
    //DAOTournee.delete(tournee.getIdtournee);
  }
}
