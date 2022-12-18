package fr.roman.modeles;

import java.util.Objects;

/**
* Représente la tournée d'un producteur.
*/
public class Tournee extends Modele {

  private int idTournee;
  private String horaireDebut;
  private String horaireFin;
  private String estimationDuree;
  private String note;
  private boolean valide;
  private Producteur producteur;
  private Vehicule vehicule;

  /**
   * Constructeur de la classe Tournee.
   *
   * @param idTournee L'identifiant de la tournée dans la base.
   * @param horaireDebut Le début de la tournée.
   * @param horaireFin La fin de la tournée.
   * @param estimationDuree La durée estimée de la tournée pour être effectuée.
   * @param note La note associée à la tournée.
   * @param valide La validité de la tournée. Une tournée est valide si les contraintes horaires
   *               et les contraintes de poids sont vérifiées.
   * @param producteur Le producteur responsable de cette tournée.
   * @param vehicule Le véhicule utilisé pour effectuer la tournée.
   */
  public Tournee(int idTournee, String horaireDebut, String horaireFin, String estimationDuree,
                 String note, boolean valide, Producteur producteur, Vehicule vehicule) {
    this.idTournee = idTournee;
    this.horaireDebut = horaireDebut;
    this.horaireFin = horaireFin;
    this.estimationDuree = estimationDuree;
    this.note = note;
    this.valide = valide;
    this.producteur = producteur;
    this.vehicule = vehicule;
  }

  /**
   * Le constructeur par défaut de la classe Tournee.
   */
  public Tournee() {
  }

  public int getIdTournee() {
    return idTournee;
  }

  public String getHoraireDebut() {
    return horaireDebut;
  }

  public void setHoraireDebut(String horaireDebut) {
    this.horaireDebut = horaireDebut;
  }

  public String getHoraireFin() {
    return horaireFin;
  }

  public void setHoraireFin(String horaireFin) {
    this.horaireFin = horaireFin;
  }

  public String getEstimationDuree() {
    return estimationDuree;
  }

  public void setEstimationDuree(String estimationDuree) {
    this.estimationDuree = estimationDuree;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public boolean isValide() {
    return valide;
  }

  public void setValide(boolean valide) {
    this.valide = valide;
  }

  public Producteur getProducteur() {
    return producteur;
  }

  public void setProducteur(Producteur producteur) {
    this.producteur = producteur;
  }

  public Vehicule getVehicule() {
    return vehicule;
  }

  public void setVehicule(Vehicule vehicule) {
    this.vehicule = vehicule;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tournee tournee = (Tournee) o;
    return idTournee == tournee.idTournee && valide == tournee.valide && Objects.equals(horaireDebut, tournee.horaireDebut) && Objects.equals(horaireFin, tournee.horaireFin) && Objects.equals(estimationDuree, tournee.estimationDuree) && Objects.equals(note, tournee.note) && Objects.equals(producteur, tournee.producteur) && Objects.equals(vehicule, tournee.vehicule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTournee, horaireDebut, horaireFin, estimationDuree, note, valide, producteur, vehicule);
  }

  @Override
  public String toString() {
    return "Tournee{"
        + "idTournee=" + idTournee
        + ", horaireDebut='" + horaireDebut + '\''
        + ", horaireFin='" + horaireFin + '\''
        + ", estimationDuree='" + estimationDuree + '\''
        + ", note='" + note + '\''
        + ", valide=" + valide
        + ", producteur=" + producteur.toString()
        + ", vehicule=" + vehicule.toString()
        + '}';
  }
}