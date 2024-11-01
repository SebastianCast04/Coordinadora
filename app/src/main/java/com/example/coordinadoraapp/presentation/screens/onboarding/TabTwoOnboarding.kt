package com.example.coordinadoraapp.presentation.screens.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.coordinadoraapp.R
import com.example.coordinadoraapp.presentation.screens.login.LoginScreen
import com.example.coordinadoraapp.presentation.theme.colorScheme

class TabTwoOnboarding : Tab {

    override val options: TabOptions
        @Composable get() {
            return remember {
                TabOptions(
                    index = 0u, title = "", icon = null
                )
            }
        }

    @Composable
    override fun Content() {
        onBoardingTabTwoContent()
    }

}

@Composable
fun onBoardingTabTwoContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.primary)
    ) {
        val navigator: Navigator? = LocalNavigator.current

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.transporte_aereo),
                contentDescription = "logo",
                modifier = Modifier.size(360.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Transporte aéreo de mercancía",
                fontSize = 20.sp,
                color = Color(0xFF1464AC)
            )
            Text(
                text = "Suele caracterizarse por ser una de las opciones con mayor valor agregado, más rápida y segura, ofreciendo la posibilidad de hacer envíos masivos a otras ciudades en el mismo territorio o sobrepasando fronteras.",
                fontSize = 16.sp,
                color = colorScheme.surface,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(60.dp))
            ProgressCirclesTabTwo(activeIndex = 1)

        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    navigator?.replaceAll(item = LoginScreen())
                }
        ) {
            Text(
                text = "Saltar",
                fontSize = 20.sp,
                color = colorScheme.surface,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Flecha",
                tint = colorScheme.surface,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ProgressCirclesTabTwo(activeIndex: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(5) { index ->
            Canvas(modifier = Modifier.size(14.dp)) {
                val color =
                    if (index == activeIndex) Color(0xFF1464AC) else colorScheme.surfaceVariant
                drawCircle(color = color)
            }
        }
    }
}