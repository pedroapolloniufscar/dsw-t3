package br.ufscar.dc.dsw.domain;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "salasdeteatro")
public class SalasDeTeatro extends AbstractEntity<Long> {
	
	@NotBlank(message = "{NotBlank.salasdeteatro.nome}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String nome;

	@NotBlank(message = "{NotBlank.salasdeteatro.cidade}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String cidade;
    
	@NotNull(message = "{NotNull.salasdeteatro.email}")
	@Column(nullable = false, length = 60)
	private String email;
	
	@NotNull(message = "{NotNull.salasdeteatro.cnpj}")
	@Column(nullable = false, length = 60)
	private String cnpj;
	
    

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
