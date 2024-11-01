package com.example.coordinadoraapp.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.coordinadoraapp.R
import com.example.coordinadoraapp.presentation.screens.login.LoginViewModel
import com.example.coordinadoraapp.presentation.screens.main.MainScreen
import com.example.coordinadoraapp.presentation.screens.onboarding.OnBoardingScreen
import kotlinx.coroutines.delay

class SplashScreen : Screen {
    @Composable
    override fun Content() {

        val splashViewModel =  getViewModel<SplashViewModel>()



        val navigator: Navigator? = LocalNavigator.current
        val count = remember { mutableStateOf(0) }

        LaunchedEffect(key1 = true) {
            delay(timeMillis = 2000)

            //Podría usar EncryptedSharedPreferences para encriptar el email y contraseña
            val email = "coordi2024"
            val password = "accesopruebas"
            val usuario = splashViewModel.checkUser(email, password)

            if (usuario != null) {
                navigator?.replace(item = MainScreen())
            } else {
                navigator?.replace(item = OnBoardingScreen())
            }
        }

        LaunchedEffect(key1 = true) {
            repeat(Int.MAX_VALUE) {
                for (i in 1..3) {
                    count.value = i
                    delay(timeMillis = 400)
                }
            }
        }

        val colorsA = listOf(
            Color(0xFF1464AC),
            Color(0xFFFFFFFF)
        )

        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0XFFc7c7c7))
            )


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 20.dp)
                    .shadow(
                        elevation = 26.dp,
                        clip = false
                    )
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                    .background(Brush.verticalGradient(colorsA))
                    .align(Alignment.TopCenter)
            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 240.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
                Image(
                    painterResource(id = R.drawable.logo_coordinadora),
                    contentDescription = "logo coordinadora",
                    modifier = Modifier
                        .width(281.dp)
                        .height(138.dp)
                        .offset(y = (-40).dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                val image = when (count.value) {
                    1 -> R.drawable.cargando1
                    2 -> R.drawable.cargando2
                    else -> R.drawable.cargando1
                }
                Image(
                    painterResource(id = image),
                    contentDescription = "logo coordinadora"
                )
                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    text = "1.1",
                    fontSize = 16.sp,
                    color = Color(0xFF1464AC),
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}