package br.com.algafoodapi.jpa;

import br.com.algafoodapi.AlgafoodApiApplication;
import br.com.algafoodapi.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {

        ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = aplApplicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setName("Brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setName("Japonesa");

        cozinha = cadastroCozinha.salvar(cozinha);
        cozinha2 = cadastroCozinha.salvar(cozinha2);

        System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getName());
        System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getName());
    }
}
