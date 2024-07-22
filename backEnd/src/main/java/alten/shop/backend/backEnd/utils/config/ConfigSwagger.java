package alten.shop.backend.backEnd.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;



@Configuration
public class ConfigSwagger {

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OpenAPI spécification - CRUD PRODUCT")
                        .version("0.0.1")
                        .description("Open Documentation for CRUD Products")
                )
                .addServersItem( new Server()
                        .description("local environment")
                        .url("http://localhost:" + this.serverPort)
                );
    }

}
