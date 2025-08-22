package com.example.limonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.limonade.ui.theme.LimonadeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LimonadeTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
                ) { innerPadding ->
                    Column {
                        MyHeader()
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFF5F0CD))
                        ) {
                            InteractiveImage()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF578FCA))
            .height(70.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(R.string.mensagem_header),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            fontSize = 30.sp,
        )
    }
}

@Composable
fun InteractiveImage(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(1) }
    var squezing by remember { mutableStateOf((2..4).random()) }
    var visible by remember { mutableStateOf(true) }
    val imageState = when (state) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> {R.drawable.lemon_restart}
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier,
            onClick = {
                visible = !visible
                when (state) {
                    1 -> {
                        state++
                    }
                    2 -> {
                        if (squezing == 0) {
                            state++
                            squezing = (2..4).random()
                        } else {
                            squezing--
                        }
                    }

                    3 -> {
                        state++
                    }
                    else -> {
                        state = 1
                    }
                }
            }
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Image(
                modifier = modifier
                    .animateEnterExit(
                        enter = slideInHorizontally(),
                        exit = slideOutHorizontally()
                    ),
                painter = painterResource(imageState),
                contentDescription = "",
            )
            }

        }
    }


}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    LimonadeTheme {
        Scaffold(modifier = Modifier
            .fillMaxSize()
        ) { innerPadding ->
            Column {
                MyHeader()
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF5F0CD))
                ) {
                    InteractiveImage()
                }
            }

        }

    }
}