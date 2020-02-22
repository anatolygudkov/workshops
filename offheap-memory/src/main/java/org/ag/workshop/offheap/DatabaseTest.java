package org.ag.workshop.offheap;

public class DatabaseTest {
    public static final int NUMBER_OF_USERS = 20_000_000;

    public static final byte[] firstUsername = Utils.newUsernameTemplate();
    public static final byte[] lastUsername = Utils.newUsernameTemplate();

    static {
        Utils.setDummyUsername(0, firstUsername);
        Utils.setDummyUsername(NUMBER_OF_USERS - 1, lastUsername);
    }

    protected static void playWholeScenarion(final Database database) throws Exception {
        long start = System.nanoTime();

        database.allocate(NUMBER_OF_USERS);

        long time = System.nanoTime() - start;

        System.out.println(database.getName() + " allocating took " + time + " nanos");

        start = System.nanoTime();

        database.loadUsers();

        time = System.nanoTime() - start;

        System.out.println(database.getName() + " loading took " + time + " nanos");

        start = System.nanoTime();

        final User lastUser = database.findUserByName(lastUsername);

        time = System.nanoTime() - start;

        System.out.println(database.getName() + " looking through took " + time + " nanos");

        System.out.println("User " + lastUser + " found");

        System.out.println();

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memory used: " + (runtime.totalMemory() - runtime.freeMemory()));

        System.out.println("Look into the process' memory and then press Enter to finish...");
        System.in.read();
    }
}