package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Pessoa;

import java.util.List;

@Dao
public interface PessoaDao {
    @Query("SELECT * FROM pessoa")
    List<Pessoa> getAll();

    @Update
    void update(Pessoa pessoa);

    @Insert
    void insertAll(Pessoa... pessoas);
}
