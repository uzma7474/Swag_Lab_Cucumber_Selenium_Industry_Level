package models;

import java.util.List;

public class LoginTestData {

    private List<LoginUser> users;
    private List<LoginUser> invalidCredentials;
    private List<LoginUser> emptyCredentials;

    public LoginTestData() {
    }

    public List<LoginUser> getUsers() {
        return users;
    }

    public void setUsers(List<LoginUser> users) {
        this.users = users;
    }

    public List<LoginUser> getInvalidCredentials() {
        return invalidCredentials;
    }

    public void setInvalidCredentials(List<LoginUser> invalidCredentials) {
        this.invalidCredentials = invalidCredentials;
    }

    public List<LoginUser> getEmptyCredentials() {
        return emptyCredentials;
    }

    public void setEmptyCredentials(List<LoginUser> emptyCredentials) {
        this.emptyCredentials = emptyCredentials;
    }
}