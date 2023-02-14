package br.com.algafoodapi.jpa;

import br.com.algafoodapi.AlgafoodApiApplication;
import br.com.algafoodapi.domain.model.Cozinha;
import br.com.algafoodapi.domain.model.Restaurante;
import br.com.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class SalvaRestauranteMain {

    public static void main(String[] args) {

        ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository repository = aplApplicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setName("Tuk Tuk");
        restaurante.setTaxaFrete(new BigDecimal("9.5"));

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setId(2L);
        restaurante1.setName("Japonesa");
        restaurante1.setTaxaFrete(new BigDecimal("10"));

        repository.salvar(restaurante);
        repository.salvar(restaurante1);

    }
}
