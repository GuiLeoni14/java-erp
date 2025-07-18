package br.edu.ifsuldeminas.mch.springbootcrud.config;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Category;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Product;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.CategoryRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Component
@Transactional
public class AtSystemStartup implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Address aEmerson = new Address();
		aEmerson.setNumber(123);
		aEmerson.setPlace("Rua A");
		aEmerson.setZipCode("136000");
		addressRepository.save(aEmerson);
		
		Address aNoe = new Address();
		aNoe.setNumber(100);
		aNoe.setPlace("Rua B");
		aNoe.setZipCode("136888");
		addressRepository.save(aNoe);
		
		Address aLu = new Address();
		aLu.setNumber(101);
		aLu.setPlace("Rua L");
		aLu.setZipCode("000888");
		addressRepository.save(aLu);
		
		addressRepository.flush();
		
		User emerson = new User();
		emerson.setName("Emerson A. Carvalho");
		emerson.setGender(User.Gender.M);
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);
		
		User luiza = new User();
		luiza.setName("Luiza O. Carvalho");
		luiza.setGender(User.Gender.F);
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLu);
		
		User noe = new User();
		noe.setName("Noe L. Carvalho");
		noe.setGender(User.Gender.M);
		noe.setEmail("noe@mail.com");
		noe.setAddress(aNoe);
		
		userRepository.save(emerson);
		userRepository.save(luiza);
		userRepository.save(noe);

		Category placasDeVideo = new Category();
		placasDeVideo.setName("Placas de Vídeo");
		placasDeVideo.setDescription("GPUs para jogos e aplicações gráficas");
		placasDeVideo.setCode("GPU001");

		Category processadores = new Category();
		processadores.setName("Processadores");
		processadores.setDescription("CPUs para desktops e workstations");
		processadores.setCode("CPU001");

		Category perifericos = new Category();
		perifericos.setName("Periféricos");
		perifericos.setDescription("Teclados, mouses e acessórios");
		perifericos.setCode("PERI001");

		categoryRepository.save(placasDeVideo);
		categoryRepository.save(processadores);
		categoryRepository.save(perifericos);
		categoryRepository.flush();

		Product rtx3060 = new Product();
		rtx3060.setName("NVIDIA GeForce RTX 3060");
		rtx3060.setDescription("Placa de vídeo com 12GB GDDR6, ideal para jogos em 1080p");
		rtx3060.setPrice(new BigDecimal("1899.90"));
		rtx3060.setStock(10);
		rtx3060.setCategory(placasDeVideo);

		Product ryzen7 = new Product();
		ryzen7.setName("AMD Ryzen 7 5800X");
		ryzen7.setDescription("Processador octa-core com excelente performance para jogos e multitarefa");
		ryzen7.setPrice(new BigDecimal("1399.99"));
		ryzen7.setStock(15);
		ryzen7.setCategory(processadores);

		Product tecladoMecanico = new Product();
		tecladoMecanico.setName("Teclado Mecânico RGB Redragon Kumara");
		tecladoMecanico.setDescription("Teclado com switches mecânicos Outemu e retroiluminação RGB");
		tecladoMecanico.setPrice(new BigDecimal("249.90"));
		tecladoMecanico.setStock(50);
		tecladoMecanico.setCategory(perifericos);

		Product mouseGamer = new Product();
		mouseGamer.setName("Mouse Gamer Logitech G502 HERO");
		mouseGamer.setDescription("Mouse com sensor HERO 25K, 11 botões programáveis");
		mouseGamer.setPrice(new BigDecimal("299.90"));
		mouseGamer.setStock(40);
		mouseGamer.setCategory(perifericos);

		productRepository.save(rtx3060);
		productRepository.save(ryzen7);
		productRepository.save(tecladoMecanico);
		productRepository.save(mouseGamer);
	}
}
