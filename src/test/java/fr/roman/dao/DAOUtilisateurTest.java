package fr.roman.dao;

import fr.roman.modeles.Adresse;
import fr.roman.modeles.Producteur;
import fr.roman.modeles.Role;
import fr.roman.modeles.Utilisateur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
    // TODO: 24/12/2022 Modifier car on chiffre le mot de passe dans le contrôleur
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    testUtilisateur.setMdp(retourUtilisateur.getMdp());
    testUtilisateur.setSel(retourUtilisateur.getSel());

    // s'il s'agit du premier utilisateur ajouté il sera root.
    if (retourUtilisateur.getIdUtilisateur()==1) {testUtilisateur.setRole(Role.ROOT);}
    // compare l'objet retourné par la méthode insert (admin).
    assertEquals(retourUtilisateur, testUtilisateur);
    // test insérer le même utilisateur.
    retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    assertNull(retourUtilisateur);
  }

  /**
   * Test de la méthode insert (producteur)
   * Dépend des méthodes insert (admin) et insert (adresse) de la classe DAOAdresse
   */
  @Test
  void insertProducteur() {
    // TODO: 24/12/2022 Modifier car on ajoute l'utilisateur en même temps que le producteur et on chiffre le mdp dans le ctrl
    // création d'objet Utilisateur, Adresse et producteur.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.PRODUCTEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    
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
    Adresse retourAdresse = TEST_DAOAdresse.insert(testAddresse);

    Producteur testProducteur = new Producteur();
    testProducteur.setSiret("01234567891234");
    testProducteur.setNomEtablissement("nomÉtablissement");
    testProducteur.setTel("0123456789");
    testProducteur.setAdresse(retourAdresse);
    testProducteur.setUtilisateur(retourUtilisateur);
    Producteur retourProducteur = TEST_DAOUtilisateur.insert(testProducteur);

    // compare l'objet retourné par la méthode insert (producteur).
    assertEquals(retourProducteur, testProducteur);
    retourProducteur = TEST_DAOUtilisateur.insert(testProducteur);
    assertNull(retourProducteur);
  }

  /**
   * Test de la méthode update().
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void update() {
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);

    // modification de l'objet Utilisateur.
    retourUtilisateur.setNom("update nom");
    retourUtilisateur.setPrenom("update prénom");
    assertTrue(TEST_DAOUtilisateur.update(retourUtilisateur));
    // test recherche de l'objet modifier
    Utilisateur updateUtilisateur = TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur());
    // test si l'objet a bien été modifié.
    assertEquals(retourUtilisateur, updateUtilisateur);
  }

  /**
   * Test de la méthode delete().
   * Dépend des méthodes insert() et findById().
   */
  @Test
  void delete() {
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);

    // test suppression du dernier utilisateur ajouter à la BD.
    assertTrue(TEST_DAOUtilisateur.delete(retourUtilisateur.getIdUtilisateur()));
    // test recherche de l'utilisateur supprimer.
    assertNull(TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur()));
    // test suppression d'un utilisateur qui n'existe pas dans la BD.
    assertFalse(TEST_DAOUtilisateur.delete(retourUtilisateur.getIdUtilisateur()+1));
  }

  /**
   * Test de la méthode find().
   * Dépend de la méthode insert().
   */
  @Test
  void find() {
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    TEST_DAOUtilisateur.insert(testUtilisateur);

    // recherche les utilisateurs qui ont comme nom "nom".
    HashMap<Utilisateur.Champs, String> criteres = new HashMap<>();
    criteres.put(Utilisateur.Champs.nom, "nom");
    ArrayList<Utilisateur> retourFind = TEST_DAOUtilisateur.find(criteres);
    // test si tous les utilisateurs retournés par find() sont corrects.
    retourFind.forEach(x -> assertEquals("nom", x.getNom()));
  }

  /**
   * Test de la méthode findById().
   * Dépend de la méthode insert().
   */
  @Test
  void findById() {
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    testUtilisateur.setNomUtilisateur(UUID.randomUUID().toString());
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    
    // test recherche de l'utilisateur ajouter.
    assertEquals(retourUtilisateur, TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur()));
    // test recherche d'un utilisateur qui n'existe pas dans la BD.
    assertNull(TEST_DAOUtilisateur.findById(retourUtilisateur.getIdUtilisateur()+1));
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
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);

    // test recherche l'utilisateur ajouté par son nomUtilisateur.
    assertEquals(retourUtilisateur, TEST_DAOUtilisateur.findByNomUtilisateur(nomUtilisateur));
    // test recherche un utilisateur avec un nomUtilisateur inexistant.
    assertNull(TEST_DAOUtilisateur.findByNomUtilisateur(UUID.randomUUID().toString()));
  }
  
  /**
   * Test de a méthode authentification().
   * Dépend de la méthode insert().
   */
  @Test
  void authentification() {
    // TODO: 24/12/2022 Modifier car changement de localisation de la méthode
    // création d'un objet Utilisateur sans idUtilisateur ni de sale.
    Utilisateur testUtilisateur = new Utilisateur();
    String nomUtilisateur = UUID.randomUUID().toString();
    testUtilisateur.setNomUtilisateur(nomUtilisateur);
    testUtilisateur.setMdp("mot de passe");
    testUtilisateur.setNom("nom");
    testUtilisateur.setPrenom("prénom");
    testUtilisateur.setEmail("email");
    testUtilisateur.setRole(Role.ADMINISTRATEUR);
    Utilisateur retourUtilisateur = TEST_DAOUtilisateur.insert(testUtilisateur);
    /*
    // test d'authentification via le nomUtilisateur et le mdp de l'utilisateur ajouté.
    assertEquals(retourUtilisateur, TEST_DAOUtilisateur.authentification(nomUtilisateur, "mot de passe"));
    // test d'authentification via mdp erroné.
    assertNull(TEST_DAOUtilisateur.authentification(nomUtilisateur, "erroné"));
    // test d'authentification vias nomUtilisateur inexistant.
    assertNull(TEST_DAOUtilisateur.authentification(UUID.randomUUID().toString(), "mot de passe"));
     */
  }
}