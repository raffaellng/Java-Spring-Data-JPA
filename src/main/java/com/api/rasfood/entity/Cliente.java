package com.api.rasfood.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@JsonInclude(JsonInclude.Include.NON_NULL) // So vai trazer aquilo que não e nulo
public class Cliente {

    @EmbeddedId
    private ClienteId clienteId = new ClienteId();
    private String nome;

    @Embedded
    private Contato contato;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecoList = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String cpf, String email, String nome) {
        this.clienteId = new ClienteId(email,cpf);
        this.nome = nome;
    }

    public void addEndereco(Endereco endereco) {
        endereco.setCliente(this);
        this.enderecoList.add(endereco);
    }

    public String getEmail() {
        return clienteId.getEmail();
    }

    public void setEmail(String email) {
        this.clienteId.setEmail(email);
    }

    public String getCpf() {
        return clienteId.getCpf();
    }

    public void setCpf(String cpf) {
        this.clienteId.setCpf(cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + clienteId.getCpf() + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + clienteId.getEmail() + '\'' +
                ", contato=" + contato +
                ", enderecoList=" + enderecoList +
                '}';
    }
}
