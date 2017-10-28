package main.java.gof.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CipherInputStream extends FilterInputStream {

    private RC4V2 rc4Encryptor = new RC4V2("SECRETKEY".getBytes(), 1000, null);

    private List<Byte> buffer = new ArrayList<>(32);

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
        int encodedArrayLength = b.length;
        byte[] array = new byte[encodedArrayLength];
        int result = in.read(array, 0, encodedArrayLength);
        if (result == -1) {
            return result;
        }
        byte[] decodedBytes = rc4Encryptor.decrypt(array, 0, array.length);
        for (int j = 0; j < result; j++) {
            b[j] = decodedBytes[j];
        }

        return result;
    }

}
