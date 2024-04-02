package br.com.rickandmortywin.services

import br.com.rickandmortywin.model.Personagem
import br.com.rickandmortywin.model.Resultados
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IPersonagemService {

    @GET("character")
    fun listarTodosOsPersornagens(): Call<Resultados>

    @GET("character/{codigo}")
    fun buscarPersonagemPeloId(@Path("codigo") id: Int): Call<Personagem>
}