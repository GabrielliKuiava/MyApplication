package com.example.myapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.Pessoa;

@Database(entities = {Pessoa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PessoaDao pessoaDao();
}