package main.java.gof.decorator;

import javax.crypto.Cipher;
import javax.crypto.CipherSpi;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;

import java.io.*;

public class CipherOutputStream extends FilterOutputStream {

    private RC4 rc4Encryptor = new RC4(new byte[0]);
    RC4Engine engine = new RC4Engine();
    CipherParameters params = new KeyParameter("Key".getBytes());

    public CipherOutputStream(OutputStream out) {
        super(out);
        engine.init(true, params);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        //byte[] encrypted = rc4Encryptor.encrypt(new String(b)).getBytes();
        engine.processBytes()
        byte[] encrypted = engine. .encrypt(new String(b)).getBytes();
        out.write(encrypted);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] encrypted = rc4Encryptor.encrypt(new String(b)).getBytes();
        out.write(encrypted, off*encrypted.length/len, encrypted.length);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
