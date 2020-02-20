package org.ag.workshop.offheap;

public interface User {
    void setUsername(byte[] username);

    byte[] getUsername(byte[] to);

    void setAge(byte age);

    byte getAge();

    void setCollectedMiles(long collectedMiles);

    long getCollectedMiles();
}