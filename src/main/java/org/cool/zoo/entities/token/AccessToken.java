package org.cool.zoo.entities.token;

import javax.persistence.*;

/**
 * Created by Dang Dim
 * Date     : 05-Jan-18, 11:27 AM
 * Email    : d.dim@gl-f.com
 */

@Entity
@Table(name = "oauth_access_token")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "token_id", length = 255)
    private String token_id;
    @Column(name = "token")
    private byte[] token;
    @Column(name = "authentication_id", length = 255)
    private String authentication_id;
    @Column(name = "user_name", length = 255)
    private String user_name;
    @Column(name = "client_id", length = 255)
    private String client_id;
    @Column(name = "authentication")
    private byte[] authentication;
    @Column(name = "refresh_token", length = 255)
    private String refresh_token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}

