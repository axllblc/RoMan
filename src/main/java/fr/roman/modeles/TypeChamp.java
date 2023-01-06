package fr.roman.modeles;

import java.util.Calendar;

/**
 * Classe qui défini un type de champ avec des informations complémentaires.
 */
public class TypeChamp {
  private LibelleChamp libelle;
  private String valeur = null;
  private boolean valeurBool = false;
  private String regex = null;
  private String placeholder;

  private double minDouble, maxDouble, initDouble, valeurDouble;
  private int minInt, maxInt, initInt, valeurInt;
  private Calendar calendar = null;

  public TypeChamp(LibelleChamp libelle) {
    this.libelle = libelle;
  }

  // SPINNER
  public void setSpinnerDouble(Double min,Double max,Double valeurInitial) {
    setMinDouble(min);
    setMaxDouble(max);
    setInitDouble(valeurInitial);
  }
  
  public void setSpinnerInt(int min,int max,int valeurInitial) {
    setMinInt(min);
    setMaxInt(max);
    setInitInt(valeurInitial);
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

  public double getInitDouble() {
    return initDouble;
  }

  public void setInitDouble(double initDouble) {
    this.initDouble = initDouble;
  }

  public double getValeurDouble() {
    return valeurDouble;
  }

  public void setValeurDouble(double valeurDouble) {
    this.valeurDouble = valeurDouble;
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
  
  public int getInitInt() {
    return initInt;
  }
  
  public void setInitInt(int initInt) {
    this.initInt = initInt;
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
}
