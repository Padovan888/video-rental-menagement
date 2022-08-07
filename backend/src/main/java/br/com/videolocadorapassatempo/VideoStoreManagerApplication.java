package br.com.videolocadorapassatempo;

import io.swagger.annotations.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@Api(tags = "API Runner", description = "Inicializador da API")
public class VideoStoreManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoStoreManagerApplication.class, args);
	}

	@GetMapping
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mensagem retornada com sucesso !")
	})
	@ApiOperation("Retorna a mensagem Hello World !")
	public String index() {
		return "Hello World !";
	}

}
