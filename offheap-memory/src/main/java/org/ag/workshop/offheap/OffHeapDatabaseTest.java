package org.ag.workshop.offheap;

public class OffHeapDatabaseTest extends DatabaseTest {

    public static void main(String[] args) throws Exception {
        try (Database database = new OffHeapDatabase()) {

            playWholeScenarion(database);

        }
    }
}