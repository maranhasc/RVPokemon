package net.azarquiel.pokemon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.pokemon.R
import net.azarquiel.pokemon.model.ResultAll
import net.azarquiel.recyclerviewPokemons.adapter.AdapterPokemon
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: AdapterPokemon
    private lateinit var result: ResultAll
    private lateinit var rvpokemons: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvpokemons = findViewById<RecyclerView>(R.id.rvpokemons)
        initRV()
        getPokemons()

    }

    private fun initRV() {
        adapter = AdapterPokemon(this, R.layout.rowpokemon)
        rvpokemons.adapter = adapter
        rvpokemons.layoutManager = LinearLayoutManager(this)
    }

    private fun getPokemons() {
        //			String jsonTxt = IOUtils.toString(new URL(urlTxt), Charset.forName("UTF-8"));
        //			result = new Gson().fromJson(jsonTxt, ResultAll.class);

        GlobalScope.launch() {
            val jsonString = URL("https://pokeapi.co/api/v2/pokemon-species?limit=1010").readText(Charsets.UTF_8)
            launch(Main) {
                result=Gson().fromJson(jsonString, ResultAll::class.java)
                for (pokemon in result.pokemons) {
                    val foto =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                    val trozosUrl = pokemon.url.split("/")
                    pokemon.foto = "$foto${trozosUrl[trozosUrl.size - 2]}.png"
                    pokemon.number = "${trozosUrl[trozosUrl.size - 2]}"
                }
                adapter.setPokemons(result.pokemons)
            }
        }

    }
}