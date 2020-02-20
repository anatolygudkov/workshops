package org.ag.workshop.offheap;

public class OffHeapDatabaseTest extends DatabaseTest {
    public static void main(String[] args) throws Exception {
        try (Database database = new OffHeapDatabase()) {
            System.out.println("Press Enter to start...");
            System.in.read();

           fullExperiment(database);

            System.out.println("Press Enter to finish...");
            System.in.read();
        }
    }
}