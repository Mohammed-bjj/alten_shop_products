package alten.shop.backend.backEnd;

import alten.shop.backend.backEnd.utils.entities.Product;
import alten.shop.backend.backEnd.repositories.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class BackEndApplication  {
	private static final Logger logger = LoggerFactory.getLogger(BackEndApplication.class);

	public static void main(String[] args)  {
		SpringApplication.run(BackEndApplication.class, args);
	}

	/**
	 *   Commande insérant les data existant dans le fichier products.json
	 *   qui se trouves dans la répertoire ressources  suite au démarrage de l'app
	 * @param productRepository
	 * @return
	 */
	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			logger.info("Application started...");
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<>() {};
			try {
				InputStream inputStream = TypeReference.class.getResourceAsStream("/products.json");
				List<Product> products = mapper.readValue(inputStream, typeReference);
				products.forEach( product ->  System.out.println(" product -> name : "+ product));
				productRepository.saveAll(products);
				logger.info("Products saved!");
			}catch (Exception e){
				logger.error("Unable to save products: ", e);
			}
		};
	}
}
