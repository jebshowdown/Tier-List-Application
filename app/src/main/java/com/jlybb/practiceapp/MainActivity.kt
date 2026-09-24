package com.jlybb.practiceapp

import android.content.ClipData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.net.Uri
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.jlybb.practiceapp.ui.theme.PracticeappTheme
import coil3.compose.AsyncImage
import kotlin.collections.plus

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeappTheme {
                    MainApp()
                }
            }
        }
    }

@Composable
fun Tiers(modifier: Modifier = Modifier, tierUris: Map<Char, List<Uri?>>, OnDrop: (Uri, Char) -> Unit) { // sets up the tier sections on the left column
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            TierBox('S', 0xffff6961)
            PlacementGrid(modifier = Modifier.weight(1f), 'S', tierUris = tierUris, OnDrop = OnDrop)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            TierBox('A', 0xffffb347)
            PlacementGrid(modifier = Modifier.weight(1f), 'A', tierUris = tierUris, OnDrop = OnDrop)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            TierBox('B', 0xfffada5e)
            PlacementGrid(modifier = Modifier.weight(1f), 'B', tierUris = tierUris, OnDrop = OnDrop)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            TierBox('C', 0xffb2ec5d)
            PlacementGrid(modifier = Modifier.weight(1f), 'C', tierUris = tierUris, OnDrop = OnDrop)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            TierBox('D', 0xff87ceeb)
            PlacementGrid(modifier = Modifier.weight(1f), 'D', tierUris = tierUris, OnDrop = OnDrop)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            TierBox('E', 0xffb39eb5)
            PlacementGrid(modifier = Modifier.weight(1f), 'E', tierUris = tierUris, OnDrop = OnDrop)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            TierBox('F', 0xfff984e5)
            PlacementGrid(modifier = Modifier.weight(1f), 'F', tierUris = tierUris, OnDrop = OnDrop)
        }

    }
}


@Composable
fun PlacementGrid(modifier: Modifier,
                  tier: Char,
                  tierUris: Map<Char, List<Uri?>>,
                  OnDrop: (Uri, tier: Char) -> Unit) {

    val callback = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val clipData = event
                    .toAndroidDragEvent()
                    .clipData
                val selectedUri = Uri.parse(
                    clipData
                    .getItemAt(0)
                    .text
                    ?.toString()
                )
                OnDrop(selectedUri, tier)
                return true
            }
        }
    }

    LazyHorizontalGrid(
        modifier = modifier
            .dragAndDropTarget(
                shouldStartDragAndDrop = { true },
                target = callback
            )
        .size(100.dp)
        .background(Color.Black),
        rows = GridCells.Fixed(1)

    ){
            items(tierUris[tier]?: emptyList()){ uri ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .dragAndDropSource{_ ->
                            DragAndDropTransferData(
                                ClipData.newPlainText(
                                    "Photo",uri.toString()
                                )
                            )
                        }
                ){
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

@Composable
fun TierBox(letter:Char, color:Long) { // sets up the box for each tier
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(color))
            .width(30.dp),
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
fun MainApp(){
    var imageUris by remember {
        mutableStateOf<List<Uri?>>(emptyList()) }

    var tierUris by remember {
        mutableStateOf<Map<Char, List<Uri>>>(
            mapOf(
                'S' to emptyList(),
                'A' to emptyList(),
                'B' to emptyList(),
                'C' to emptyList(),
                'D' to emptyList(),
                'E' to emptyList(),
                'F' to emptyList()
            )
        )
    }

    fun moveToTier(uri: Uri, tier: Char) {
        imageUris = imageUris - uri

        tierUris = tierUris.mapValues { (key, uris) ->

            if (key == tier && !uris.contains(uri)) {
                uris + uri

            }
            else if(key == tier && uris.contains(uri)){
                return
        }
            else {
                uris - uri
            }
        }
    }

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(10)
    ) { uris ->
        imageUris += uris
    }

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
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ) {
                LazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth(),
                    rows = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    items(imageUris){ uri ->
                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .dragAndDropSource{_ ->
                                    DragAndDropTransferData(
                                        ClipData.newPlainText(
                                            "Photo",uri.toString()
                                        )
                                    )
                                }
                        ){
                            AsyncImage(
                                model = uri,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { // onClick -> open photo picker
                photoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
                Icon(Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        innerPadding ->
        Tiers(
            modifier = Modifier.padding(innerPadding),
            tierUris = tierUris,
            OnDrop = { uri, tier ->
                moveToTier(uri, tier)
            }
        )
    }
}