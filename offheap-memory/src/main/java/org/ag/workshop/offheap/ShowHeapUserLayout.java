package org.ag.workshop.offheap;

import org.openjdk.jol.info.ClassLayout;

public class ShowHeapUserLayout {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(HeapUser.class).toPrintable());
    }
}
