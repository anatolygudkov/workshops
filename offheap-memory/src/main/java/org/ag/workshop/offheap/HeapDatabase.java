package org.ag.workshop.offheap;

/**
 * Not thread safe
 */
public class HeapDatabase implements Database {
    private final byte[] usernameTemplateForLoadUsers = Utils.newUsernameTemplate();

    private HeapUser[] users;

    @Override
    public void allocate(final int numberOfUsers) {
        users = new HeapUser[numberOfUsers];
        for (int i = 0; i < users.length; i++) {
            users[i] = new HeapUser();
        }
    }

    @Override
    public void loadUsers() {
        for (int i = 0; i < users.length; i++) {
            final HeapUser user = users[i];
            Utils.setDummyUser(user, i, usernameTemplateForLoadUsers);
        }
    }

    @Override
    public User findUserByName(final byte[] username) {
        for (int i = 0; i < users.length; i++) {
            final HeapUser user = users[i];

            if (user.usernameEqualsTo(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void close() {
        // nothing to do
    }
}
