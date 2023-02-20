//package br.com.algafoodapi.jpa;
//
//import br.com.algafoodapi.AlgafoodApiApplication;
//import br.com.algafoodapi.domain.model.Cozinha;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//
//import java.util.List;
//
//public class BuscaCozinhaMain {
//
//    public static void main(String[] args) {
//
//        ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//
//        CadastroCozinha cadastroCozinha = aplApplicationContext.getBean(CadastroCozinha.class);
//
//        Cozinha cozinha = cadastroCozinha.buscar(1L);
//
//        System.out.println(cozinha.getName());
//    }
//}
