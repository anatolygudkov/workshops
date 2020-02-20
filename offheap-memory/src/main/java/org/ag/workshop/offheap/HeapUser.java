package org.ag.workshop.offheap;

import java.util.Arrays;

import static org.ag.workshop.offheap.Utils.FIXED_STRING_LENGTH;

public class HeapUser implements User {
    private final byte[] username = new byte[FIXED_STRING_LENGTH];
    private byte age;
    private long collectedMiles;

    public HeapUser() {
    }

    public byte[] getUsername() {
        return username;
    }

    @Override
    public byte[] getUsername(final byte[] to) {
        System.arraycopy(username, 0, to, 0, username.length);
        return to;
    }

    public void setUsername(final byte[] username) {
        System.arraycopy(username, 0, this.username, 0, username.length);
    }

    public byte getAge() {
        return age;
    }

    public void setAge(final byte age) {
        this.age = age;
    }

    public long getCollectedMiles() {
        return collectedMiles;
    }

    public void setCollectedMiles(final long collectedMiles) {
        this.collectedMiles = collectedMiles;
    }

    public boolean usernameEqualsTo(final byte[] username) {
        return Arrays.equals(this.username, username);
    }

    @Override
    public String toString() {
        return "HeapUser{" +
                "username=" + new String(username) +
                ", age=" + age +
                ", collectedMiles=" + collectedMiles +
                '}';
    }
}