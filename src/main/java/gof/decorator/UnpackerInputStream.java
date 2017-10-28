package main.java.gof.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class UnpackerInputStream extends FilterInputStream {

    private static final int FILTER_BITS = 6;

    public UnpackerInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] resultArray) throws IOException {
        int encodedArrayLength = resultArray.length * FILTER_BITS / 8;
        byte[] array = new byte[encodedArrayLength];
        int result = in.read(array, 0, encodedArrayLength);
        if (result == -1) {
            return result;
        }
        String decoded = decode(Arrays.copyOfRange(array, 0, result), FILTER_BITS);
        byte[] decodedBytes = decoded.getBytes();
        for (int i = 0; i < decodedBytes.length; i++) {
            resultArray[i] = decodedBytes[i];
        }
        return decodedBytes.length;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    private String decode(byte[] encoded, int bit) {
        String strTemp = new String("");
        String strBinary = new String("");
        String strText = new String("");
        Integer tempInt = new Integer(0);
        int intTemp = 0;
        for (int i = 0; i < encoded.length; i++) {
            if (encoded[i] < 0) {
                intTemp = (int) encoded[i] + 256;
            } else
                intTemp = (int) encoded[i];
            strTemp = Integer.toBinaryString(intTemp);
            while (strTemp.length() % 8 != 0) {
                strTemp = "0" + strTemp;
            }
            strBinary = strBinary + strTemp;
        }
        for (int i = 0; i < strBinary.length(); i = i + bit) {
            if (i + bit <= strBinary.length()) {
                tempInt = tempInt.valueOf(strBinary.substring(i, i + bit), 2);
                strText = strText + toChar(tempInt.intValue());
            }
        }
        return strText;
    }

    private char toChar(int val) {
        char ch = ' ';
        switch (val) {
            case 0:
                ch = ' ';
                break;
            case 1:
                ch = 'a';
                break;
            case 2:
                ch = 'b';
                break;
            case 3:
                ch = 'c';
                break;
            case 4:
                ch = 'd';
                break;
            case 5:
                ch = 'e';
                break;
            case 6:
                ch = 'f';
                break;
            case 7:
                ch = 'g';
                break;
            case 8:
                ch = 'h';
                break;
            case 9:
                ch = 'i';
                break;
            case 10:
                ch = 'j';
                break;
            case 11:
                ch = 'k';
                break;
            case 12:
                ch = 'l';
                break;
            case 13:
                ch = 'm';
                break;
            case 14:
                ch = 'n';
                break;
            case 15:
                ch = 'o';
                break;
            case 16:
                ch = 'p';
                break;
            case 17:
                ch = 'q';
                break;
            case 18:
                ch = 'r';
                break;
            case 19:
                ch = 's';
                break;
            case 20:
                ch = 't';
                break;
            case 21:
                ch = 'u';
                break;
            case 22:
                ch = 'v';
                break;
            case 23:
                ch = 'w';
                break;
            case 24:
                ch = 'x';
                break;
            case 25:
                ch = 'y';
                break;
            case 26:
                ch = 'z';
                break;
            case 27:
                ch = '.';
                break;
            case 28:
                ch = '*';
                break;
            case 29:
                ch = ',';
                break;
            case 30:
                ch = '\\';
                break;
            case 31:
                ch = '2';
                break;
            case 32:
                ch = 'A';
                break;
            case 33:
                ch = 'B';
                break;
            case 34:
                ch = 'C';
                break;
            case 35:
                ch = 'D';
                break;
            case 36:
                ch = 'E';
                break;
            case 37:
                ch = 'F';
                break;
            case 38:
                ch = 'G';
                break;
            case 39:
                ch = 'H';
                break;
            case 40:
                ch = 'I';
                break;
            case 41:
                ch = 'J';
                break;
            case 42:
                ch = 'K';
                break;
            case 43:
                ch = 'L';
                break;
            case 44:
                ch = 'M';
                break;
            case 45:
                ch = 'N';
                break;
            case 46:
                ch = 'O';
                break;
            case 47:
                ch = 'P';
                break;
            case 48:
                ch = 'Q';
                break;
            case 49:
                ch = 'R';
                break;
            case 50:
                ch = 'S';
                break;
            case 51:
                ch = 'T';
                break;
            case 52:
                ch = 'U';
                break;
            case 53:
                ch = 'V';
                break;
            case 54:
                ch = 'W';
                break;
            case 55:
                ch = '0';
                break;
            case 56:
                ch = '1';
                break;
            case 57:
                ch = '3';
                break;
            case 58:
                ch = '4';
                break;
            case 59:
                ch = '5';
                break;
            case 60:
                ch = '6';
                break;
            case 61:
                ch = '7';
                break;
            case 62:
                ch = '8';
                break;
            case 63:
                ch = '9';
                break;
            default:
                ch = ' ';
        }
        return ch;
    }
}
