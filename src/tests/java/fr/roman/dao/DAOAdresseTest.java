package fr.roman.dao;

import fr.roman.modeles.Adresse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DAOAdresseTest {
  private final DAOAdresse TEST_DAO = new DAOAdresse();

  /**
   * Compare le résultat de la méthode insert()
   * seul l'idAdresse ne doit pas être égal.
   */
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

    Adresse addresseAttendu = new Adresse();
    addresseAttendu.setCoordonneesGPS(coord);
    addresseAttendu.setLibelle("test");
    addresseAttendu.setNumeroVoie(1);
    addresseAttendu.setComplementNumero("numéro");
    addresseAttendu.setVoie("voie");
    addresseAttendu.setComplementAdresse("adresse");
    addresseAttendu.setCodePostal(37000);
    addresseAttendu.setVille("tours");
    Adresse retourAdresse = TEST_DAO.insert(testAddresse);

    // test si la méthode insert renvoi l'objet donné en paramètre.
    assertEquals(addresseAttendu, retourAdresse);
    assertNotEquals(addresseAttendu.getIdAdresse(), retourAdresse.getIdAdresse());
    addresseAttendu.setVille("paris");
    assertNotEquals(addresseAttendu, retourAdresse);
    assertNotEquals(addresseAttendu.getIdAdresse(), retourAdresse.getIdAdresse());
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