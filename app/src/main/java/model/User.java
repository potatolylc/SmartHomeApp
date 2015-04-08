package model;

/**
 * Created by ajou on 2015-04-08.
 */
public class User {
    private String userName;
    private String WifiSsid;
    private String userPassword;

    public User() {
    }

    public User(String userName, String wifiSsid, String userPassword) {
        this.userName = userName;
        WifiSsid = wifiSsid;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getWifiSsid() {
        return WifiSsid;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", WifiSsid='" + WifiSsid + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
