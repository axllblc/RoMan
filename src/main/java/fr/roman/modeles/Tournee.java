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
  public Tournee(int idTournee, Calendar horaireDebut, Calendar horaireFin,
                 Duration estimationDuree, String note, boolean valide, Producteur producteur,
                 Vehicule vehicule) {
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
        + ", producteur=" + producteur
        + ", vehicule=" + vehicule
        + '}';
  }

  /**
   * Liste des champs de la table {@code Tournees} dans la base de données.
   */
  public enum Champs implements ChampsModele {
    idTournee(true, false, false, false, false),
    horaireDebut, horaireFin,
    note(false, true, true, false, false),
    valide(false, false, false, false, false),
    idProducteur(false, false, false, true, false),
    idVehicule(false, true, true, true, false);
    private final boolean modifProd;
    private final boolean modifAdmin;
    private final boolean id;
    private final boolean idExt;
    private final boolean nullable;
    /**
     * Constructeur pour désigner des propriétés du champ
     *
     * @param id True si il s'agit de l'identifiant, false sinon
     * @param modifProd True si le champ est modifiable par un producteur, false sinon
     * @param modifAdmin True si le champ est modifiable par un administrateur, false sinon
     * @param idExt True s'il s'agit d'un identifiant qui correspond
     *              à une clé étrangère dans la BDD, false sinon
     * @param nullable True si le champ peut être null, false sinon
     */
    Champs(boolean id, boolean modifProd, boolean modifAdmin, boolean idExt, boolean nullable) {
      this.id = id;
      this.modifProd = modifProd;
      this.modifAdmin = modifAdmin;
      this.idExt = idExt;
      this.nullable = nullable;
    }
    /**
     * Par défaut, tous les utilisateurs peuvent modifier le champ,
     * ce n'est pas un id et il est nullable
     */
    Champs() {
      modifProd = true;
      modifAdmin = true;
      id = false;
      idExt = false;
      nullable = true;
    }
    @Override
    public boolean isModifProd() {
      return this.modifProd;
    }
    @Override
    public boolean isModifAdmin() {
      return this.modifAdmin;
    }
    @Override
    public boolean isId() {
      return this.id;
    }
    @Override
    public boolean isIdExt() {
      return this.idExt;
    }
    @Override
    public boolean isNullable() {
      return this.nullable;
    }
  }
}