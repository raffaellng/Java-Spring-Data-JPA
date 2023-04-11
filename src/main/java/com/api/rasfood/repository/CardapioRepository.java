package com.api.rasfood.repository;

import com.api.rasfood.dto.CardapioDto;
import com.api.rasfood.entity.Cardapio;
import com.api.rasfood.repository.projection.CardapioProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Integer> {

    @Query("SELECT new com.api.rasfood.dto.CardapioDto(c.nome, c.descricao, c.valor, c.categoria.nome) " +
            "FROM Cardapio c WHERE c.nome LIKE %:nome% AND c.disponivel = true")
    List<CardapioDto> findAllByNome(final String nome);

    //  ?1 indica a pocição dos parametros
    @Query(value = "SELECT" +
            "    c.nome as nome," +
            "    c.descricao as descricao," +
            "    c.valor as valor," +
            "    cat.nome as nomeCategoria" +
            "    FROM cardapio c" +
            "    INNER JOIN categorias cat on c.categoria_id = cat.id" +
            "    WHERE c.categoria_id = ?1 AND c.disponivel = true",nativeQuery = true)
    List<CardapioProjection> finAllByCategoria(final Integer categoria);

}
