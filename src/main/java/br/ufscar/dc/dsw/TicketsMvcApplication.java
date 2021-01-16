package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.PromoDAO;
import br.ufscar.dc.dsw.dao.SalasDeTeatroDAO;
import br.ufscar.dc.dsw.dao.SitesDeVendaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Promo;
import br.ufscar.dc.dsw.domain.SalasDeTeatro;
import br.ufscar.dc.dsw.domain.SitesDeVenda;
import br.ufscar.dc.dsw.domain.Usuario;


@SpringBootApplication

public class TicketsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsMvcApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(SalasDeTeatroDAO salasdeteatroDAO, SitesDeVendaDAO sitesdevendaDAO, PromoDAO promoDAO, UsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {	
			SalasDeTeatro e1 = new SalasDeTeatro();
			e1.setCnpj("55.789.390/0008-99");
			e1.setNome("Cine Clube Matão");
			e1.setCidade("Matão");
			e1.setEmail("matao@cineclube.com");
			salasdeteatroDAO.save(e1);
			
			SalasDeTeatro e2 = new SalasDeTeatro();
			e2.setCnpj("71.150.470/0001-40");
			e2.setNome("Teatro Municipal de Itapecerica da Serra");
			e2.setCidade("Itapecerica da Serra");
			e2.setEmail("itadaserra@teatro.com");
			salasdeteatroDAO.save(e2);
			
			SalasDeTeatro e3 = new SalasDeTeatro();
			e3.setCnpj("32.106.536/0001-82");
			e3.setNome("Teatro Azul");
			e3.setCidade("São Carlos");
			e3.setEmail("azul@teatro.com");
			salasdeteatroDAO.save(e3);		
			
			
			SitesDeVenda s1 = new SitesDeVenda();
			s1.setTelefone("33841279");
			s1.setNome("Tickets Já");
			s1.setEmail("ticketsja@gmail.com");
			s1.setEndereco("tickesja.com");
			sitesdevendaDAO.save(s1);
			
			SitesDeVenda s2 = new SitesDeVenda();
			s2.setTelefone("997788765");
			s2.setNome("Byma");
			s2.setEmail("byma@gmail.com");
			s2.setEndereco("byma.com");
			sitesdevendaDAO.save(s2);
			
			SitesDeVenda s3 = new SitesDeVenda();
			s3.setTelefone("999090965");
			s3.setNome("Ingresse");
			s3.setEmail("ingresse@gmail.com");
			s3.setEndereco("ingresse.com");
			sitesdevendaDAO.save(s3);
			
			Promo p1 = new Promo();
			p1.setSitedevenda(s1);
			p1.setNomepeca("Cisne Negro");
			p1.setCnpj("12312312312/0001");
			p1.setPreco(BigDecimal.valueOf(54.9));
			promoDAO.save(p1);
			
			Promo p2 = new Promo();
			p2.setSitedevenda(s2);
			p2.setNomepeca("Os Bananas");
			p2.setCnpj("888882312/0001");
			p2.setPreco(BigDecimal.valueOf(94.9));
			promoDAO.save(p2);
			
			Promo p3 = new Promo();
			p3.setSitedevenda(s3);
			p3.setNomepeca("Os Bananas");
			p3.setCnpj("888882312/0001");
			p3.setPreco(BigDecimal.valueOf(40.9));
			promoDAO.save(p3);
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setCPF("012.345.678-90");
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			
			Usuario u2 = new Usuario();
			u2.setUsername("beltrano");
			u2.setPassword(encoder.encode("123"));
			u2.setCPF("985.849.614-10");
			u2.setName("Beltrano Andrade");
			u2.setRole("ROLE_USER");
			u2.setEnabled(true);
			usuarioDAO.save(u2);
			
			Usuario u3 = new Usuario();
			u3.setUsername("fulano");
			u3.setPassword(encoder.encode("123"));
			u3.setCPF("367.318.380-04");
			u3.setName("Fulano Silva");
			u3.setRole("ROLE_USER");
			u3.setEnabled(true);
			usuarioDAO.save(u3);
			
			
		};
	}
	

}
