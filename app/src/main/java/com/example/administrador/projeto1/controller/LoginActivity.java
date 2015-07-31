package com.example.administrador.projeto1.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.model.persistence.ClientContract;
import com.example.administrador.projeto1.util.FormHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;

    private EditText nome_usuario;
    private EditText login_usuario;
    private ListView listClient;

    private List<Client> listaUser;
    private Client client;

    /*private void bindFildesUsuario() {

        nome_usuario = (EditText) findViewById(R.id.login);
        login_usuario = (EditText) findViewById(R.id.password);
    }*/



    public static Client bindUser(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();
            client.setId_usuario(cursor.getInt(cursor.getColumnIndex(ClientContract.ID_USUARIO)));
            client.setLogin_usuario(cursor.getString(cursor.getColumnIndex(ClientContract.LOGIN_USUARIO)));
            client.setSenha_usuario(cursor.getString(cursor.getColumnIndex(ClientContract.SENHA_USUARIO)));
            return client;
        }
        return null;
    }

   public static List<Client> bindListUsuario(Cursor cursor) {
        List<Client> clients = new ArrayList<>();
        while (cursor.moveToNext()) {
            clients.add(bindUser(cursor));
        }
        return clients;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindLoginButton();

        listaUser = Client.getAllUsuario();
        client = listaUser.get(0);

        //bindFildesUsuario();

    }

    private void bindLoginButton() {
        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText login = (EditText) findViewById(R.id.login);
                EditText senha = (EditText) findViewById(R.id.password);
                //Intent intent = new Intent(LoginActivity.this, ClientListActivity.class);
                //startActivity(intent);


                if (FormHelper.requiredValidate(LoginActivity.this, senha, login)) {
                    if (login.getText().toString().equals(client.getLogin_usuario()) && senha.getText().toString().equals(client.getSenha_usuario())) {
                        Intent novaPagina = new Intent(LoginActivity.this, ClientListActivity.class);
                        startActivity(novaPagina);

                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Senha Incorreta!", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }



        });
    }
}

