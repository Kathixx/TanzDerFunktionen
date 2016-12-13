package com.example.arabellaprivat.tanzderfunktionen;

/**
 * Created by Vicky on 13.12.2016.
 */

public class DatabaseTable_2 {

    //Variable für den Text der Entbewertung
    private String text;

    //Konstruktor
    public DatabaseTable_2 (String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        String output =  text;

        return output;
    }


}

