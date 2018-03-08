/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


/**
 *
 * @author erick
 * @version 1.0
 */
public class EncriptaSenha {
    
    /**
     * 
     * @param senha
     * @return 
     */
    public static String encripta(String senha){
        try{
            
            // digest criado com o algoritmo MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            
            // o metodo update adiciona bytes a mensagem a ser criptografada
            digest.update(senha.getBytes());
            
            // cria-se um encoder para impedir que a senha encriptada apareça
            // com caracteres que costumam causar problemas em xml e alguns bancos de dados
            BASE64Encoder encoder = new BASE64Encoder();
            
            // o metodo digest.digest() retorna a mensagem criptografada
            // o metodo encoder.ecode() substitui os caracters problematicos
            return encoder.encode(digest.digest());
    } catch (NoSuchAlgorithmException e){
    e.printStackTrace();
    return senha;
    }
}
}
