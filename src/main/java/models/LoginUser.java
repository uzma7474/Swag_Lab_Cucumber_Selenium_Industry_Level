package models;

public class LoginUser {

    private String username;
    private String password;
    private String userType;
    private String category;
    private boolean expectedLoginSuccess;

    public LoginUser() {
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isExpectedLoginSuccess() {
        return expectedLoginSuccess;
    }

    public void setExpectedLoginSuccess(boolean expectedLoginSuccess) {
        this.expectedLoginSuccess = expectedLoginSuccess;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", category='" + category + '\'' +
                ", expectedLoginSuccess=" + expectedLoginSuccess +
                '}';
    }
}