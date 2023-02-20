//package br.com.algafoodapi.jpa;
//
//import br.com.algafoodapi.AlgafoodApiApplication;
//import br.com.algafoodapi.domain.model.Restaurante;
//import br.com.algafoodapi.domain.repository.RestauranteRepository;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//
//import java.util.List;
//
//public class ConsultaRestauranteMain {
//
//    public static void main(String[] args) {
//
//        ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//
//        RestauranteRepository restauranteRepository = aplApplicationContext.getBean(RestauranteRepository.class);
//
//        List<Restaurante> restaurantes = restauranteRepository.listar();
//
//        for(Restaurante restaurante : restaurantes) {
//            System.out.printf("%s - %f - %s\n", restaurante.getName(),
//                    restaurante.getTaxaFrete(), restaurante.getCozinha().getName());
//        }
//    }
//}
