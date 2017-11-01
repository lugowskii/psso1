package main.java.gof.decorator;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CipherOutputStream extends FilterOutputStream {

    private RC4 rc4 = new RC4(true);

    public CipherOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        write(new byte[]{(byte)b});
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] encrypted = new byte[b.length];
        rc4.processBytes(b, 0, b.length, encrypted, 0);
        out.write(encrypted, off * encrypted.length / len, len);
    }

}
