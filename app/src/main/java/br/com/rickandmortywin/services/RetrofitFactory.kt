package br.com.rickandmortywin.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val URL_BASE = "https://nexus-api-henna.vercel.app/"
    //private val URL_BASE = "https://rickandmortyapi.com/api/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun listarTodosOsPersonagens(): IPersonagemService {
        return retrofitFactory.create((IPersonagemService::class.java))

    }

    fun buscarPersonagemPeloId(): IPersonagemService{
        return retrofitFactory.create(IPersonagemService::class.java)
    }

    fun homeServiceGetHomeData() : IPersonagemService {
        return retrofitFactory.create(IPersonagemService:: class.java)
    }
}