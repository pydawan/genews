package br.gov.go.agr.genews.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.gov.go.agr.genews.models.Newsletter;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@ManagedBean(name = "newsletterConverter")
public class NewsletterConverter implements Converter {
   
   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value) {
      return Newsletter.objects.get("nome", value);
   }
   
   @Override
   public String getAsString(FacesContext context, UIComponent component, Object value) {
      return ((Newsletter) value).getNome();
   }
   
}
