package net.azarquiel.pokemon.model

import com.google.gson.annotations.SerializedName

data class ResultAll(
    @SerializedName("results")
    var pokemons:List<Pokemon>
)
data class Pokemon(var name:String="", var url:String="", var foto:String="", var number:String="")