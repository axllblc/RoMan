package fr.roman.controleurs.inscription;

import fr.roman.dao.DAOUtilisateur;
import fr.roman.modeles.Utilisateur;
import javafx.util.Pair;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class CtrlInscription {
    /**
     * Cette méthode sert à chiffrer le mot de passe pour être stocké dans la base.
     * L'algorithme choisi est "PBKDF2WithHmacSHA1".
     * Le salage (en paramètre) et le mot de passe (chiffré) ont une entropie de 248 bits (31 octets).
     * On itère 10 000 fois le chiffrement
     *
     * @param mdp Le mot de passe renseigné par l'utilisateur.
     * @return Un objet Pair avec le mot de passe en première position et le sel en deuxième position
     */
    public static Pair<byte[], byte[]> chiffrerMDP(String mdp, byte[] sel) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // On paramètre la clé de chiffrement selon les modalités décrites en documentation
        KeySpec spec = new PBEKeySpec(mdp.toCharArray(), sel, 10000, 248);
        // On récupère l'algorithme de chiffrement choisi
        SecretKeyFactory algo = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        // On procède au chiffrement du mot de passe et on le retourne avec le sel utilisé
        return new Pair<>(algo.generateSecret(spec).getEncoded(), sel);
    }

    /**
     * Méthode servant à générer un sel ayant une entropie de 32 octets.
     * @return Un sel sous forme de tableau de bits.
     */
    private byte[] genererSel() {
        SecureRandom random = new SecureRandom(); // On utilise un générateur d'octets.
        // On génère le sel sur 31 octets
        return random.generateSeed(31);
    }

    /**
     *
     * @param nomUtilisateur Le nom d'utilisateur
     * @param mdp Le mot de passe renseigné (non chiffré)
     * @return Un objet utilisateur correspondant au nom d'utilisateur.
     *         Renvoie null si le nom d'utilisateur et/ou le mot de passe est incorrect.
     */
    public Utilisateur authentification(String nomUtilisateur, String mdp) {
        try {
            DAOUtilisateur daoU = new DAOUtilisateur();
            Utilisateur u = daoU.findByNomUtilisateur(nomUtilisateur);
            if (u != null){ // Si le nom d'utilisateur existe
                // Note : on utilise Base64 pour convertir le mot de passe (chaine de caractère) en tableau de bits
                if (Arrays.equals(CtrlInscription.chiffrerMDP(mdp, u.getSel()).getKey() , Base64.getDecoder().decode(u.getMdp()))){
                    // Et si le mot de passe est correct, on retourne l'objet Utilisateur
                    return u;
                }
            }
            //Sinon on ne renvoie rien
            return null;
        } catch (Exception e) {
            // En cas d'erreur pour le processus d'authentification, on ne renvoie rien
            return null;
        }
    }
}
