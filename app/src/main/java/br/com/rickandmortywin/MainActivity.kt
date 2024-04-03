package br.com.rickandmortywin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rickandmortywin.model.HomeDto
import br.com.rickandmortywin.model.Personagem
import br.com.rickandmortywin.model.Resultados
import br.com.rickandmortywin.services.RetrofitFactory
import br.com.rickandmortywin.ui.theme.RickAndMortyWinTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyWinTheme {
                // A surface container using the 'background'
                // color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme
                        .colorScheme.background
                ) {
                //requestToApi()
                 Greeting()
                }
            }
        }
    }
}

fun requestToApi() {
    var requisicao = RetrofitFactory().homeServiceGetHomeData().buscarPersonagemPeloId(1)

    //requisicao.enqueue(object : Callback<Resultados>{
    requisicao.enqueue(object : Callback<HomeDto>{
        override fun onResponse(call: Call<HomeDto>, response: Response<HomeDto>) {
            var y  = response.body()!!.enterpriseId
            var z = response.body()!!.recentPosts
        }

        override fun onFailure(call: Call<HomeDto>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })


}

@Composable
fun Greeting() {

    var buscaState = remember {
        mutableStateOf("")
    }


    var listaDePersonagens = remember {
        mutableStateOf(listOf<Personagem>())
    }

    // request to api
    //var requisicao = RetrofitFactory().listarTodosOsPersonagens().listarTodosOsPersornagens()
    var requisicao = RetrofitFactory().homeServiceGetHomeData().buscarPersonagemPeloId(1)

    //requisicao.enqueue(object : Callback<Resultados>{
    requisicao.enqueue(object : Callback<HomeDto>{
        override fun onResponse(call: Call<HomeDto>, response: Response<HomeDto>) {
            var y  = response.body()!!.enterpriseId
            var z = response.body()!!.recentPosts
        }

        override fun onFailure(call: Call<HomeDto>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })


    Image(
        painter = painterResource(
            id = R.drawable.rick_morty
        ),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop
    )


    Column(
        modifier = Modifier
            .background(Color(0x229C27B0))
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = buscaState.value,
            onValueChange = {
                buscaState.value = it
                            },
            modifier = Modifier
                .fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Button",
                        tint = Color.White
                    )
                }
            },
            label = {
                Text(
                    text = "Buscar personagem"
                )
            },
            colors = OutlinedTextFieldDefaults
                .colors(
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.Yellow,
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = Color.Yellow
                )
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        Text(
            text = "Lista de personsagens",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        // var count = 1;

        LazyColumn {
            items (listaDePersonagens.value) {
               // Text(
                    //text = "Repetindo ${count}"
                    // text = "Repetindo"
                //)
            //count++;
                Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(100.dp)
                       .padding(bottom = 8.dp),
                    colors = CardDefaults
                        .cardColors(
                        containerColor = Color(0xBF6730C7),
                        //contentColor = Color.Green
                    )
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Card {
                            AsyncImage(
                                model = it.image,
                                contentDescription = ""
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement
                                .Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 24.dp)
                        ) {
                            Text(
                                text = it.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = it.species,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }

                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    RickAndMortyWinTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Greeting()
        }

    }
}

// for(i in 1 .. 5){
//    Text(
//        text = "Texto repetindo",
//        color = Color.White
//    )
// }