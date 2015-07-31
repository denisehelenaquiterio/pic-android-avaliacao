package com.example.administrador.projeto1.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.projeto1.model.entities.AppUtil;
import com.example.administrador.projeto1.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class SQLiteClientRepositiry implements ClientRepository {

    private static SQLiteClientRepositiry singletonInstance;

    public SQLiteClientRepositiry() {
        super();
    }

    public static SQLiteClientRepositiry getInstance() {
        if (SQLiteClientRepositiry.singletonInstance == null) {
            SQLiteClientRepositiry.singletonInstance = new SQLiteClientRepositiry();
        }
        return SQLiteClientRepositiry.singletonInstance;
    }

    @Override
    public void save(Client client) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = Client.getContentValues(client);

        if (client.getId() == null) {
            db.insert(ClientContract.TABLE, null, values);
        } else {
            String where = ClientContract.ID + " = ? ";
            String[] args = {client.getId().toString()};
            db.update(ClientContract.TABLE, values, where, args);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<Client> getAll() {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor =
                db.query(ClientContract.TABLE, ClientContract.COLUNS, null, null, null, null, ClientContract.NAME);


        List<Client> clients = getList(cursor);

        db.close();
        helper.close();
        return clients;
    }

    @Override
    public List<Client> getAllUsuario() {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor =
                db.query(ClientContract.TABLE_USUARIO, ClientContract.COLUNS_USUARIO, null, null, null, null, ClientContract.LOGIN_USUARIO);


        List<Client> clients = getListUsuario(cursor);

        db.close();
        helper.close();
        return clients;
    }

    private Client bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();
            client.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setPhone(cursor.getString(cursor.getColumnIndex(ClientContract.PHONE)));
            client.setAddress(cursor.getString(cursor.getColumnIndex(ClientContract.ADDRESS)));
            return client;
        }
        return null;
    }

    private List<Client> getListUsuario(Cursor cursor) {
        List<Client> clients = new ArrayList<>();
        while (cursor.moveToNext()) {
            clients.add(bind(cursor));
        }
        return clients;
    }

    private List<Client> getList(Cursor cursor) {
        List<Client> clients = new ArrayList<>();
        while (cursor.moveToNext()) {
            clients.add(bind(cursor));
        }
        return clients;
    }

    @Override
    public void delete(Client client) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = ClientContract.ID + " = ? ";
        String[] args = {client.getId().toString()};
        db.delete(ClientContract.TABLE, where, args);
    }


}
