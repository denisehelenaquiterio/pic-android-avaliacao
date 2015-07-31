package com.example.administrador.projeto1.model.entities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.projeto1.model.persistence.ClientContract;
import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;
import com.example.administrador.projeto1.model.persistence.SQLiteClientRepositiry;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable, Parcelable {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private String phone;



    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    private Integer id_usuario;
    private String login_usuario;
    private String senha_usuario;


    public Client(){
        super();
    }

    public Client(Parcel in){
        super();
        readToParcel(in);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void save() {
        SQLiteClientRepositiry.getInstance().save(this);
    }

    public void delete() {
        SQLiteClientRepositiry.getInstance().delete(this);
    }

    public static List<Client> getAll() {
        return SQLiteClientRepositiry.getInstance().getAll();
    }

    public static List<Client> getAllUsuario() {
        return SQLiteClientRepositiry.getInstance().getAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (!getId().equals(client.getId())) return false;
        if (!getName().equals(client.getName())) return false;
        if (!getAge().equals(client.getAge())) return false;
        if (!getAddress().equals(client.getAddress())) return false;
        return getPhone().equals(client.getPhone());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPhone().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getName() + "\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(phone == null ? "" : phone);
        dest.writeInt(age == null ? -1 : age);
        dest.writeString(address == null ? "" : address);
    }

    public void readToParcel(Parcel in) {
        int partialId = in.readInt();
        id = partialId == -1 ? null : partialId;
        name = in.readString();
        phone = in.readString();
        int partialAge = in.readInt();
        age = partialAge == -1 ? null : partialAge;
        address = in.readString();
    }
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.PHONE, client.getName());
        values.put(ClientContract.ADDRESS, client.getName());
        return values;
    }

}
