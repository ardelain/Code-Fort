package com.company;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Convertisseur {

    /**
     *
     * @param message
     * @return
     */
    public static byte[] StringToBytes(String message){
        System.out.println("StringToBytes");
        byte[] b = message.getBytes();
        for (byte bb: b) {
            System.out.print(bb+" ");
        }
        System.out.println("");
        return b;
    }

   //Converting from bytes to booleans:

    /**
     *
     * @param bytes
     * @return
     */
   public static boolean[] convertToBooleanArray(byte[] bytes) {
        System.out.println("convertToBooleanArray");
        boolean [] result = new boolean[bytes.length * 8];
        for (int i=0; i<bytes.length; i++) {
            int index = i*8;
            result[index+0] = (bytes[i] & 0x80) != 0;
            result[index+1] = (bytes[i] & 0x40) != 0;
            result[index+2] = (bytes[i] & 0x20) != 0;
            result[index+3] = (bytes[i] & 0x10) != 0;
            result[index+4] = (bytes[i] & 0x8) != 0;
            result[index+5] = (bytes[i] & 0x4) != 0;
            result[index+6] = (bytes[i] & 0x2) != 0;
            result[index+7] = (bytes[i] & 0x1) != 0;
        }
        return result;
    }

    public static boolean[] byteArray2BitArray(byte[] bytes) {
        boolean[] bits = new boolean[bytes.length * 8];
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
                bits[i] = true;
        }
        return bits;
    }

    public static boolean convertToBoolean(byte[] array) {
        return (array[0] > 0 ? true : false );
    }

    //Converting from booleans to bytes by cutting off the last few booleans:
    /**
     * This will round down to the nearest number of bytes.  So it will chop off the last few booleans.
     * Eg: If there are 9 booleans, then that will be 1 byte, and it will lose the last boolean.
     */
    public static byte[] convertToByteArray(boolean[] booleans) {
        byte[] result = new byte[booleans.length/8];
        for (int i=0; i<result.length; i++) {
            int index = i*8;
            byte b = (byte)(
                    (booleans[index+0] ? 1<<7 : 0) +
                            (booleans[index+1] ? 1<<6 : 0) +
                            (booleans[index+2] ? 1<<5 : 0) +
                            (booleans[index+3] ? 1<<4 : 0) +
                            (booleans[index+4] ? 1<<3 : 0) +
                            (booleans[index+5] ? 1<<2 : 0) +
                            (booleans[index+6] ? 1<<1 : 0) +
                            (booleans[index+7] ? 1 : 0));
            result[i] = b;
        }
        return result;
    }



    public static byte[] intToByte(int value) {
        ByteBuffer _intShifter = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE)
                .order(ByteOrder.LITTLE_ENDIAN);
        _intShifter.clear();
        _intShifter.putInt(value);
        return _intShifter.array();
    }

    public static int byteToInt(byte[] data)
    {
        ByteBuffer _intShifter = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE)
                .order(ByteOrder.LITTLE_ENDIAN);
        _intShifter.clear();
        _intShifter.put(data, 0, Integer.SIZE / Byte.SIZE);
        _intShifter.flip();
        return _intShifter.getInt();
    }
}
