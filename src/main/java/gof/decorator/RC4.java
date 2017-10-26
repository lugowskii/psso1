package main.java.gof.decorator;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class RC4 {
    private int[] S = new int[256];
    private final int keylen;


    public RC4(byte[] key) {
        if (key.length == 0) {
            key = "Key".getBytes();
        }
        if (key.length < 1 || key.length > 256) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        } else {
            keylen = key.length;
            for (int i = 0; i < 256; i++) {
                S[i] = i;
            }

            int j = 0;

            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + key[i % keylen]) % 256;
                int temp = S[i];
                S[i] = S[j];
                S[j] = temp;
            }
        }
    }

    public String encrypt(final String message) {
        byte[] plaintext = new byte[0];
        try {
            plaintext = message.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
        }

        byte[] ciphertext = toBytes(transform(toInts(plaintext)));
        return Hex.encodeHexString(ciphertext);
    }

    private int[] transform(final int[] input) {
        final int[] output = new int[input.length];

        final int[] S = new int[this.S.length];
        System.arraycopy(this.S, 0, S, 0, S.length);

        int i = 0, j = 0, k, t;
        for (int counter = 0; counter < input.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            S[i] ^= S[j];
            S[j] ^= S[i];
            S[i] ^= S[j];
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            output[counter] = input[counter] ^ k;
        }

        return output;
    }

    private int[] toInts(byte[] bytes) {
        int[] output = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

    private byte[] toBytes(int[] ints) {
        byte[] output = new byte[ints.length];
        for (int i = 0; i < ints.length; i++) {
            output[i] = (byte) ints[i];
        }
        return output;
    }

    public String decrypt(final String ciphertext) {
        try {
            int[] cipherints = toInts(Hex.decodeHex(ciphertext.toCharArray()));
            for (int i = 0; i < cipherints.length; i++) {
                cipherints[i] = cipherints[i] & 0xff;
            }
            byte[] plaintext = toBytes(transform(cipherints));
            return new String(plaintext, Charset.forName("ASCII"));
        } catch (DecoderException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        byte[] key = "Key".getBytes();
        RC4 rc4 = new RC4(key);
        String message = "pedia";
        System.out.println("Message: " + message);
        String cipherText = rc4.encrypt(message);
        System.out.println("Encrypted: " + cipherText);
        String decrypted = rc4.decrypt(cipherText);
        String d1 = rc4.decrypt(cipherText.substring(0,5));
        String d2 = rc4.decrypt(cipherText.substring(5,cipherText.length()));
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Decrypted2: " + d1+d2);
    }
}