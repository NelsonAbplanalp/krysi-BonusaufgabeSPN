package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class SPNModel {
    private IntegerProperty rounds = new SimpleIntegerProperty();
    private IntegerProperty bitsN = new SimpleIntegerProperty();
    private IntegerProperty bitsM = new SimpleIntegerProperty();
    private IntegerProperty keyLength = new SimpleIntegerProperty();
    private IntegerProperty keyIndex = new SimpleIntegerProperty();
    private StringProperty key = new SimpleStringProperty();
    private StringProperty text = new SimpleStringProperty();
    private StringProperty chiffre = new SimpleStringProperty();
    private Sbox sbox; // @todo
    private ArrayList partKeys; // @todo
    private Bitpermutation bitPerm; // @todo
    private CryptingMode cryptingMode; // @todo

    public SPNModel() {
    }

    public SPNModel(int r, int bn, int bm, Sbox sb, Bitpermutation bp, int kl, ArrayList pk, int ki, String k, String t, String c, CryptingMode cm) {
        roundsProperty().setValue(r);
        bitsNProperty().setValue(bn);
        bitsMProperty().setValue(bm);
        keyLengthProperty().setValue(kl);
        keyIndexProperty().setValue(0);
//        sbox = sb;
//        bitPerm = bp;
//        partKeys = pk;
//        key = k;
//        text = t;
//        chiffre = c;
//        cryptingMode = cm;
    }

    /**********************************************************************
     * Text/Chiffre: String <-> ASCII <-> Bitstring <-> ASCII <-> String
     **********************************************************************/
    public String getBitstringByText(String text) {
        // @todo
        return "";
    }

    public String getTextByBitstring(String bits) {
        // @todo
        return "";
    }

    /*************************
     * Getter and Setters
     *************************/

    public int getRounds() {
        return rounds.get();
    }

    public IntegerProperty roundsProperty() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds.set(rounds);
    }

    public int getBitsN() {
        return bitsN.get();
    }

    public IntegerProperty bitsNProperty() {
        return bitsN;
    }

    public void setBitsN(int bitsN) {
        this.bitsN.set(bitsN);
    }

    public int getBitsM() {
        return bitsM.get();
    }

    public IntegerProperty bitsMProperty() {
        return bitsM;
    }

    public void setBitsM(int bitsM) {
        this.bitsM.set(bitsM);
    }

    public int getKeyLength() {
        return keyLength.get();
    }

    public IntegerProperty keyLengthProperty() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength.set(keyLength);
    }

    public int getKeyIndex() {
        return keyIndex.get();
    }

    public IntegerProperty keyIndexProperty() {
        return keyIndex;
    }

    public void setKeyIndex(int keyIndex) {
        this.keyIndex.set(keyIndex);
    }

    public String getKey() {
        return key.get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public String getChiffre() {
        return chiffre.get();
    }

    public StringProperty chiffreProperty() {
        return chiffre;
    }

    public void setChiffre(String chiffre) {
        this.chiffre.set(chiffre);
    }
}
