package main.java.gof.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CipherInputStream extends FilterInputStream {

    private RC4 rc4 = new RC4(true);

    public CipherInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        byte[] array = new byte[b.length];
        int result = in.read(array, 0, array.length);
        if (result == -1) {
            return result;
        }
        byte[] decodedBytes = new byte[array.length];
        rc4.processBytes(array, 0, array.length, decodedBytes, 0);
        System.arraycopy(decodedBytes, 0, b, 0, decodedBytes.length);
        return result;
    }

}
