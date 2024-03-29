package fr.roman.dao;

import fr.roman.controleurs.comptes.OutilsMotDePasse;
import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la classe {@link DAOUtilisateur}
 *
 * @author Valentin Leclair
 */
class DAOUtilisateurTest {
  private final DAOUtilisateur TEST_DAOUtilisateur;

  {
    try {
      TEST_DAOUtilisateur = new DAOUtilisateur();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private final DAOAdresse TEST_DAOAdresse;

  {
    try {
      TEST_DAOAdresse = new DAOAdresse();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * Test de la méthode insert (admin).
   */
  @Test
  void insertAdmin() {
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur;
    try {
      retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // s'il s'agit du premier utilisateur ajouté il sera root.
    if (retourUtilisateur.getIdUtilisateur()==1) {testUtilisateur.setRole(Role.ROOT);}
    // compare l'objet retourné par la méthode insert (admin).
    assertEquals(retourUtilisateur, testUtilisateur);
    // test insérer le même utilisateur.
    try {
      TEST_DAOUtilisateur.insert(testUtilisateur);
      fail();
    } catch (Exception e) {
      assertEquals("Nom d'utilisateur déjà renseigné", e.getMessage());
    }
  }

  /**
   * Test de la méthode insert (producteur)
   * Dépend des méthodes insert (admin) et insert (adresse) de la classe DAOAdresse
   */
  @Test
  void insertProducteur() {
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);

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
    Adresse retourAdresse;
    try {
      retourAdresse = TEST_DAOAdresse.insert(testAddresse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    Producteur testProducteur = new Producteur();
    testProducteur.setSiret(UUID.randomUUID().toString().substring(0, 14));
    testProducteur.setNomEtablissement("nomÉtablissement");
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(testUtilisateur);
    Producteur retourProducteur;
    try {
      retourProducteur = TEST_DAOUtilisateur.insert(testProducteur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // compare l'objet retourné par la méthode insert (producteur).
    assertEquals(retourProducteur, testProducteur);
    try {
      TEST_DAOUtilisateur.insert(testProducteur);
      fail();
    } catch (Exception e) {
      assertEquals("Siret déjà renseignée", e.getMessage());
    }
  }

  /**
   * Test de la méthode update().
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void update() {
    // création d'un objet Utilisateur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur;
    try {
      retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // modification de l'objet Utilisateur.
    retourUtilisateur.setNom("update nom");
    retourUtilisateur.setPrenom("update prénom");
    try {
      assertTrue(TEST_DAOUtilisateur.update(retourUtilisateur));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test recherche de l'objet modifier
    Utilisateur updateUtilisateur;
    try {
      updateUtilisateur = TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test si l'objet a bien été modifié.
    assertEquals(retourUtilisateur, updateUtilisateur);
  }

  /**
   * Test de la méthode delete().
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void delete() {
    // création d'un objet Utilisateur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur;
    try {
      retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test suppression du dernier utilisateur ajouter à la BD.
    if (retourUtilisateur.getIdUtilisateur() != 1) {
      try {
        assertTrue(TEST_DAOUtilisateur.delete(retourUtilisateur.getIdUtilisateur()));
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      // test recherche de l'utilisateur supprimer.
      try {
        assertNull(TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur()));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    // test suppression d'un utilisateur qui n'existe pas dans la BD.
    try {
      assertFalse(TEST_DAOUtilisateur.delete(retourUtilisateur.getIdUtilisateur()+1));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test de la méthode find().
   * Dépend de la méthode insert().
   */
  @Test
  void find() {
    // création d'un objet Utilisateur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    try {
      TEST_DAOUtilisateur.insert(testUtilisateur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // recherche les utilisateurs qui ont comme nom "nom".
    LinkedHashMap<Utilisateur.Champs, String> criteres = new LinkedHashMap<>();
    criteres.put(Utilisateur.Champs.nom, "nom");
    ArrayList<Utilisateur> retourFind;
    try {
      retourFind = TEST_DAOUtilisateur.find(criteres);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test si tous les utilisateurs retournés par find() sont corrects.
    retourFind.forEach(x -> assertEquals("nom", x.getNom()));
  }

  /**
   * Test de la méthode findById().
   * Dépend de la méthode insert().
   */
  @Test
  void findById() {
    // création d'un objet Utilisateur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur;
    try {
      retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test recherche de l'utilisateur ajouter.
    try {
      assertEquals(retourUtilisateur, TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test recherche d'un utilisateur qui n'existe pas dans la BD.
    try {
      assertNull(TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur()+1));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * Test de la méthode FindByNomUtilisateur().
   * Dépend de la méthode insert().
   */
  @Test
  void findByNomUtilisateur() {
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    String nomUtilisateur = UUID.randomUUID().toString();
    testUtilisateur.setNomUtilisateur(nomUtilisateur);
    testUtilisateur.setSel(OutilsMotDePasse.genererSel());
    try {
      testUtilisateur.setMdp(Base64.getEncoder()
      .encodeToString(OutilsMotDePasse.chiffrerMDP("mot de passe", testUtilisateur.getSel())));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur;
    try {
      retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    // test recherche l'utilisateur ajouté par son nomUtilisateur.
    try {
      assertEquals(retourUtilisateur, TEST_DAOUtilisateur.findByNomUtilisateur(nomUtilisateur));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // test recherche un utilisateur avec un nomUtilisateur inexistant.
    try {
      assertNull(TEST_DAOUtilisateur.findByNomUtilisateur(UUID.randomUUID().toString()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}