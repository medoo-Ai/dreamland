package blog.dreamland.com.entity;

import lombok.Data;

public @Data class UserWithBLOBs extends User {
    private byte[] sslCipher;

    private byte[] x509Issuer;

    private byte[] x509Subject;

    private String authenticationString;

    public String getAuthenticationString() {
        return authenticationString;
    }
    public void setAuthenticationString(String authenticationString) {
        this.authenticationString = authenticationString == null ? null : authenticationString.trim();
    }
}