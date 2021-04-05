package leakvid.hpcore

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class AppConfig {
    @Bean
    fun mongoClient(@Value("\${spring.data.mongodb.host}") host: String,
                    @Value("\${spring.data.mongodb.port}") port: String): MongoClient {
        return MongoClients.create("mongodb://${host}:${port}")
    }

    @Bean
    fun mongoTemplate(@Value("\${spring.data.mongodb.database}") databaseName: String,
                      @Autowired mongoClient: MongoClient): MongoTemplate {
        return MongoTemplate(mongoClient, databaseName)
    }

    @Bean
    fun openApi(@Value("\${api.version}") version: String): OpenAPI? {
        return OpenAPI()
                .components(Components())
                .info(Info()
                        .title("Homebrewer's Tools: Core")
                        .version(version))
    }
}