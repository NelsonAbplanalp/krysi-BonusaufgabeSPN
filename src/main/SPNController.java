package main;

import java.util.ArrayList;

public class SPNController {
    private int rounds;
    private int bitsN;
    private int bitsM;
    private Sbox sbox = new Sbox();
    private Bitpermutation bitPerm = new Bitpermutation();
    private int keyLength;
    private ArrayList partKeys;
    private int keyIndex;
    private String key;
    private String text;
    private String chiffre;
    private CryptingMode cryptingMode = new CryptingMode();

    public SPNController() {
    }
}
