//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.bj.king.config;

public class MailServerConfig {
    private String hostName;
    private int smtpPort;
    private boolean useSSLOnConnect;
    private String userName;
    private String password;

    public MailServerConfig() {
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getSmtpPort() {
        return this.smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public boolean isUseSSLOnConnect() {
        return this.useSSLOnConnect;
    }

    public void setUseSSLOnConnect(boolean useSSLOnConnect) {
        this.useSSLOnConnect = useSSLOnConnect;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static MailServerConfig createGMailConfig(String userName, String password) {
        MailServerConfig config = new MailServerConfig();
        config.setHostName("smtp.googlemail.com");
        config.setSmtpPort(465);
        config.setUseSSLOnConnect(true);
        config.setUserName(userName);
        config.setPassword(password);
        return config;
    }
}
