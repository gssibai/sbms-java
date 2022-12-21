package com.example.sbms;

import java.io.*;
import java.util.Objects;

public class FileStorage implements IStorage {

    private String ext = ".txt";

    @Override
    public boolean write(SecureAcc sa) {
        if (sa.getId() != null) {
            try {
                FileWriter fw = new FileWriter(sa.getId() + ext);
                BufferedWriter bw = new BufferedWriter(fw);
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
                bw.close();
                fw.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public SecureAcc read(String id, String passHash) {
        try {
            FileReader fr = new FileReader(id);
            BufferedReader br = new BufferedReader(fr);
            SecureAcc sa = new SecureAcc();
            String fPassHash = br.readLine();

            if (!fPassHash.equals(passHash)) {
                br.close();
                fr.close();
                return null;
            }
            sa.setFaKey(br.readLine());
            sa.setName(br.readLine());
            sa.setSurname(br.readLine());
            sa.setEmail(br.readLine());
            sa.setPhoneNo(br.readLine());
            sa.setBalance(Double.parseDouble(br.readLine()));
            br.close();
            fr.close();
            return sa;
        } catch (IOException e) {
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
