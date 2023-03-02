package br.com.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

//    @JsonIgnore
//    @OneToMany(mappedBy = "cozinha")
//    private List<Restaurante> restaurantes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cozinha cozinha = (Cozinha) o;
        return id != null && Objects.equals(id, cozinha.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
