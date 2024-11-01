package com.example.coordinadoraapp.presentation.screens.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.coordinadoraapp.presentation.screens.login.LoginScreen
import com.example.coordinadoraapp.presentation.theme.colorScheme
import kotlinx.coroutines.launch

class OnBoardingScreen : Screen {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {

        val navigator: Navigator? = LocalNavigator.current
        val pageCount = 5
        val pagerState = rememberPagerState(initialPage = 0) {
            pageCount
        }
        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.background(color = colorScheme.primary),
            bottomBar = {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                                val nextPage = pagerState.currentPage + 1
                                if (nextPage < pagerState.pageCount) {
                                    pagerState.animateScrollToPage(nextPage)
                                } else {
                                    navigator?.replaceAll(item = LoginScreen())
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1464AC),
                            contentColor = colorScheme.primary
                        ),
                        modifier = Modifier.width(350.dp),
                    ) {
                        Text(if (pagerState.currentPage == pageCount - 1) "Ingresar" else "Siguiente")
                    }
                }
            },
            content = {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> TabOneOnboarding().Content()
                        1 -> TabTwoOnboarding().Content()
                        2 -> TabThreeOnboarding().Content()
                        3 -> TabFourOnboarding().Content()
                        4 -> TabFiveOnboarding().Content()
                    }
                }
            }
        )
    }
}