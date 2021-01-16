package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


@SuppressWarnings("serial")
@Entity
@Table(name = "promo")
public class Promo extends AbstractEntity<Long> {
	
	@NotNull(message = "{NotNull.promo.sitedevenda}")
	@ManyToOne
	@JoinColumn(name = "sitesdevenda_id")
	private SitesDeVenda sitedevenda;

	@NotBlank(message = "{NotBlank.promo.cnpj}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String cnpj;
    
	@NotNull(message = "{NotNull.promo.nomePeca}")
	@Column(nullable = false, length = 60)
	private String nomePeca;
	
	@NotNull(message = "{NotNull.promo.preco}")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal preco;
	
    

	public SitesDeVenda getSitedevenda() {
		return sitedevenda;
	}

	public void setSitedevenda(SitesDeVenda sitedevenda) {
		this.sitedevenda = sitedevenda;
	}

	public String getNomePeca() {
		return nomePeca;
	}

	public void setNomepeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
