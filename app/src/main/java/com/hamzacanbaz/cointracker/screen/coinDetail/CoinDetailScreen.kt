package com.hamzacanbaz.cointracker.screen.coinDetail

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.*
import com.hamzacanbaz.domain.model.coinDetail.Coin
import com.hamzacanbaz.domain.model.coinPriceHistory.Data
import com.hamzacanbaz.domain.util.ResultData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CoinDetailScreen(
    coinName: String? = null
) {
    val viewModel = hiltViewModel<CoinDetailViewModel>()
    viewModel.getCoinDetail(coinName)
//    viewModel.getCoinDetailHistory(coinName, "d1")
    val coinDetail = viewModel.coinDetail
    val coinDetailHistory by viewModel.getCoinDetailHistory()
    val timeRangeState = remember { mutableStateOf(TimeRange.ONE_HOUR) }

    LaunchedEffect(key1 = Unit) {
        this.launch() {
            while (true) {
                viewModel.getCoinDetail(coinName)
                delay(3000)
            }
        }
    }

    when (coinDetailHistory) {
        is ResultData.Loading -> {
            println("loading")
        }
        is ResultData.Success -> {
            println("success")
            val coinHistoryPrice = (coinDetailHistory as ResultData.Success<List<Data>>).data

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.Black
            ) {
                App(
                    coinDetail,
                    coinHistoryPrice ?: listOf(),
                    viewModel,
                    timeRangeState
                ) {
                    timeRangeState.value = it
                }
            }
        }
        is ResultData.Failed -> {
            println("failed")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getCoinDetailHistoryFromRemote(coinName, TimeRange.ONE_DAY.id)
    }
}

@Composable
fun App(
    coinDetail: Coin,
    coinDetailHistory: List<Data>,
    viewModel: CoinDetailViewModel,
    timeRange: MutableState<TimeRange>,
    timeRangeChanged: (TimeRange) -> Unit
) {

    val timeRangeState = remember { mutableStateOf(timeRange) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        CoinPriceAndChangePercentage(coinDetail)

        Spacer(modifier = Modifier.height(32.dp))

//        Graphic(coinDetail)
        TimeRangePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            selectedTimeRange = timeRangeState.value.value
        ) { timeRange ->
            timeRangeChanged.invoke(timeRange)
            viewModel.getCoinDetailHistoryFromRemote(coinDetail.id, timeRange.id)
        }
        if (coinDetailHistory.isNotEmpty()) {
            LineGraphic(coinDetailHistory)
        }

        CoinDetailValueItem(
            infoText = "Supply",
            valueText = coinDetail.supply?.formatFloatingPoint(2) ?: ""
        )

        Divider(color = Color.Gray)

        CoinDetailValueItem(
            infoText = "Max Supply",
            valueText = coinDetail.maxSupply?.formatFloatingPoint(2) ?: ""
        )

        Divider(color = Color.Gray)

        CoinDetailValueItem(
            infoText = "Volume",
            valueText = coinDetail.volumeUsd24Hr?.formatFloatingPoint(2) ?: ""
        )

        Divider(color = Color.Gray)


        CoinDetailValueItem(
            infoText = "Volume Weighted Average Price",
            valueText = coinDetail.vwap24Hr?.formatFloatingPoint(2) ?: ""
        )

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = Color.White)
}

@Composable
fun CoinPriceAndChangePercentage(coinDetail: Coin) {
    Text(
        text = "Current Price",
        color = Color.White,
        fontSize = 16.sp
    )
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            text = "$" + (coinDetail.priceUsd?.formatFloatingPoint(4) ?: "00"),
            color = Color.White,
            fontSize = 24.sp
        )
        CoinChangePercentage(changePercentage = coinDetail.changePercent24Hr?.formatFloatingPoint(2))
    }

}

