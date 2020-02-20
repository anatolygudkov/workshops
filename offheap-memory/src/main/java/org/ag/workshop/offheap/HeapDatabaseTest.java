package org.ag.workshop.offheap;

public class HeapDatabaseTest extends DatabaseTest {
    public static void main(String[] args) throws Exception {
        try (Database database = new HeapDatabase()) {
            fullExperiment(database);

            System.out.println("Press Enter to finish...");
            System.in.read();
        }
    }
}