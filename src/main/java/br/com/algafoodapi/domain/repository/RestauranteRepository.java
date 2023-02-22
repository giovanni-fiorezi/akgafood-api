package br.com.algafoodapi.domain.repository;

import br.com.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//    List<Restaurante> findByNameContainingAndCozinhaId(String name, Long cozinha);

//    @Query("from Restaurante where name like %:name% and cozinha.id = :id" )
    List<Restaurante> consultarPorNome(String name, @Param("id") Long cozinha);

    Optional<Restaurante> findFirstRestauranteByNameContaining(String name);

    List<Restaurante> find(String name, BigDecimal taxaInicial, BigDecimal taxaFinal);

    int countByCozinhaId(Long cozinhaId);
}
