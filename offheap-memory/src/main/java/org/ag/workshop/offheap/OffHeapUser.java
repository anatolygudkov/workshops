package org.ag.workshop.offheap;

import static org.ag.workshop.offheap.UnsafeAccess.UNSAFE;
import static org.ag.workshop.offheap.Utils.FIXED_STRING_LENGTH;

public class OffHeapUser implements User {
    private static final int BYTE_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(int[].class);

    private final static int USERNAME_OFFSET = 0;
    private final static int AGE_OFFSET = USERNAME_OFFSET + FIXED_STRING_LENGTH;
    private final static int COLLECTED_MILES_OFFSET = AGE_OFFSET + 4;
    public final static int RECORD_SIZE = COLLECTED_MILES_OFFSET + 8 + 4 /* +4 due to the next record alignment on 8 bytes */;

    private long recordOffset;

    public void setRecordOffset(final long recordOffset) {
        this.recordOffset = recordOffset;
    }

    public void setAge(final byte age) {
        UNSAFE.putByte(recordOffset + AGE_OFFSET, age);
    }

    public byte getAge() {
        return UNSAFE.getByte(recordOffset + AGE_OFFSET);
    }

    public void setCollectedMiles(final long collectedMiles) {
        UNSAFE.putLong(recordOffset + COLLECTED_MILES_OFFSET, collectedMiles);
    }

    public long getCollectedMiles() {
        return UNSAFE.getLong(recordOffset + COLLECTED_MILES_OFFSET);
    }

    public void setUsername(final byte[] username) {
        UNSAFE.copyMemory(username, BYTE_ARRAY_OFFSET,
                null, recordOffset + USERNAME_OFFSET, username.length);
    }

    public byte[] getUsername(final byte[] to) {
        UNSAFE.copyMemory(null, recordOffset + USERNAME_OFFSET,
                to, BYTE_ARRAY_OFFSET, to.length);
        return to;
    }

    public boolean usernameEqualsTo(final byte[] username) {
        for (int i = 0; i < username.length; i++) {
            if (UNSAFE.getByte(recordOffset + USERNAME_OFFSET + i) != username[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final byte[] username = new byte[FIXED_STRING_LENGTH];
        getUsername(username);
        return "OffHeapRecord{" +
                "username=" + new String(username) +
                ", age=" + getAge() +
                ", collectedMiles=" + getCollectedMiles() +
                '}';
    }
}