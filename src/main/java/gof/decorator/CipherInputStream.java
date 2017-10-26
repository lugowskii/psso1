package main.java.gof.decorator;

import java.io.*;
import java.util.Arrays;

public class CipherInputStream extends FilterInputStream {

    private RC4 rc4Encryptor = new RC4(new byte[0]);

    public CipherInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int encodedArrayLength = 2 * b.length;
        byte[] array = new byte[encodedArrayLength];
        int result = in.read(array, 0, encodedArrayLength);
        if (result == -1) {
            return result;
        }
        String decoded = rc4Encryptor.decrypt(new String(Arrays.copyOfRange(array, 0, result)));
        byte[] decodedBytes = decoded.getBytes();
        for(int i=0; i<decodedBytes.length; i++){
            b[i] = decodedBytes[i];
        }
        return decodedBytes.length;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        byte[] array = new byte[2 * b.length];
        int result = in.read(array, 0, 2 * b.length);
        if (result == -1) {
            return result;
        }
        String decoded = rc4Encryptor.decrypt(new String(Arrays.copyOfRange(array, 0, result)));
        byte[] decodedBytes = decoded.getBytes();
        for(int i=0; i< decodedBytes.length; i++){
            b[i] = decodedBytes[i];
        }
        return decodedBytes.length;
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public int available() throws IOException {
        return super.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
    }

    @Override
    public boolean markSupported() {
        return super.markSupported();
    }
}
