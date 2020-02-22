package org.ag.workshop.offheap;

public class HeapDatabaseTest extends DatabaseTest {

    public static void main(String[] args) throws Exception {
        try (Database database = new HeapDatabase()) {
            
            playWholeScenarion(database);

        }
    }
}