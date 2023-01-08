package fr.roman.controleurs.metiers;

import fr.roman.modeles.Vehicule;
import fr.roman.vues.metiers.VueAdresse;
import fr.roman.dao.DAOVehicule;

import java.sql.SQLException;

public class CtrlVehicule {

  /**
   * Instance de VueVehicule.
   */
  private final VueAdresse vueVehicule;

  /**
   * Instance DAOVehicule
   */
  private final DAOVehicule daoVehicule;

  public CtrlVehicule(VueAdresse vueVehicule) throws Exception {
    this.vueVehicule = vueVehicule;
    daoVehicule = new DAOVehicule();
  }

  public void removeVehicule(Vehicule vehicule) throws SQLException {
    daoVehicule.delete(vehicule.getIdVehicule());
  }
}
