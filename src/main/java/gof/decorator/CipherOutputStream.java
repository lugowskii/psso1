package main.java.gof.decorator;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CipherOutputStream extends FilterOutputStream {

    private RC4V2 rc4Encryptor = new RC4V2("SECRETKEY".getBytes(), 1000, null);

    public CipherOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        byte[] encrypted = rc4Encryptor.encrypt(b, 0, b.length);
        out.write(encrypted);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] encrypted = rc4Encryptor.encrypt(b, off, len);
        out.write(encrypted, off * encrypted.length / len, len);
    }

}
