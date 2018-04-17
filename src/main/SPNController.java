package main;

import sun.security.util.Length;

import java.util.HashMap;

public class SPNController {
    private final SPNModel model;

    public SPNController(SPNModel m) {
        model = m;
    }

    public void Encryption() {
        boolean[] chiffreBS = SpnEncryption();

        // @todo: get chiffre bit string and send to Frontend
        System.out.println(chiffreBS.toString());
    }

    public void Decryption() {

    }

    /**
     * Encrypt a given clear text with the SPN defined (from Frontend)
     */
    private boolean[] SpnEncryption() {
        boolean[] textBS    = new boolean[]{false, true, true, false, true, true};
        boolean[] chiffreBS = new boolean[textBS.length];

        //-------------------------------------------------------------------------------------
        // @todo: Testing variables (change with model/view properties)
        //-------------------------------------------------------------------------------------
        // Frontend inputs:
        int rounds = 4;
        int bitsN = 4;
        int bitsM = 4;
        HashMap<boolean[], boolean[]> sbox = getSbox(
                new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} },
                new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} },
                false
        );
        int[] bitPerm = getBitPermutationArray(new int[]{0, 1, 2, 3}, new int[]{3, 2, 1, 0});

        boolean[] key = new boolean[]{true, false, true, true, false, false};
        int keyIndex = 0;
        int keyLength = key.length;
        int subKeyStartPos = 4;

        // Prepared variables
        int bitStringLength = bitsM * bitsN;

        // 1. Create round keys (subKeys)
        boolean[][] subKeys = getSubKeys(key, rounds, subKeyStartPos, bitStringLength);

        //-------------------------------------------------------------------------------------

        // 2. Initial white step
        chiffreBS = XOR(textBS, subKeys[0]);

        // 3. Rounds loop with XOR subKeys, S-Box, BitPermutation
        for (int i = 1; i < rounds; i++) {
            chiffreBS = getBitStringBySbox(chiffreBS, sbox, bitsM);
            chiffreBS = getBitstringByBitPermutation(chiffreBS, bitPerm);
            chiffreBS = XOR(chiffreBS, subKeys[i]);
        }

        // 4. Last round with XOR subKeys and S-Box
        chiffreBS = getBitStringBySbox(chiffreBS, sbox, bitsM);
        chiffreBS = XOR(chiffreBS, subKeys[rounds]);

        return chiffreBS;
    }

