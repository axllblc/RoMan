package fr.roman.vues.edition;

import fr.roman.modeles.Commande;
import fr.roman.modeles.Vehicule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Classe qui défini un type de champ avec des informations complémentaires.
 */
public class TypeChamp {
  private LibelleChamp libelle;
  
  @Override
  public String toString() {
    return "TypeChamp{" +
            "libelle=" + libelle +
            ", valeur='" + valeur + '\'' +
            ", valeurBool=" + valeurBool +
            ", disable=" + disable +
            ", regex='" + regex + '\'' +
            ", placeholder='" + placeholder + '\'' +
            ", minDouble=" + minDouble +
            ", maxDouble=" + maxDouble +
            ", valeurDouble=" + valeurDouble +
            ", initDoubleTab1x2=" + Arrays.toString(initDoubleTab1x2) +
            ", valeurDoubleTab1x2=" + Arrays.toString(valeurDoubleTab1x2) +
            ", minInt=" + minInt +
            ", maxInt=" + maxInt +
            ", valeurInt=" + valeurInt +
            ", calendar=" + calendar +
            '}';
  }
  
  private String valeur = "";
  private boolean valeurBool, disable;
  private String regex = "";
  private String placeholder;
  private double minDouble, maxDouble, valeurDouble;
  private double[] initDoubleTab1x2, valeurDoubleTab1x2;
  private int minInt, maxInt, valeurInt;
  private Calendar calendar = null;
  private ArrayList<Commande> commande;
  private ArrayList<Vehicule> vehicules;
  private ArrayList<String> iteam;

  public TypeChamp(LibelleChamp libelle) {
    this.libelle = libelle;
  }

  // SPINNER
  public void setSpinnerDouble(double min,double max,double valeurInitial) {
    setMinDouble(min);
    setMaxDouble(max);
    setValeurDouble(valeurInitial);
  }
  public void setDoubleSpinnerDouble(double min,double max,double valeurInitial1,double valeurInitial2) {
    setMinDouble(min);
    setMaxDouble(max);
    setValeurDoubleTab1x2(new double[]{valeurInitial1,valeurInitial2});
  }

  public void setSpinnerInt(int min,int max,int valeurInitial) {
    setMinInt(min);
    setMaxInt(max);
    setValeurInt(valeurInitial);
  }

  public void setDoubleTab1x2(double[] valeurInitial) {
    setInitDoubleTab1x2(valeurInitial);
  }

  // GETTER SETTER

  public LibelleChamp getLibelle() {
    return libelle;
  }

  public void setLibelle(LibelleChamp libelle) {
    this.libelle = libelle;
  }

  public String getValeur() {
    return valeur;
  }

  public void setValeur(String valeur) {
    this.valeur = valeur;
  }

  public boolean isValeurBool() {
    return valeurBool;
  }

  public void setValeurBool(boolean valeurBool) {
    this.valeurBool = valeurBool;
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }

  public double getMinDouble() {
    return minDouble;
  }

  public void setMinDouble(double minDouble) {
    this.minDouble = minDouble;
  }

  public double getMaxDouble() {
    return maxDouble;
  }

  public void setMaxDouble(double maxDouble) {
    this.maxDouble = maxDouble;
  }
  public double getValeurDouble() {
    return valeurDouble;
  }

  public void setValeurDouble(double valeurDouble) {
    this.valeurDouble = valeurDouble;
  }

  public double[] getInitDoubleTab1x2() {
    return initDoubleTab1x2;
  }
  public void setInitDoubleTab1x2(double[] initDoubleTab1x2) {
    this.initDoubleTab1x2 = initDoubleTab1x2;
  }

  public double[] getValeurDoubleTab1x2() {
    return valeurDoubleTab1x2;
  }

  public void setValeurDoubleTab1x2(double[] valeurDoubleTab1x2) {
    this.valeurDoubleTab1x2 = valeurDoubleTab1x2;
  }

  public int getMinInt() {
    return minInt;
  }
  
  public void setMinInt(int minInt) {
    this.minInt = minInt;
  }
  
  public int getMaxInt() {
    return maxInt;
  }

  public void setMaxInt(int maxInt) {
    this.maxInt = maxInt;
  }
  public int getValeurInt() {
    return valeurInt;
  }

  public void setValeurInt(int valeurInt) {
    this.valeurInt = valeurInt;
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }

  public ArrayList<Commande> getCommande() {
    return commande;
  }

  public void setCommande(ArrayList<Commande> commande) {
    this.commande = commande;
  }

  public ArrayList<Vehicule> getVehicules() {
    return vehicules;
  }

  public void setVehicules(ArrayList<Vehicule> vehicules) {
    this.vehicules = vehicules;
  }
  
  public ArrayList<String> getIteam() {
    return iteam;
  }
  public void setIteam(ArrayList<String> iteam) {
    this.iteam = iteam;
  }
}
