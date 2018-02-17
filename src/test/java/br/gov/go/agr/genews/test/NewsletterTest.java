package br.gov.go.agr.genews.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.genews.models.Newsletter;
import br.gov.go.agr.genews.models.Usuario;
import jedi.db.engine.JediEngine;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
public class NewsletterTest {
   
   @BeforeClass
   public static void createTables() {
      JediEngine.dropTables();
      JediEngine.createTables();
   }
   
   @Test
   public void testSave() {
      Newsletter newsletterEsperada = Newsletter.of(
         "Comunica AGR", 
         "thiago.amm.agr@gmail.com", 
         "Thiago Monteiro",
         Usuario.of(
            "Thiago", 
            "Monteiro", 
            "thiago.amm.agr@gmail.com", 
            "admin", 
            "thiago", 
            true, 
            true
         ) 
      );
      newsletterEsperada.save();
      Newsletter newsletterObtida = Newsletter.objects.get("nome", "Comunica AGR");
      Assert.assertEquals(newsletterEsperada, newsletterObtida);
   }
   
}
