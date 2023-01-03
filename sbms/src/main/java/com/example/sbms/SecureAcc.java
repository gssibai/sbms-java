package com.example.sbms;

import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.security.SecureRandom;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class SecureAcc extends BankAcc {
    // 1- FileStorage , 2- SecureFileStorage
    private static int storageImplementation = 2;
    private String passHash, faKey = null;

    public void setPass(String pass) {
        passHash = genPassHash(pass);
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
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
        String passHash = genPassHash(pass);
        SecureAcc sa = ss.read(id, passHash);
        return sa;
    }


    private static String genPassHash(String pass) {
        return sha256Hex(pass);
    }

    private void genFAKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        faKey = base32.encodeToString(bytes);
    }

    public boolean delete() {
        IStorage ss = getStorage();
        return ss.delete(this);
    }

    private static IStorage getStorage() {
        if (storageImplementation == 1) {
            return new FileStorage();
        } else {
            return new SecureFileStorage();
        }
    }

    private String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    public boolean is2faValid(String code) {
        String otp = getTOTPCode(faKey);
        return code.equals(otp);
    }
}


