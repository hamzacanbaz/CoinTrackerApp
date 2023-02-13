package com.hamzacanbaz.cointracker.screen.coinList


import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamzacanbaz.cointracker.R
import com.hamzacanbaz.core.items.*
import com.hamzacanbaz.domain.model.allcoins.Coin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CoinListScreen(
    goToCoinDetailScreen: (coinDetailName: String) -> Unit
) {
    val viewModel = hiltViewModel<CoinListViewModel>()
    val coinList = viewModel.filtredCoinList

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {

        Column(modifier = Modifier.padding(bottom = 50.dp)) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                 SearchAppBar(viewModel = viewModel)
            }

            CoinList(coinList = coinList, goToCoinDetailScreen)
        }
    }
}

@Composable
fun SearchAppBar(
    viewModel: CoinListViewModel,
) {
    val btnTxtRank = "Rank"
    val btnTxt24hChange = "24hChange"
    val btnTxtPrice = "Price"
    val btnTxtFav = "MyFav"
    // state of the menu
    var clickCounterRankBtn by remember{ mutableStateOf(0) }
    var clickCounterPriceBtn by remember{ mutableStateOf(0) }
    var clickCounterChangeBtn by remember{ mutableStateOf(0) }
    var clickCounterFavBtn by remember{ mutableStateOf(0) }
    var sendedFilterCounter by remember{ mutableStateOf(0) }
    var sendedFavCounter by remember{ mutableStateOf(0) }
    var selectedFilterName by remember { mutableStateOf("Rank") }
    // Immediately update and keep track of query from text field changes.
    var query: String by rememberSaveable { mutableStateOf("") }
    var showClearIcon by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    if (query.isEmpty()) {
        showClearIcon = false
    } else if (query.isNotEmpty()) {
        showClearIcon = true
    }
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = query,
                onValueChange = { onQueryChanged ->
                    query = onQueryChanged
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
                        IconButton(onClick = {
                            query = ""
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = "Clear Icon"
                            )
                        }
                    }
                },
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent, textColor = Color.White, cursorColor = Color.White),
                placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
                textStyle = MaterialTheme.typography.subtitle1,

                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.background, shape = RectangleShape)
            )
        }

        Row(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
            .horizontalScroll(state = ScrollState(0)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            FilterButtonForFilter(btnText = btnTxtRank, selectedFilterName = selectedFilterName ,clickCounter = clickCounterRankBtn){
                clickCounterRankBtn = (clickCounterRankBtn +1) % 2
                sendedFilterCounter = clickCounterRankBtn
                clickCounterChangeBtn = 0
                clickCounterPriceBtn = 0
                selectedFilterName = btnTxtRank
            }
            FilterButtonForFilter(btnText = btnTxtPrice, selectedFilterName = selectedFilterName, clickCounter = clickCounterPriceBtn){
                clickCounterPriceBtn = (clickCounterPriceBtn +1) % 2
                sendedFilterCounter = clickCounterPriceBtn
                clickCounterChangeBtn = 0
                clickCounterRankBtn = 0
                selectedFilterName = btnTxtPrice
            }
            FilterButtonForFilter(btnText = btnTxt24hChange, selectedFilterName = selectedFilterName, clickCounter = clickCounterChangeBtn){
                clickCounterChangeBtn = (clickCounterChangeBtn +1) % 2
                sendedFilterCounter = clickCounterChangeBtn
                clickCounterRankBtn = 0
                clickCounterPriceBtn = 0
                selectedFilterName = btnTxt24hChange
            }
            FilterButtonForFav(btnText = btnTxtFav, clickCounter = clickCounterFavBtn){
                clickCounterFavBtn = (clickCounterFavBtn +1) % 2
                sendedFavCounter = clickCounterFavBtn
            }
        }

    }


    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch() {
            while (true) {
                searhInViewModel(viewModel = viewModel, query = query, selectedFilterName = selectedFilterName, sendedFilterCounter = sendedFilterCounter)
                delay(3000)
            }
        }
    }

    searhInViewModel(viewModel,query,selectedFilterName,sendedFilterCounter)
}

private fun searhInViewModel(viewModel: CoinListViewModel,query:String="",selectedFilterName:String = "Rank", sendedFilterCounter:Int = 0){
    viewModel.getCoinList()
    viewModel.performQuery(query, viewModel.coinList,selectedFilterName,sendedFilterCounter)
}


@Composable
fun CoinList(
    coinList: List<Coin>, goToCoinDetailScreen: (coinDetailName: String) -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn {
            items(coinList) { coinItem ->
                Box(Modifier.clickable(onClick = {
                    goToCoinDetailScreen.invoke(coinItem.id)
                })) {
                    CoinItem(item = coinItem){
                        showToast(context,message = "Coin added favorites succssfully!")
                    }
                }
            }
        }
    }

}

