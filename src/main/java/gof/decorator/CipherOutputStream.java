package main.java.gof.decorator;

import java.io.*;

public class CipherOutputStream extends FilterOutputStream {

     /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public CipherOutputStream(OutputStream out) {
        super(out);
    }
}
