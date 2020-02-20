package org.ag.workshop.offheap;

import org.openjdk.jol.info.ClassLayout;

public class ShowOffHeapRecordLayout {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(OffHeapUser.class).toPrintable());
    }
}