@Composable
fun CoinChangePercentage(changePercentage: String?) {
    var color = Color.Green
    var changePercentageValue = changePercentage
    if (changePercentage != null) {
        color = if (changePercentage[0] == '-') {
            Color.Red
        } else {
            changePercentageValue = "+$changePercentage"
            Color.Green
        }
    } else {
        changePercentageValue = "0.00"
    }


    Box(
        Modifier
            .padding(start = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color)
    ) {
        Text(
            text = "$changePercentageValue%",
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun Graphic(coinDetail: Coin) {

    val values: ArrayList<CandleEntry> = ArrayList()

    for (i in 0 until 20) {
        val multi: Double = 10.0 + 1
        val `val` = (Math.random() * 40).toFloat() + multi
        val high = (Math.random() * 9).toFloat() + 8f
        val low = (Math.random() * 9).toFloat() + 8f
        val open = (Math.random() * 6).toFloat() + 1f
        val close = (Math.random() * 6).toFloat() + 1f
        val even = i % 2 == 0
        values.add(
            CandleEntry(
                i.toFloat(), (`val` + high).toFloat(),
                (`val` - low).toFloat(),
                (if (even) `val` + open else `val` - open).toFloat(),
                (if (even) `val` - close else `val` + close).toFloat()
            )
        )
    }


    val set1 = CandleDataSet(values, "Data Set")

    set1.setDrawIcons(false)
    set1.axisDependency = AxisDependency.LEFT
//        set1.setColor(Color.rgb(80, 80, 80));
    //        set1.setColor(Color.rgb(80, 80, 80));
    set1.shadowColor = Color.DarkGray.toArgb()
    set1.shadowWidth = 0.7f
    set1.decreasingColor = Color.Red.toArgb()
    set1.decreasingPaintStyle = Paint.Style.FILL
    set1.increasingColor = Color.Green.toArgb()
    set1.increasingPaintStyle = Paint.Style.FILL
    set1.neutralColor = Color.Blue.toArgb()

    val candleData = CandleData(set1)

    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {


        AndroidView(modifier = Modifier.fillMaxSize(), factory = {
            CandleStickChart(it).apply {
                setBackgroundColor(Color(0xFFBB86FC).toArgb())
                description.isEnabled = false
                isDragEnabled = false
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(false)
                axisRight.isEnabled = false
                legend.isEnabled = false
                setTouchEnabled(false)
                setScaleEnabled(false)
                setDrawGridBackground(false)
                setDrawBorders(false)
                data = candleData
                invalidate()
            }
        })
    }
}

@Composable
fun LineGraphic(data: List<Data>) {
    println("size+${data.size}")
    val entryList = data.map {
        Entry(it.time.toFloat(), it.priceUsd.toFloat())
    }
    val lineDataSet = LineDataSet(entryList, "market_price").apply {
        mode = LineDataSet.Mode.CUBIC_BEZIER
        color = Color.Cyan.toArgb()
        highLightColor = Color.Green.toArgb()
        lineWidth = 2f
        setDrawFilled(true)
        setDrawCircles(false)
    }



    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                isDragEnabled = false
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(false)
                axisLeft.textColor = Color.White.toArgb()
                axisRight.isEnabled = false
                legend.isEnabled = false
                setTouchEnabled(false)
                setScaleEnabled(false)
                setDrawGridBackground(false)
                setDrawBorders(false)
                invalidate()

                setLineDataSet(lineDataSet = lineDataSet)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(300.dp)
    )


}

private const val DEFAULT_ANIMATE_XY_DURATION: Int = 300
private const val MIN_ENTRY_COUNT_FOR_ANIMATION: Int = 30

fun LineChart.setLineDataSet(
    lineDataSet: LineDataSet? = null,
    animateXDuration: Int = 0
) {
    if (lineDataSet != null) {
        clear()
        data = LineData(lineDataSet).apply {
            setDrawValues(false)
        }
    }

    if (lineDataSet?.entryCount.orZero() > MIN_ENTRY_COUNT_FOR_ANIMATION) {
        animateX(if (animateXDuration > 0) animateXDuration else DEFAULT_ANIMATE_XY_DURATION)
    }
}

fun Int?.orZero() = this ?: 0


@Composable
fun CoinDetailValueItem(infoText: String, valueText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp),
            text = infoText,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 16.dp),
            text = valueText,
            color = Color.White,
            textAlign = TextAlign.End
        )
    }
}

enum class TimeRange(val id: String) {
    ONE_MIN("m1"), FIVE_MIN("m5"), FIFTEEN_MIN("m15"), THIRTY_MIN("m30"), ONE_HOUR("h1"),
    TWELVE_HOUR("h12"), ONE_DAY("d1");
}


@Composable
fun TimeRangePicker(
    modifier: Modifier = Modifier,
    selectedTimeRange: TimeRange = TimeRange.FIFTEEN_MIN,
    onTimeRangeSelected: (TimeRange) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TimeRangeChip(
            time = "1m",
            isSelected = selectedTimeRange == TimeRange.ONE_MIN
        ) {
            onTimeRangeSelected(TimeRange.ONE_MIN)
        }

        TimeRangeChip(
            time = "5m",
            isSelected = selectedTimeRange == TimeRange.FIVE_MIN
        ) {
            onTimeRangeSelected(TimeRange.FIVE_MIN)
        }

        TimeRangeChip(
            time = "15m",
            isSelected = selectedTimeRange == TimeRange.FIFTEEN_MIN
        ) {
            onTimeRangeSelected(TimeRange.FIFTEEN_MIN)
        }

        TimeRangeChip(
            time = "30m",
            isSelected = selectedTimeRange == TimeRange.THIRTY_MIN
        ) {
            onTimeRangeSelected(TimeRange.THIRTY_MIN)
        }

        TimeRangeChip(
            time = "1h",
            isSelected = selectedTimeRange == TimeRange.ONE_HOUR
        ) {
            onTimeRangeSelected(TimeRange.ONE_HOUR)
        }

        TimeRangeChip(
            time = "12h",
            isSelected = selectedTimeRange == TimeRange.TWELVE_HOUR
        ) {
            onTimeRangeSelected(TimeRange.TWELVE_HOUR)
        }
    }
}

@Composable
private fun TimeRangeChip(
    time: String,
    isSelected: Boolean,
    onTimeRangeSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color.White else Color.Black,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onTimeRangeSelected()
            },
    ) {
        Text(
            text = time,
            color = if (isSelected) Color.Black else Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}


fun String.formatFloatingPoint(afterPointCount: Int): String {
    val split = this.split(".")
    return if (this.length > 1) {
        split[0] + "." + split[1].substring(0, afterPointCount)
    } else {
        split[0] + "." + "0".repeat(afterPointCount)
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun previewUi() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        App(Coin(), listOf(), hiltViewModel(), mutableStateOf(TimeRange.ONE_DAY)) {}
    }
}


