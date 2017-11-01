package main.java.gof.decorator;

public class RC4 {

    private static final int STATE_LENGTH = 256;

    private byte[] engineState = null;
    private int x = 0;
    private int y = 0;
    private byte[] workingKey = null;
    private byte[] defaultKey = "SECRETKEY".getBytes();

    public RC4(boolean useDefaultKey){
        if (useDefaultKey){
            init(this.defaultKey);
        }
    }

    //if you want to use different key than default
    public void init(byte[] key) {
            workingKey = key;
            setKey(workingKey);
    }

    /*encryption and decryption is symetric*/
    public void processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) {
        for (int i = 0; i < len; i++) {
            x = (x + 1) & 0xff;
            y = (engineState[x] + y) & 0xff;

            // swap
            byte tmp = engineState[x];
            engineState[x] = engineState[y];
            engineState[y] = tmp;

            // xor
            out[i + outOff] = (byte) (in[i + inOff]
                    ^ engineState[(engineState[x] + engineState[y]) & 0xff]);
        }
    }

    private void setKey(byte[] keyBytes) {
        workingKey = keyBytes;

        x = 0;
        y = 0;

        if (engineState == null) {
            engineState = new byte[STATE_LENGTH];
        }

        // reset the state of the engine
        for (int i = 0; i < STATE_LENGTH; i++) {
            engineState[i] = (byte) i;
        }

        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < STATE_LENGTH; i++) {
            i2 = ((keyBytes[i1] & 0xff) + engineState[i] + i2) & 0xff;
            // swap
            byte tmp = engineState[i];
            engineState[i] = engineState[i2];
            engineState[i2] = tmp;
            i1 = (i1 + 1) % keyBytes.length;
        }
    }
}