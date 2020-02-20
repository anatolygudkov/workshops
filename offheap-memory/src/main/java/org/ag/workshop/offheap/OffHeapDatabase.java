package org.ag.workshop.offheap;

import static org.ag.workshop.offheap.UnsafeAccess.UNSAFE;
import static org.ag.workshop.offheap.Utils.newUsernameTemplate;

/**
 * Not thread safe
 */
public class OffHeapDatabase implements Database {
    private final OffHeapUser userFlyweight = new OffHeapUser();
    private final byte[] usernameTemplateForLoadUsers = newUsernameTemplate();

    private int numberOfUsers;
    private long address;

    @Override
    public void allocate(final int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
        address = UNSAFE.allocateMemory(numberOfUsers * OffHeapUser.RECORD_SIZE);
    }

    @Override
    public void loadUsers() {
        for (int i = 0; i < numberOfUsers; i++) {
            userFlyweight.setRecordOffset(address + (i * OffHeapUser.RECORD_SIZE));
            Utils.setDummyUser(userFlyweight, i, usernameTemplateForLoadUsers);
        }
    }

    @Override
    public User findUserByName(final byte[] username) {
        for (int i = 0; i < numberOfUsers; i++) {
            userFlyweight.setRecordOffset(address + (i * OffHeapUser.RECORD_SIZE));
            if (userFlyweight.usernameEqualsTo(username)) {
                return userFlyweight;
            }
        }
        return null;
    }

    @Override
    public void close() {
        UNSAFE.freeMemory(address);
    }
}
