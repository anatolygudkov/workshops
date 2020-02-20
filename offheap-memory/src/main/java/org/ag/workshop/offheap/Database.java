package org.ag.workshop.offheap;

public interface Database extends AutoCloseable {

    void allocate(final int numberOfUsers);

    void loadUsers();

    User findUserByName(byte[] username);

    default String getName() {
        return getClass().getSimpleName();
    }
}
