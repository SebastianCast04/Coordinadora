package com.example.coordinadoraapp.presentation.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.coordinadoraapp.R
import com.example.coordinadoraapp.domain.util.ShowPDF
import com.example.coordinadoraapp.domain.util.savePDF
import com.example.coordinadoraapp.presentation.components.ButtonComponentLogin
import com.example.coordinadoraapp.presentation.components.ButtonTypeLogin
import com.example.coordinadoraapp.presentation.components.MyCustomDialog
import com.example.coordinadoraapp.presentation.components.NavigationDrawerContent
import com.example.coordinadoraapp.presentation.enums.ScreensDrawerEnum
import com.example.coordinadoraapp.presentation.screens.main.maps.MapsScreen
import com.example.coordinadoraapp.presentation.theme.colorScheme

class MainScreen: Screen {
    @Composable
    override fun Content() {
        val navigator: Navigator? = LocalNavigator.current
        val mainScreenViewModel = getViewModel<MainScreenViewModel>()

        NavigationDrawerContent(
            navigator = navigator,
            page = ScreensDrawerEnum.Main,
            isShowTopBar = false,
            contentNavigation = {
                MainScreenContent(
                    mainScreenViewModel
                )
            }
        )
    }
}

@Composable
fun MainScreenContent(
    viewModel: MainScreenViewModel
){

    LaunchedEffect(Unit) {
        viewModel.getImages()
    }

    val navigator: Navigator? = LocalNavigator.current
    val state by viewModel.stateFlowImagen.collectAsState()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var pdfPath by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val gradientColors = listOf(
            Color(0xFF1464AC),
            Color(0xFF1464AC),
            Color(0xFFFFFFFF)
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp)
                    .offset(y = (-40).dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
                shadowElevation = 8.dp
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = gradientColors,
                            startY = 0f,
                            endY = size.height
                        )
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.transporte_terrestre),
                    contentDescription = null,
                    modifier = Modifier.height(70.dp)
                        .clickable{
                        },
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 460.dp)
                    .background(Color.White)
                    .fillMaxHeight()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 54.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.fillMaxWidth().height(410.dp))


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                        .offset(y = (-60).dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Transparent)
                            .padding(bottom = 40.dp)
                    ) { }
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                        .offset(y = (-60).dp),
                ) {
                    Text(
                        text = "¿Qué necesitas hacer?",
                        color = Color(0xFF1464AC),
                        fontWeight = FontWeight(500),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Divider(
                        color = colorScheme.inverseSurface,
                        thickness = 0.8.dp,
                        modifier = Modifier.padding(start = 50.dp, end = 50.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ButtonComponentLogin(
                            onClick = {
                                state.data?.let { response ->
                                    println("SOY EL RESPONSE DESDE EL MAIN SCREEN $response")
                                    savePDF(response.base64, context)
                                     pdfPath = savePDF(response.base64, context)
                                    showDialog = true
                                }
                            },
                            text = "Obtener archivos",
                            backgroundColor = colorScheme.primary,
                            borderStroke = BorderStroke(0.5.dp, colorScheme.inverseSurface),
                            textColor = Color(0xFF1464AC),
                            setFontWeight = FontWeight(600),
                            squareShape = MaterialTheme.shapes.small,
                            buttonType = ButtonTypeLogin.CircularButton,
                            fontSize = 14.sp,
                            buttonStyle = { modifier ->
                                modifier.padding(0.dp)
                            },
                        )

                        ButtonComponentLogin(
                            onClick = {
                                state.data?.let { response ->
                                    navigator?.push(MapsScreen(
                                        positions = response.posiciones,
                                        onMarkerClick = { position ->
                                            showDialog = true
                                            pdfPath = savePDF(response.base64, context)
                                        }
                                    ))
                                }
                            },
                            text = "Consultar maps",
                            backgroundColor = colorScheme.primary,
                            setFontWeight = FontWeight(600),
                            textColor = Color(0xFF1464AC),
                            borderStroke = BorderStroke(0.5.dp, colorScheme.inverseSurface),
                            squareShape = MaterialTheme.shapes.small,
                            buttonType = ButtonTypeLogin.CircularButton,
                            fontSize = 14.sp,
                            buttonStyle = { modifier ->
                                modifier.align(Alignment.CenterHorizontally)
                            },
                        )


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Actualizar datos más recientes ->",
                                color = colorScheme.inverseSurface,
                                fontWeight = FontWeight(500),
                                fontSize = 16.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Actualizar",
                                tint = Color(0xFF1464AC),
                                modifier = Modifier.size(24.dp).clickable {
                                    viewModel.getImages()
                                }
                            )
                        }

                        if (showDialog) {
                            MyCustomDialog(
                                onDismissRequest = { showDialog = false },
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp, end = 16.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "Vista Previa del PDF",
                                            color = Color(0xFF1464AC),
                                            modifier = Modifier.padding(start = 16.dp)
                                        )

                                        IconButton(
                                            onClick = { showDialog = false },
                                            modifier = Modifier.size(24.dp).offset(x = 70.dp, y = (-12).dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Cerrar diálogo",
                                                tint = Color.Gray
                                            )
                                        }
                                    }

                                    pdfPath?.let { path ->
                                        ShowPDF(path)
                                    }
                                }
                            }
                        }


                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, start = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Conteo de ingresos restantes: ")
                            withStyle(style = SpanStyle(color = Color(0xFF1464AC), fontWeight = FontWeight.Bold, fontSize = 18.sp)) {
                                append("4")
                            }
                        },
                        color = Color.Black,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }

        var showWelcome by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = true){
            showWelcome = true
        }



        if (showWelcome) {
            AlertDialog(
                containerColor = colorScheme.primary,
                shape = RoundedCornerShape(6.dp),
                onDismissRequest = {
                    showWelcome = false
                },
                confirmButton = {
                    Button(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 60.dp),
                        border = BorderStroke(0.5.dp, colorScheme.onSecondary),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1464AC)
                        ),
                        onClick = {
                            showWelcome = false
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Continuar",
                                color = colorScheme.primary
                            )
                        }
                    }
                },
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painterResource(id = R.drawable.hola),
                            contentDescription = "Error",
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(26.dp))
                        Text(
                            text = "¡Bienvenid@ a la familia coordinadora!",
                            color = Color(0xFF1464AC),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Text(
                        text = "Nos alegra que estes de vuelta con nosotros",
                        color = colorScheme.surface,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }
}