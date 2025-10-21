package com.example.artspace


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtworkWall(painter: Painter){
    Image(
        modifier = Modifier
            .size(350.dp)
            .clip(RoundedCornerShape(30.dp)),
        painter = painter,
        contentDescription = "hero"
    )
}
@Composable
fun ArtworkDescriptor(title : Int, artworkArtist : Int){

    Column(
        modifier = Modifier
            .width(350.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color(0x51B2AB88))
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(

            text = stringResource(title),
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 50.sp,
            textAlign = TextAlign.Center,

        )
        Text(
            text = stringResource(artworkArtist),
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Black


        )
    }
}

@Composable
fun ArtSpaceLayout(){

    data class ArtSpace(val image: Int, val title: Int, val artworkArtist :Int )

    val artSpaceList = listOf(
        ArtSpace(
            R.drawable.cat,
            R.string.cat_title,
            R.string.cat_description
        ),
        ArtSpace(
            R.drawable.hero,
            R.string.hero_title,
            R.string.hero_description
        ),
        ArtSpace(
            R.drawable.graffiti,
            R.string.graffiti_title,
            R.string.graffiti_description
        ),
        ArtSpace(
            R.drawable.teeth,
            R.string.teeth_title,
            R.string.teeth_description
        ),

    )

    var currentIndex by remember { mutableIntStateOf(0) }

    val currentStage = artSpaceList[currentIndex]

    Box(
        modifier = Modifier
            // 🔹 This makes sure content does not touch the top (status bar)
            .statusBarsPadding()
            // 🔹 This makes sure content does not touch the bottom (navigation bar)
            .navigationBarsPadding()
            .background(Color(0x8F394924))
            .padding(8.dp)
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(5.dp),
                clip = false
            )
            .background(Color(0x8F394924), RoundedCornerShape(5.dp))
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            ArtworkWall(painterResource(currentStage.image))
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            ArtworkDescriptor(currentStage.title,currentStage.artworkArtist)
            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp, // 👈 shadow when normal
                        pressedElevation = 4.dp,  // 👈 shadow when pressed
                        focusedElevation = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0x51B2AB88)),
                ) {
                    Text(
                        text = "Previous",
                        fontSize = 18.sp,
                        color = Color.Black
                        )
                }
                Button(
                    onClick = { if (currentIndex < artSpaceList.size - 1) currentIndex++ },
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp, // 👈 shadow when normal
                        pressedElevation = 4.dp,  // 👈 shadow when pressed
                        focusedElevation = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0x51B2AB88)),
                ) {
                    Text(
                        text ="Next",
                        fontSize = 18.sp,
                        color = Color.Black

                    )
                }
            }
        }

    }




}

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    ArtSpaceTheme {
        ArtSpaceLayout()

    }
}