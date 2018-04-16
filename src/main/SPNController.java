package main;

import sun.security.util.Length;

import java.util.HashMap;

public class SPNController {
    private final SPNModel model;

    public SPNController(SPNModel m) {
        model = m;
    }

    public void Encryption() {
        SpnEncryption();
    }

    public void Decryption() {

    }

    private void SpnEncryption() {
        //-------------------------------------------------------------------------------------
        // @todo: Testing variables (change with model/view properties)
        //-------------------------------------------------------------------------------------
        // Inputs:
        int rounds = 4;
        int bitsN = 4;
        int bitsM = 4;
        HashMap<boolean[], boolean[]> sbox = new HashMap<>();

        boolean[] key;
        int keyIndex = 0;
        int keyLength = key.length;

        boolean[] text;
        boolean[] chiffre;

        // Prepared variables
        int bitsCount = bitsM * bitsN;
        int[] bitPerm = new int[bitsCount];
        String[] partKeys = new String[keyLength / bitsCount];

        //-------------------------------------------------------------------------------------

        sbox = getSbox(
            new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} },
            new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} }
        );
    }

    /**
     *
     * Get the sbox in an Hashmap from the Frontend input lines
     * Example:
     *   xRow:      x | 00 | 01 | 10 | 11 |
     *  sxRow:   S(x) | 11 | 10 | 01 | 00 |
     *
     * @param xRow First row of S-Box from Frontend
     * @param sxRow Second row of S-Box from Frontend
     * @return
     */
    public static HashMap<boolean[], boolean[]> getSbox(boolean[][] xRow, boolean[][]sxRow) {
        HashMap<boolean[], boolean[]> sbox = new HashMap<>();

        for (int i = 0; i < xRow.length; i++) {
            int bitLength = sxRow[i].length;
            boolean[] bitStringX  = new boolean[bitLength];
            boolean[] bitStringSx = new boolean[bitLength];

            for (int j = 0; j < bitLength; j++) {
                bitStringX[j]  = xRow[i][j];
                bitStringSx[j] = sxRow[i][j];
            }

            sbox.put(bitStringX, bitStringSx);
        }

//        boolean[][] a = new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} };
//        boolean[][] b = new boolean[][] { new boolean[]{false, true, false}, new boolean[]{false, true, false}, new boolean[]{true, false, true} };
//
//        HashMap<boolean[], boolean[]> c = new HashMap<boolean[], boolean[]>() { [true, false, true], [false, true, false]   ||    [true, false, true], [false, true, false]   ||  ....}

        return sbox;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean[] XOR(boolean[] x, boolean[] y) {
        int lengthDifference = x.length - y.length;

        // If one variable has less bits: fill it with 0s
        if (lengthDifference < 0) {
            x = fillWithZeroBits(x, y.length);
        } else if (lengthDifference > 0) {
            y = fillWithZeroBits(y, x.length);
        }

        boolean[] result = new boolean[x.length];
        // XOR : 0x0:0, 1x1:0, 1x0:1, 0x1:1
        for (int i = 0; i < x.length; i++) {
            result[i] = x[i] != y[i];
        }

        return result;
    }

    public static boolean[] fillWithZeroBits(boolean[] a, int bLength) {
        for (int i = a.length; i < bLength; i++) {
            a[i] = false;
        }
        return a;
    }
}
