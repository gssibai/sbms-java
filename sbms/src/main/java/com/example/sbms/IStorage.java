package com.example.sbms;

public interface IStorage {
    boolean write(SecureAcc sa);

    boolean delete(SecureAcc sa);

    SecureAcc read(String id, String passHash);

    boolean isAccExist(String id);
}
