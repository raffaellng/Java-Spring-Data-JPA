package com.api.rasfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "cardapio") // nome da tabela
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private Boolean disponivel;
    private BigDecimal valor;

    /*
     * ManyToOne
     * ManyToMany
     * OneToMany
     * OneToOne
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Categoria categoria;

    @Column(name = "data_de_registro")
    private LocalDateTime dataDeRegistro = LocalDateTime.now();

    public Cardapio(String nome, String descricao, Boolean disponivel, BigDecimal valor, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.disponivel = disponivel;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Cardapio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(LocalDateTime dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    //Para tratar a nossa entidade API para ela em nossos resultados

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", disponivel=" + disponivel +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dataDeRegistro=" + dataDeRegistro +
                '}';
    }
}
