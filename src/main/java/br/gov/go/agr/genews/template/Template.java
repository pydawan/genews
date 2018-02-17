package br.gov.go.agr.genews.template;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.FileLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
public class Template {

   public static final String TOMCAT_DIR = System.getProperty("catalina.base");
   public static final String TOMCAT_WEBAPPS_DIR = String.format("%s/webapps", TOMCAT_DIR);
   public static final String TOMCAT_WTP_WEBAPPS_DIR = String.format("%s/wtpwebapps", TOMCAT_DIR);
   public static final String APP_DIR = String.format("%s/genews", TOMCAT_WTP_WEBAPPS_DIR); // ambiente desenvolvimento.
//   public static final String APP_DIR = String.format("%s/genews", TOMCAT_WEBAPPS_DIR); // ambiente produção.
   private String prefix;
   private String suffix;
   private String charset;
   private PebbleEngine engine;
   private FileLoader loader;
   private Writer writer;
   
   public Template(String prefix, String suffix, String charset) {
      prefix = prefix == null ? String.format("%s/WEB-INF/templates", APP_DIR) : prefix;
      suffix = suffix == null ? ".html" : suffix;
      charset = charset == null ? "UTF-8" : charset;
      this.prefix = prefix;
      this.suffix = suffix;
      loader = new FileLoader();
      if (!prefix.isEmpty()) {
         loader.setPrefix(prefix);
      }
      if (!suffix.isEmpty()) {
         loader.setSuffix(suffix);
      }
      if (!charset.isEmpty()) {
         loader.setCharset(charset);
      }
      engine = new PebbleEngine.Builder().loader(loader).build();
      writer = new StringWriter();
   }
   
   public Template(String prefix, String suffix) {
      this(prefix, suffix, null);
   }
   
   public Template(String prefix) {
      this(prefix, null, null);
   }
   
   public Template() {
      this(null, null, null);
   }
   
   public static Template of(String prefix, String suffix, String charset) {
      return new Template(prefix, suffix, charset);
   }
   
   public static Template of(String prefix, String suffix) {
      return new Template(prefix, suffix);
   }
   
   public static Template of(String prefix) {
      return new Template(prefix);
   }
   
   public static Template of() {
      return new Template();
   }
   
   public String render(String template, TemplateContext context) {
      String output = "";
      template = template == null ? "" : template;
      if (!template.isEmpty()) {
         try {
            PebbleTemplate compiledTemplate = engine.getTemplate(template);
            compiledTemplate.evaluate(writer, context);
            output = writer.toString();
         } catch (PebbleException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return output;
   }
   
   public String getPrefix() {
      return prefix;
   }
   
   public void setPrefix(String prefix) {
      prefix = prefix == null ? String.format("%s/WEB-INF/templates", APP_DIR) : prefix;
      this.prefix = prefix;
      if (!prefix.isEmpty()) {
         engine.getLoader().setPrefix(prefix);
      }
   }
   
   public String prefix() {
      return getPrefix();
   }
   
   public Template prefix(String prefix) {
      setPrefix(prefix);
      return this;
   }
   
   public String getSuffix() {
      return suffix;
   }
   
   public void setSuffix(String suffix) {
      suffix = suffix == null ? ".html" : suffix;
      this.suffix = suffix;
      if (!suffix.isEmpty()) {
         engine.getLoader().setSuffix(suffix);
      }
   }
   
   public String suffix() {
      return getSuffix();
   }
   
   public Template suffix(String suffix) {
      setSuffix(suffix);
      return this;
   }
   
   public String getCharset() {
      return charset;
   }
   
   public void setCharset(String charset) {
      charset = charset == null ? "UTF-8" : charset;
      this.charset = charset;
      if (!charset.isEmpty()) {
         engine.getLoader().setCharset(charset);
      }
   }
   
   public String charset() {
      return getCharset();
   }
   
   public Template charset(String charset) {
      setCharset(charset);
      return this;
   }
   
}
