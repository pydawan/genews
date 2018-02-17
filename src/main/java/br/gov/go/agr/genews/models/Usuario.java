package br.gov.go.agr.genews.models;

import java.util.Date;

import br.gov.go.agr.genews.crypto.Criptografia;
import jedi.db.models.BooleanField;
import jedi.db.models.CharField;
import jedi.db.models.DateTimeField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import jedi.db.models.QuerySet;
import jedi.types.DateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@Getter
@Setter
@Accessors(fluent = true, chain = true)
@ToString(includeFieldNames = true)
public class Usuario extends Model {
   
   private static final long serialVersionUID = 1L;
   
   @CharField(max_length = 50)
   private String nome;
   
   @CharField(max_length = 50)
   private String sobrenome;
   
   @CharField(max_length = 50, required = false)
   private String email;
   
   @CharField(max_length = 50)
   private String login;
   
   @CharField(max_length = 50)
   private String senha;
   
   @BooleanField(default_value = true)
   private boolean ativo;
   
   @BooleanField(default_value = false)
   private boolean admin;
   
   @DateTimeField(auto_now_add = false, auto_now = true)
   private Date dataCadastro;
   
   @DateTimeField(required = false, default_value = "NULL")
   private Date dataInativacao;
   
   private boolean isLogado = false;
   
   public static Manager objects = new Manager(Usuario.class);
   
   public Usuario(String nome, String sobrenome, String email, String login, String senha, boolean ativo, boolean admin, Date dataCadastro, Date dataInativacao) {
      this.nome = nome;
      this.sobrenome = sobrenome;
      this.email = email;
      this.login = login;
      this.senha = senha;
      this.ativo = ativo;
      this.admin = admin;
      this.dataCadastro = dataCadastro;
      this.dataInativacao = dataInativacao;
   }
   
   public Usuario(String nome, String sobrenome, String email, String login, String senha, boolean ativo, boolean admin, Date dataCadastro) {
      this(nome, sobrenome, email, login, senha, ativo, admin, dataCadastro, null);
   }
   
   public Usuario(String nome, String sobrenome, String email, String login, String senha, boolean ativo, boolean admin) {
      this(nome, sobrenome, email, login, senha, ativo, admin, new Date());
   }
   
   public Usuario(String nome, String sobrenome, String email, String login, String senha, boolean ativo) {
      this(nome, sobrenome, email, login, senha, ativo, false);
   }
   
   public Usuario(String nome, String sobrenome, String email, String login, String senha) {
      this(nome, sobrenome, email, login, senha, true);
   }
   
   public Usuario() {
      this("", "", "", "", "", true, false, new Date(), null);
   }
   
   public static Usuario of(String nome, String sobrenome, String email, String login, String senha, boolean ativo, boolean admin, Date dataCadastro, Date dataInativacao) {
      return new Usuario(nome, sobrenome, email, login, senha, ativo, admin, dataCadastro, dataInativacao);
   }
   
   public static Usuario of(String nome, String sobrenome, String email, String login, String senha, boolean ativo, boolean admin, Date dataCadastro) {
      return new Usuario(nome, sobrenome, email, login, senha, ativo, admin, dataCadastro, new Date());
   }
   
   public static Usuario of(String nome, String sobrenome, String email, String login, String senha, boolean ativo, boolean admin) {
      return new Usuario(nome, sobrenome, email, login, senha, ativo, admin, new Date());
   }
   
   public static Usuario of(String nome, String sobrenome, String email, String login, String senha, boolean ativo) {
      return new Usuario(nome, sobrenome, email, login, senha, ativo, false);
   }
   
   public static Usuario of(String nome, String sobrenome, String email, String login, String senha) {
      return new Usuario(nome, sobrenome, email, login, senha, true);
   }
   
   public static Usuario of() {
      return new Usuario();
   }
   
   public String getNome() {
      return nome;
   }
   
   public void setNome(String nome) {
      this.nome = nome;
   }
   
   public String getSobrenome() {
      return sobrenome;
   }
   
   public void setSobrenome(String sobrenome) {
      this.sobrenome = sobrenome;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public String getLogin() {
      return login;
   }
   
   public void setLogin(String login) {
      this.login = login;
   }
   
   public String getSenha() {
      return senha;
   }
   
   public void setSenha(String senha) {
      senha = Criptografia.encryptMD5(senha);
      this.senha = senha;
   }
   
   public Usuario senha(String senha) {
      setSenha(senha);
      return this;
   }
   
   public boolean isAtivo() {
      return ativo;
   }
   
   public void setAtivo(boolean ativo) {
      this.ativo = ativo;
   }
   
   public boolean isAdmin() {
      return admin;
   }
   
   public void setAdmin(boolean admin) {
      this.admin = admin;
   }
   
   public Date getDataCadastro() {
      return dataCadastro;
   }
   
   public void setDataCadastro(DateTime dataCadastro) {
      this.dataCadastro = dataCadastro;
   }
   
   public Date getDataInativacao() {
      return dataInativacao;
   }
   
   public void setDataInativacao(DateTime dataInativacao) {
      this.dataInativacao = dataInativacao;
   }
   
   public boolean isLogado() {
      return isLogado;
   }
   
   public void setLogado(boolean isLogado) {
      this.isLogado = isLogado;
   }
   
   // Generated by Jedi ORM
   public QuerySet<TemplateNewsletter> getTemplateNewsletterSet() {
      return TemplateNewsletter.objects.getSet(Usuario.class, this.id);
   }
   
   public QuerySet<TemplateNewsletter> templateNewsletterSet() {
      return getTemplateNewsletterSet();
   }
   
   // Generated by Jedi ORM
   public QuerySet<Newsletter> getNewsletterSet() {
      return Newsletter.objects.getSet(Usuario.class, this.id);
   }
   
   public QuerySet<Newsletter> newsletterSet() {
      return getNewsletterSet();
   }
   
}
