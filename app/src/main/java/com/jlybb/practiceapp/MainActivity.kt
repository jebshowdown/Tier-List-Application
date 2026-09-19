package com.jlybb.practiceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlybb.practiceapp.ui.theme.PracticeappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeappTheme {
                    Skeleton()
                }
            }
        }
    }

@Composable
fun Tiers(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        TierBox('S', 0xffff6961, modifier = Modifier.weight(1f))
        TierBox('A', 0xFFffb347, modifier = Modifier.weight(1f))
        TierBox('B', 0xfffada5e, modifier = Modifier.weight(1f))
        TierBox('C', 0xFFb2ec5d, modifier = Modifier.weight(1f))
        TierBox('D', 0xff87ceeb, modifier = Modifier.weight(1f))
        TierBox('E', 0xFFb39eb5, modifier = Modifier.weight(1f))
        TierBox('F', 0xfff984e5, modifier = Modifier.weight(1f))

    }
}

@Composable
fun TierBox(letter:Char, color:Long, modifier:Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(Color(color))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            color = Color.White,
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Skeleton(){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Tier List",
                        color = Color.White
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Images go here"
                )
            }
        }
    ) {
        innerPadding ->
        Tiers(
            modifier = Modifier.padding(innerPadding)
        )
    }

}