//    private boolean[] SpnDecryption() {
//        boolean[] chiffreBS = new boolean[]{false, true, true, false, true, true};
//        boolean[] textBS    = new boolean[chiffreBS.length];
//
//        //-------------------------------------------------------------------------------------
//        // @todo: Testing variables (change with model/view properties)
//        //-------------------------------------------------------------------------------------
//        // Frontend inputs:
//        int rounds = 4;
//        int bitsN = 4;
//        int bitsM = 4;
//        HashMap<boolean[], boolean[]> sbox = getSbox(
//                new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} },
//                new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} }
//        );
//        int[] bitPerm = getBitPermutationArray(new int[]{0, 1, 2, 3}, new int[]{3, 2, 1, 0});
//
//        boolean[] key = new boolean[]{true, false, true, true, false, false};
//        int keyIndex = 0;
//        int keyLength = key.length;
//
//        // Prepared variables
//        int bitsCount = bitsM * bitsN;
//        String[] subKeys = new String[keyLength / bitsCount];
//
//        //-------------------------------------------------------------------------------------
//
//        // 1. Create round keys (subKeys)
//
//        // 2. Get S-Box Inverse and change subKeys (switch and BitPermutation)
//
//
//
//        // 3. Initial white step
//
//
//        // 4. Rounds loop with XOR subKeys, S-Box, BitPermutation
//
//
//        // 5. Last round with XOR subKeys and S-Box
//
//
//        return textBS;
//    }


    /******************************************************
     * Get data in correct form
     ******************************************************/

    /**
     *
     * Get the sbox from the Frontend input lines in a Hashmap
     * Example:
     *   xRow:      x | 00 | 01 | 10 | 11 |
     *  sxRow:   S(x) | 11 | 10 | 01 | 00 |
     *
     * @param xRow       First row of S-Box from Frontend
     * @param sxRow      Second row of S-Box from Frontend
     * @param getInverse Say if S-Box Inverse should be returned
     * @return HashMap<boolean[], boolean[]>
     */
    public static HashMap<boolean[], boolean[]> getSbox(boolean[][] xRow, boolean[][]sxRow, boolean getInverse) {
        HashMap<boolean[], boolean[]> sbox = new HashMap<>();

        for (int i = 0; i < xRow.length; i++) {
            int bitLength = sxRow[i].length;
            boolean[] bitStringX  = new boolean[bitLength];
            boolean[] bitStringSx = new boolean[bitLength];

            for (int j = 0; j < bitLength; j++) {
                bitStringX[j]  = xRow[i][j];
                bitStringSx[j] = sxRow[i][j];
            }

            if (getInverse) {
                sbox.put(bitStringSx, bitStringX);
            } else {
                sbox.put(bitStringX, bitStringSx);
            }
        }

//        boolean[][] a = new boolean[][] { new boolean[]{true, false, true},  new boolean[]{true, false, true},  new boolean[]{true, false, true} };
//        boolean[][] b = new boolean[][] { new boolean[]{false, true, false}, new boolean[]{false, true, false}, new boolean[]{true, false, true} };
//
//        HashMap<boolean[], boolean[]> c = new HashMap<boolean[], boolean[]>() { [true, false, true], [false, true, false]   ||    [true, false, true], [false, true, false]   ||  ....}

        return sbox;
    }

    /**
     *
     * Get the BitPermutation from the Frontend input lines in an array
     * Example:
     *   xRow:      x | 0 | 1 | 2 | 3 |
     *  sxRow:   B(x) | 3 | 2 | 1 | 0 |
     *
     * @param xRow  First row of BitPermutation from Frontend
     * @param bxRow Second row of BitPermutation from Frontend
     * @return int[]
     */
    public static int[] getBitPermutationArray(int[] xRow, int[] bxRow) {
        int[] bitPermutation = new int[xRow.length];

        for (int i = 0; i < xRow.length; i++) {
            bitPermutation[xRow[i]] = bxRow[i];
        }

        return bitPermutation;
    }

    /**
     * For each round get a subKey to encrypt
     *
     * @param key             Given key of encryption/decryption
     * @param rounds          Amounts of rounds for SPN
     * @param subKeyStartPos  Start position of each subKey (f.e. 4: 4i, so every 4. index is a start for the next subKey)
     * @param bitStringLength Length of one subKey bit string
     * @return boolean[][]
     */
    public static boolean[][] getSubKeys(boolean[] key, int rounds, int subKeyStartPos, int bitStringLength) {
        boolean[][] subKeys       = new boolean[rounds][bitStringLength];

        // Create subKey for each round of the SPN
        for (int round = 0; round <= rounds; round++) {
            int subKeyIndex  = 0;
            boolean[] subKey = new boolean[bitStringLength];

            // Get the subKey bits from key bit string (from current start position until bitStringLength)
            for (int j = round*subKeyStartPos; j < round + bitStringLength; j++) {
                subKey[subKeyIndex] = key[j];
                subKeyIndex++;
            }

            subKeys[round] = subKey;
        }

        return subKeys;
    }

    /**********************************************************************
     * SPN algorithm methods: encrypt/decrypt bit string with SPN
     **********************************************************************/

    /**
     * Get the XOR of two bit strings
     *    ( XOR : 0x0:0, 1x1:0, 1x0:1, 0x1:1 )
     *
     * @param x First bit string for xor comparison
     * @param y Second bit string for xor comparison
     * @return boolean[]
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

        for (int i = 0; i < x.length; i++) {
            result[i] = x[i] != y[i];
        }

        return result;
    }

    /**
     * Fill a bit string with Zeros to fit the given length
     *
     * @param a       Bit string to be filled with Zeros
     * @param bLength Length the given bit string needs to have
     * @return boolean[]
     */
    public static boolean[] fillWithZeroBits(boolean[] a, int bLength) {
        for (int i = a.length; i < bLength; i++) {
            a[i] = false;
        }
        return a;
    }

    /**
     * Get a given bit string with bit permutation applied
     *
     * @param bitString Bit string to be changed
     * @param bitPerm   Bit Permutation which changes the given bit string
     * @return boolean[]
     */
    public static boolean[] getBitstringByBitPermutation(boolean[] bitString, int[] bitPerm) {
        boolean[] bitPermBitString = new boolean[bitPerm.length];

        // @todo: use bit permutation on given bit string

        return bitPermBitString;
    }

    /**
     * Get a given bit string with S-Box applied
     * @param bitString           Bit string to be changed
     * @param sbox                S-Box which changes the given bit string
     * @param sboxBitStringLength Length of bit string inside the S-Box
     * @return boolean[]
     */
    public static boolean[] getBitStringBySbox(boolean[] bitString, HashMap<boolean[], boolean[]> sbox, int sboxBitStringLength) {
        boolean[] sboxBitString = new boolean[bitString.length];

        for (int i = 0; i < bitString.length; i += sboxBitStringLength) {
            boolean[] searchedBitString = getBitStringPart(bitString, i, i+sboxBitStringLength);
            boolean[] newBitString      = sbox.get(searchedBitString);

            for (int j = 0; j < sboxBitStringLength; j++) {
                sboxBitString[i+j] = newBitString[j];
            }
        }

        return sboxBitString;
    }

    /**
     * Get a certain part of a given bit string (f.e. bits from 0 to 4)
     *
     * @param bitString Bit string from which a part is needed
     * @param startBit  Start position of the needed bit string part
     * @param endBit    End position of the needed bit string part
     * @return boolean[]
     */
    public static boolean[] getBitStringPart(boolean[] bitString, int startBit, int endBit) {
        boolean[] bitStringPart = new boolean[endBit-startBit];
        int partIndex           = 0;

        for (int i = startBit; i < endBit; i++) {
            bitStringPart[partIndex] = bitString[i];
            partIndex++;
        }

        return bitStringPart;
    }
}
