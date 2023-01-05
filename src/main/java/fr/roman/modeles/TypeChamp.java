package fr.roman.modeles;

/**
 * Classe qui défini un type de champ avec des informations complémentaires.
 */
public class TypeChamp {
  private String libelle;
  private String valeur;
  private String regex;
  private String placeholder;
  
  private Double minDouble, maxDouble, initDouble;
  private int minInt, maxInt, initInt;
  
  // TEXTFIELD
  public TypeChamp(String regex) {
    this.libelle = "textField";
    setRegex(regex);
  }
  
  // SPINNER
  public TypeChamp(Double min,Double max,Double valeurInitial) {
    this.libelle = "spinnerDouble";
    this.minDouble = min;
    this.maxDouble = max;
    this.initDouble = valeurInitial;
  
  }
  public TypeChamp(int min,int max,int valeurInitial) {
    this.libelle = "spinnerInteger";
    this.minInt = min;
    this.maxInt = max;
    this.initInt = valeurInitial;
  }
  
  // CALENDARTEXTFIELD
  public TypeChamp() {
    this.libelle = "calendarTextField";
  }
  
  // GETTER SETTER
  public void setValue(String valeur) {
    this.valeur = valeur;
  }
  public String getValue() {
    return this.valeur;
  }
  public String getLibelle() {
    return this.libelle;
  }
  public void setRegex(String regex) {
    this.regex = regex;
  }
  public String getRegex() {
    return this.regex;
  }
  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }
  public String getPlaceholder() {
    return this.placeholder;
  }
  public Double getMinDouble() {
    return this.minDouble;
  }
  public Double getMaxDouble() {
    return this.maxDouble;
  }
  public Double getInitDouble() {
    return this.initDouble;
  }
  public int getMinInt() {
    return this.minInt;
  }
  public int getMaxInt() {
    return this.maxInt;
  }
  public int getInitInt() {
    return this.initInt;
  }
}
