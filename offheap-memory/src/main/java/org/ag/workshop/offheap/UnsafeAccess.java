package org.ag.workshop.offheap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAccess {
    public static final Unsafe UNSAFE;

    static {
        try {
            final Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafeField.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
