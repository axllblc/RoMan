package fr.roman.modeles;

import java.time.Duration;
import java.util.Calendar;
import java.util.Objects;

/**
* Représente la tournée d'un producteur.
*/
public class Tournee extends Modele {
  private final int idTournee;
  private Calendar horaireDebut;
  private Calendar horaireFin;
  private Duration estimationDuree;
  private String note;
  private boolean valide;
  private Producteur producteur;
  private Vehicule vehicule;

  /**
   * Constructeur sans paramètre de la classe {@link Tournee}.
   */
  public Tournee() {
    idTournee = 0;
  }

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
  public Tournee(int idTournee, Calendar horaireDebut, Calendar horaireFin, Duration estimationDuree,
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

  @Override
  public int getId() {
    return idTournee;
  }

  public int getIdTournee() {
    return idTournee;
  }

  public Calendar getHoraireDebut() {
    return horaireDebut;
  }

  public void setHoraireDebut(Calendar horaireDebut) {
    this.horaireDebut = horaireDebut;
  }

  public Calendar getHoraireFin() {
    return horaireFin;
  }

  public void setHoraireFin(Calendar horaireFin) {
    this.horaireFin = horaireFin;
  }

  public Duration getEstimationDuree() {
    return estimationDuree;
  }

  public void setEstimationDuree(Duration estimationDuree) {
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

  public enum Champs {horaireDebut, horaireFin, idTournee, note, idProducteur, valide, idVehicule, estimationDuree}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tournee tournee = (Tournee) o;
    return
        valide == tournee.valide
        && Objects.equals(horaireDebut, tournee.horaireDebut)
        && Objects.equals(horaireFin, tournee.horaireFin)
        && Objects.equals(estimationDuree, tournee.estimationDuree)
        && Objects.equals(note, tournee.note)
        && Objects.equals(producteur, tournee.producteur)
        && Objects.equals(vehicule, tournee.vehicule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        horaireDebut, horaireFin, estimationDuree, note, valide, producteur, vehicule
    );
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