package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Jsoup ---> JSON
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<String> urlsImg = new ArrayList<>();
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listadopokemon);
        //Customadapter adapter = new Customadapter(pokemons,this);
        //-----------PRE-EJECUCION------

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Document resCompleto =  Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
                    nombres = (ArrayList<String>) resCompleto.select("[href^=/es/pokedex/]").eachText();
                    nombres.remove(0);//eliminar el primer elemento
                    for (int i= 0;i<nombres.size();i++){

                        String numPkm = String.format("%03d", i+1);
                        urlsImg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+numPkm+".png");





                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //POST-EJECUCION
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Customadapter adapter = new Customadapter(pokemons,this);
                    }
                });

            }
        }).start();


    }
}