package com.hamzacanbaz.cointracker.screen.coinList
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamzacanbaz.cointracker.R
import com.hamzacanbaz.core.items.CoinItem
import com.hamzacanbaz.data.CoinBaseResponseModel.AllAssets.Data
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun CoinListScreen(
    name:String?=null,
    goToAlarms: () -> Unit
) {
    val viewModel: CoinListViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var job:Job? = null



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column {

            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()) {
                SearchAppBar(viewModel = viewModel)

            }
            CoinList(coinList = viewModel.coinListResponse)
        }


        LaunchedEffect(key1 = Unit) {
           job = coroutineScope.launch() {
               while(true){
                   viewModel.getCoinList()
                   delay(2000)
               }
            }
        }




    }
}



@Composable
private fun SearchAppBar(
    viewModel: CoinListViewModel,
) {
    // Immediately update and keep track of query from text field changes.
    var query: String by rememberSaveable { mutableStateOf("") }
    var showClearIcon by rememberSaveable { mutableStateOf(false) }

    if (query.isEmpty()) {
        showClearIcon = false
    } else if (query.isNotEmpty()) {
        showClearIcon = true
    }

    TextField(
        value = query,
        onValueChange = { onQueryChanged ->
            // If user makes changes to text, immediately updated it.
            query = onQueryChanged
            // To avoid crash, only query when string isn't empty.
            if (onQueryChanged.isNotEmpty()) {
                // Pass latest query to refresh search results.
              //  viewModel.performQuery(onQueryChanged)
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { query = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )
}


@Composable
fun CoinList(coinList:List<Data>){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        LazyColumn{
            items(coinList){
                    coinItem ->
                CoinItem(item = coinItem)
            }
        }
    }
    /*LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .height(110.dp)){

        itemsIndexed(items = coinList){
            index, item ->
            CoinItem(coin = item)
        }
    }*/
}



/*@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    CoinTrackerTheme {
        val coin = Data(id = "BTC", symbol = "", explorer = "hi"
            , changePercent24Hr = "", marketCapUsd = "", maxSupply = "",
        name = "BTC", priceUsd = "54353", rank = "1", supply = "213123",
        volumeUsd24Hr = "213213", vwap24Hr = "31231")
        CoinItem(coin = coin)
    }
}*/




