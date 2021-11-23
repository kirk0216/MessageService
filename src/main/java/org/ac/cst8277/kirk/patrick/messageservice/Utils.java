package org.ac.cst8277.kirk.patrick.messageservice;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public final class Utils {
    public static byte[] toBytes(UUID id) {
        byte[] bytes = new byte[16];

        ByteBuffer.wrap(bytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(id.getMostSignificantBits())
                .putLong(id.getLeastSignificantBits());

        return bytes;
    }

    public static UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();

        return new UUID(high, low);
    }
}
