package com.example.administrador.projeto1.model.persistence;

import android.content.ContentValues;

public class ClientContract {

    public static final String TABLE = "client";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String[] COLUNS = {ID, NAME, AGE, PHONE, ADDRESS};

    public static final String TABLE_USUARIO = "usuario";
    public static final String ID_USUARIO = "idUsuario";
    public static final String LOGIN_USUARIO = "login";
    public static final String SENHA_USUARIO = "senha";
    public static final String[] COLUNS_USUARIO = {ID_USUARIO, LOGIN_USUARIO, SENHA_USUARIO};

    public static String getSqlCreatTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(AGE + " TEXT ,");
        sql.append(PHONE + " TEXT ,");
        sql.append(ADDRESS + " TEXT ");
        sql.append(" ) ");
        return sql.toString();
    }

    public static String getDadosUsuario() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE_USUARIO);
        sql.append(" ( ");
        sql.append(ID_USUARIO + " INTEGER PRIMARY KEY, ");
        sql.append(LOGIN_USUARIO + " TEXT, ");
        sql.append(SENHA_USUARIO + " TEXT ,");
        sql.append(" ) ");
        return sql.toString();
    }

    public static String getInsertUsuario() {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ");
        sql.append(TABLE_USUARIO);
        sql.append(" ( ");
        sql.append(ID_USUARIO + ", ");
        sql.append(LOGIN_USUARIO + ", ");
        sql.append(SENHA_USUARIO + ",");
        sql.append(" ) ");
        sql.append(" VALUES ");
        sql.append(" (1, 'admin', 'admin')");

        return sql.toString();
    }

}
