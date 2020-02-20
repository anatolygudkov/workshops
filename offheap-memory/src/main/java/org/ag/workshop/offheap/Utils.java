package org.ag.workshop.offheap;

public abstract class Utils {
    private final static int[] LENGTHS = {
            9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE
    };
    private final static char[] DIGIT_TENS = {
            '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2',
            '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4',
            '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6',
            '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8',
            '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
    };
    private final static char[] DIGIT_ONES = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };
    private final static char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static final int USERNAME_TEMPLATE_PREFIX_LEN = 4;

    public static final int FIXED_STRING_LENGTH = 64;

    public static byte[] newUsernameTemplate() {
        final byte[] result = new byte[FIXED_STRING_LENGTH];
        result[0] = 'U';
        result[1] = 'S';
        result[2] = 'E';
        result[3] = 'R';
        return result;
    }

    public static void setDummyUser(final User user, final int index, final byte[] usernameTemplate) {
        user.setAge((byte) ((index & 0b111100) + 4));
        user.setCollectedMiles(index);

        setDummyUsername(index, usernameTemplate);

        user.setUsername(usernameTemplate);
    }

    public static void setDummyUsername(final int index, final byte[] username) {
        appendPositiveInt(index, username, USERNAME_TEMPLATE_PREFIX_LEN);
    }

    private static int appendPositiveInt(final int value, final byte[] to, final int firstPos) {
        final int size = digitLength(value);

        final int resultSize = firstPos + size;

        int charPos = resultSize;    // exclusive

        int i = value;

        // Generate two digits per iteration
        while (i >= 65536) {
            int q = i / 100;
            // r = i - (q * 100);
            int r = i - ((q << 6) + (q << 5) + (q << 2));

            i = q;

            final char o = DIGIT_ONES[r];
            final char t = DIGIT_TENS[r];

            to[--charPos] = (byte) o;
            to[--charPos] = (byte) t;
        }

        // Fall through to fast mode for smaller numbers
        // assert(i <= 65536, value);
        for (;;) {
            int q = (i * 52429) >>> (16 + 3);
            int r = i - ((q << 3) + (q << 1));    // r = i - (q*10) ...
            char c = DIGITS[r];

            to[--charPos] = (byte) c;

            i = q;
            if (i == 0) {
                break;
            }
        }
        return size;
    }

    public static int digitLength(final int x) {
        for (int i = 0;; i++) {
            if (x <= LENGTHS[i]) {
                return i + 1;
            }
        }
    }

    private Utils() {}
}
