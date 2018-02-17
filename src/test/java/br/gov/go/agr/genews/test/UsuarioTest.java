package br.gov.go.agr.genews.test;

import org.junit.BeforeClass;
import org.junit.Test;

import jedi.db.engine.JediEngine;

/**
 * @author thiago-amm
 * @version v1.0.0 30/10/2017
 * @since v1.0.0
 */
public class UsuarioTest {
   
   @BeforeClass
   public static void createTables() {
      JediEngine.dropTables();
      JediEngine.createTables();
   }
   
   @Test
   public void testSave() {
      
//      Usuario usuario = new Usuario();
//      
//      usuario.setNome("admin");
//      usuario.setSobrenome("Monteiro");
//      usuario.setEmail("thiago.amm.agr@gmail.com");
//      usuario.setLogin("admin");
//      usuario.setSenha("ubuntu");
//      usuario.setAtivo(true);
//      usuario.setAdmin(true);
//      usuario.save();
//      
//      usuario = new Usuario();
//      usuario.setNome("Gerência de Comunicação");
//      usuario.setSobrenome("AGR");
//      usuario.setEmail("comunicacaoagr@gmail.com");
//      usuario.setLogin("gecom");
//      usuario.setSenha("comunicaagr");
//      usuario.setAtivo(true);
//      usuario.setAdmin(false);
//      usuario.save();
//      
//      // quantidade esperada de usuários cadastrados.
//      int quantidadeEsperada = 2;
//      // quantidade obtida de usuários cadastrados.
//      int quantidadeObtida = Usuario.objects.all().count();
//      
//      String mensagem = "A quantidade esperada de usuários cadastrados não é igual a quantidade obtida!";
//      
//      Assert.assertEquals(mensagem, quantidadeEsperada, quantidadeObtida);
   }
   
}
