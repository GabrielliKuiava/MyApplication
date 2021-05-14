package com.example.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pessoa {
    @PrimaryKey
    Integer id;
    @ColumnInfo(name = "nome")
    String nome;
    @ColumnInfo(name = "sobrenome")
    String sobrenome;
    @ColumnInfo(name = "idade")
    String idade;

    public Pessoa(String nome, String sobrenome, String idade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

}
