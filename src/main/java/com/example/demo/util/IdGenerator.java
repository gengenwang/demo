package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.net.NetworkInterface;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: IdGenerator.java, V 0.1 2019/5/31 下午4:25 wanggengen Exp $$
 **/
@Slf4j
public class IdGenerator implements Serializable {

    private static final long serialVersionUID = 3670079982654483072L;

    private static final int LOW_ORDER_10_BITS = 0x000003ff;

    private static final int LOW_ORDER_12_BITS = 0x00000fff;

    private static final int MACHINE_IDENTIFIER;

    private static final AtomicInteger NEXT_COUNTER = new AtomicInteger(new SecureRandom().nextInt());

    private final long timestamp;

    private final int machineIdentifier;

    private final int counter;

    public static IdGenerator get() {
        return new IdGenerator();
    }

    public static int getGeneratedMachineIdentifier() {
        return MACHINE_IDENTIFIER;
    }

    public static int getCurrentCounter() {
        return NEXT_COUNTER.get();
    }

    public static IdGenerator createFromLegacyFormat(final long time, final int machine, final int inc) {
        return new IdGenerator(time, machine, inc);
    }

    public IdGenerator() {
        this(System.currentTimeMillis());
    }

    public IdGenerator(final long timestamp) {
        this(timestamp, MACHINE_IDENTIFIER, NEXT_COUNTER.getAndIncrement(), false);
    }

    public IdGenerator(final long timestamp, final int machineIdentifier, final int counter) {
        this(timestamp, machineIdentifier, counter, true);
    }

    private IdGenerator(final long timestamp, final int machineIdentifier, final int counter,
                        final boolean checkCounter) {
        if ((machineIdentifier & 0xfffffc00) != 0) {
            throw new IllegalArgumentException(
                    "The machine identifier must be between 0 and 1023 (it must fit in 10 bits).");
        }
        if (checkCounter && ((counter & 0xfffff000) != 0)) {
            throw new IllegalArgumentException("The counter must be between 0 and 4095 (it must fit in 12 bits).");
        }
        this.timestamp = timestamp;
        this.machineIdentifier = machineIdentifier;
        this.counter = counter & LOW_ORDER_12_BITS;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getMachineIdentifier() {
        return machineIdentifier;
    }

    public int getCounter() {
        return counter;
    }

    public Date getDate() {
        return new Date(timestamp);
    }

    public String toHexString() {
        return Long.toHexString(toLong());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdGenerator idGenerator = (IdGenerator) o;
        if (counter != idGenerator.counter) {
            return false;
        }
        if (machineIdentifier != idGenerator.machineIdentifier) {
            return false;
        }
        return timestamp == idGenerator.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, machineIdentifier, counter);
    }

    @Override
    public String toString() {
        return toHexString();
    }

    public long toLong() {
        return timestamp << 22 | machineIdentifier << 12 | counter;
    }

    static {
        try {
            MACHINE_IDENTIFIER = createMachineIdentifier();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO machinePiece获取方式需调整
    private static int createMachineIdentifier() {
        // build a 2-byte machine piece based on NICs info
        int machinePiece;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                sb.append(ni.toString());
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    ByteBuffer bb = ByteBuffer.wrap(mac);
                    try {
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                    } catch (BufferUnderflowException shortHardwareAddressException) { // NOPMD
                        // mac with less than 6 bytes. continue
                    }
                }
            }
            machinePiece = sb.toString().hashCode();
        } catch (Throwable t) {
            // exception sometimes happens with IBM JVM, use random
            machinePiece = (new SecureRandom().nextInt());
            log.warn("Failed to get machine identifier from network interface, using random number instead", t);
        }
        machinePiece = machinePiece & LOW_ORDER_10_BITS;
        return machinePiece;
    }

}
