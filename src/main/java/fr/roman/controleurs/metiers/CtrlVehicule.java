package fr.roman.controleurs.metiers;

import fr.roman.modeles.Vehicule;
import fr.roman.vues.metiers.VueAdresse;

public class CtrlVehicule {

  /**
   * Instance de VueVehicule.
   */
  private final VueAdresse vueVehicule;

  public CtrlVehicule(VueAdresse vueVehicule) {
    this.vueVehicule = vueVehicule;
  }

  public void removeVehicule(Vehicule vehicule) {
    //DAOVehicule.delete(vehicule.getIdVehicule);
  }
}
