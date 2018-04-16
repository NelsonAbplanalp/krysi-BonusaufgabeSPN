package main;

import java.util.HashMap;

public class SPNController {
    private final SPNModel model;

    public SPNController(SPNModel m) {
        model = m;
    }

    public void Encryption() {
        SPN();
    }

    public void Decryption() {

    }

    private void SPN() {
        //-------------------------------------------------------------------------------------
        // @todo: Testing variables (change with model/view properties)
        //-------------------------------------------------------------------------------------
        int rounds = 4;
        int bitsN = 4;
        int bitsM = 4;
        int bitsCount = bitsM * bitsN;
        int keyLength = 4;
        int keyIndex = 0;
        String key = "";
        String text = "";
        String chiffre = "";
        HashMap<String, String> sbox = new HashMap<>();
        HashMap<String, String> bitPerm = new HashMap<>();
        String[] partKeys = new String[key.length() / bitsCount];

        // @todo: start spn algorithm!!!!!
    }
}
