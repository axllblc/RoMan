package fr.roman.controleurs.comptes;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Classe utilitaire regroupant les opérations liées aux mots de passe : {@link #chiffrerMDP
 * chiffrment de mot de passe}, {@link #genererSel() génération d'un sel}.
 *
 * @author Pierre Saussereau
 */
public class OutilsMotDePasse {
  /**
   * Cette méthode statique sert à chiffrer le mot de passe pour être stocké dans la base.
   *
   * <p>L'algorithme choisi est "PBKDF2WithHmacSHA1".</p>
   * <p>Le salage (en paramètre) et le mot de passe (chiffré) ont une entropie de 248 bits (31
   * octets). On itère 10 000 fois le chiffrement</p>
   *
   * @param mdp Le mot de passe renseigné par l'utilisateur.
   * @return Le mot de passe chiffré dans un tableau d'octets
   */
  public static byte[] chiffrerMDP(String mdp, byte[] sel)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    // On paramètre la clé de chiffrement selon les modalités décrites en documentation
    KeySpec spec = new PBEKeySpec(mdp.toCharArray(), sel, 10000, 248);
    // On récupère l'algorithme de chiffrement choisi
    SecretKeyFactory algo = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    // On procède au chiffrement du mot de passe et on le retourne avec le sel utilisé
    return algo.generateSecret(spec).getEncoded();
  }

  /**
   * Méthode statique servant à générer un sel ayant une entropie de 32 octets.
   *
   * @return Un sel sous forme de tableau de bits.
   */
  public static byte[] genererSel() {
    SecureRandom random = new SecureRandom(); // On utilise un générateur d'octets.
    // On génère le sel sur 31 octets
    return random.generateSeed(31);
  }
}
