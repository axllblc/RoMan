package fr.roman.dao;

import fr.roman.modeles.Adresse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DAOAdresseTest {
  private DAOAdresse testDAO = new DAOAdresse();
  @Test
  void insert() {
    // création de deux objets Adresse sans identifiant.
    double[] coord = {40.0, 0.80};
    Adresse testAddresse = new Adresse();
    testAddresse.setCoordonneesGPS(coord);
    testAddresse.setLibelle("test");
    testAddresse.setNumeroVoie(1);
    testAddresse.setComplementNumero("numéro");
    testAddresse.setVoie("voie");
    testAddresse.setComplementAdresse("adresse");
    testAddresse.setCodePostal(37000);
    testAddresse.setVille("tours");

    Adresse testAddresse2 = new Adresse();
    testAddresse2.setCoordonneesGPS(coord);
    testAddresse2.setLibelle("test");
    testAddresse2.setNumeroVoie(1);
    testAddresse2.setComplementNumero("numéro");
    testAddresse2.setVoie("voie");
    testAddresse2.setComplementAdresse("adresse");
    testAddresse2.setCodePostal(37000);
    testAddresse2.setVille("tours");
    // test si la méthode insert renvoi l'objet donné en paramètre.
    Adresse retourAdresse = testDAO.insert(testAddresse);

    // Test avec "equals".
    // assertTrue(retourAdresse.equals(testAddresse2));

    // Test attribut par attribut.
    assertTrue(
            testAddresse.getCoordonneesGPS()==retourAdresse.getCoordonneesGPS() &&
            testAddresse2.getLibelle()==retourAdresse.getLibelle() &&
            testAddresse2.getNumeroVoie()==retourAdresse.getNumeroVoie() &&
            testAddresse2.getComplementNumero()==retourAdresse.getComplementNumero() &&
            testAddresse2.getVoie()==retourAdresse.getVoie() &&
            testAddresse2.getComplementAdresse()==retourAdresse.getComplementAdresse() &&
            testAddresse2.getCodePostal()==retourAdresse.getCodePostal() &&
            testAddresse2.getVille()==retourAdresse.getVille());

    testAddresse2.setVille("paris");
    // assertFalse(retourAdresse.equals(testAddresse2));
    assertFalse(
            testAddresse2.getCoordonneesGPS()==retourAdresse.getCoordonneesGPS() &&
                    testAddresse2.getLibelle()==retourAdresse.getLibelle() &&
                    testAddresse2.getNumeroVoie()==retourAdresse.getNumeroVoie() &&
                    testAddresse2.getComplementNumero()==retourAdresse.getComplementNumero() &&
                    testAddresse2.getVoie()==retourAdresse.getVoie() &&
                    testAddresse2.getComplementAdresse()==retourAdresse.getComplementAdresse() &&
                    testAddresse2.getCodePostal()==retourAdresse.getCodePostal() &&
                    testAddresse2.getVille()==retourAdresse.getVille());
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }

  @Test
  void find() {
  }

  @Test
  void findById() {
  }
}