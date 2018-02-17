package br.gov.go.agr.genews.template;

import java.util.HashMap;

/**
 * @author thiago-amm
 * @version v1.0.0 30/10/2017
 * @since v1.0.0
 */
@SuppressWarnings({ "serial" })
public class TemplateContext extends HashMap<String, Object> {
   
   @SuppressWarnings("unchecked")
   public <T> T get(String key) {
      return (T) super.get(key);
   }
   
}
