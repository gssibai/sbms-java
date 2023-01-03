package com.example.sbms;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;

public class SecureFileStorage implements IStorage {

    private String ext = ".dat";

    @Override
    public boolean write(SecureAcc sa) {
        if (sa.getId() != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(baos);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(sa.getPassHash());
                bw.newLine();
                bw.write(sa.getFaKey());
                bw.newLine();
                bw.write(sa.getName());
                bw.newLine();
                bw.write(sa.getSurname());
                bw.newLine();
                bw.write(sa.getEmail());
                bw.newLine();
                bw.write(sa.getPhoneNo());
                bw.newLine();
                bw.write(sa.getBalance().toString());
                bw.newLine();
                bw.write(sa.getId());
                bw.newLine();
                bw.close();
                osw.close();
                byte[] buff = baos.toByteArray();
                baos.close();

                byte[] pHash = Hex.decodeHex(sa.getPassHash());
                Key aesKey = new SecretKeySpec(pHash, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                byte[] encrypted = cipher.doFinal(buff);

                FileOutputStream fos = new FileOutputStream(sa.getId() + ext);
                fos.write(encrypted);
                fos.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public SecureAcc read(String id, String passHash) {
        try {
            FileInputStream fis = new FileInputStream(id + ext);
            byte[] encrypted = fis.readAllBytes();
            fis.close();

            byte[] pHash = Hex.decodeHex(passHash);
            Key aesKey = new SecretKeySpec(pHash, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] buff = cipher.doFinal(encrypted);

            ByteArrayInputStream bais = new ByteArrayInputStream(buff);
            InputStreamReader isr = new InputStreamReader(bais);
            BufferedReader br = new BufferedReader(isr);

            SecureAcc sa = new SecureAcc();
            String fPassHash = br.readLine();

            if (!fPassHash.equals(passHash)) {
                br.close();
                isr.close();
                bais.close();

                return null;
            }
            sa.setPassHash(fPassHash);
            sa.setFaKey(br.readLine());
            sa.setName(br.readLine());
            sa.setSurname(br.readLine());
            sa.setEmail(br.readLine());
            sa.setPhoneNo(br.readLine());
            sa.setBalance(Double.parseDouble(br.readLine()));
            sa.setId(br.readLine());
            br.close();
            isr.close();
            bais.close();
            return sa;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isAccExist(String id) {
        File f = new File(id + ext);
        return f.exists() && !f.isDirectory();
    }

    @Override
    public boolean delete(SecureAcc sa) {
        File f = new File(sa.getId() + ext);
        f.delete();
        return true;
    }


}
