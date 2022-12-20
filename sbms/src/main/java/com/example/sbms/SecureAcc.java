package com.example.sbms;

public class SecureAcc extends BankAcc {
    private String passHash, faKey = null;

    public void setPass(String pass) {
        passHash = getPassHash(pass);
    }

    public void setFaKey(String faKey) {
        this.faKey = faKey;
    }

    public String getPassHash() {
        return passHash;
    }

    public String getFaKey() {
        return faKey;
    }

    public boolean save() {
        IStorage ss = getStorage();
        if (getId() == null) {
            do {
                generateId();
            } while (ss.isAccExist(getId()));
        }
        if (faKey == null) {
            genFAKey();
        }
        return ss.write(this);
    }

    public static SecureAcc getSA(String id, String pass) {
        IStorage ss = getStorage();
        String passHash = getPassHash(pass);
        SecureAcc sa = ss.read(id, passHash);
        return sa;
    }

    private static String getPassHash(String pass) {
        return pass;
    }

    private void genFAKey() {
        faKey = java.util.UUID.randomUUID().toString();
    }

    public boolean delete() {
        IStorage ss = getStorage();
        return ss.delete(this);
    }
    private static IStorage getStorage(){
        return new FileStorage();
    }
}
