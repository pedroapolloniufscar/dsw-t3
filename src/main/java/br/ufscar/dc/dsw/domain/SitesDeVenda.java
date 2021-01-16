package br.ufscar.dc.dsw.domain;
//import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import org.springframework.format.annotation.NumberFormat;
//import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "sitesdevenda")
public class SitesDeVenda extends AbstractEntity<Long> {
	
	@NotBlank(message = "{NotBlank.sitesdevenda.nome}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String nome;

	@NotBlank(message = "{NotBlank.sitesdevenda.endereco}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String endereco;
    
	@NotNull(message = "{NotNull.sitesdevenda.email}")
	@Column(nullable = false, length = 60)
	private String email;
	
	@NotNull(message = "{NotNull.sitesdevenda.telefone}")
	@Column(nullable = false, length = 60)
	private String telefone;	
    
    

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
