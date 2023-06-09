package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Size;

public class User implements Serializable {

    @Size(min = 2, message = "Username must be at least 2 characters.")
    private String username;

    @Size(min = 2, message = "Password must be at least 2 characters")
    private String password;

    private boolean isAuthenticated = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + " ]";
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("username", this.getUsername())
                .add("password", this.getPassword())

                .build();
    }

    public static JsonObject toJSON(String json) {
        JsonReader r = (JsonReader) Json.createReader(new StringReader(json));
        return r.readObject();
    }

    // public static User retrieveJSON(String json) throws IOException{
    // try
    // }

}
