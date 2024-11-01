package com.example.coordinadoraapp.presentation.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.coordinadoraapp.R
import com.example.coordinadoraapp.presentation.components.ButtonComponent
import com.example.coordinadoraapp.presentation.components.ButtonComponentLogin
import com.example.coordinadoraapp.presentation.components.ButtonType
import com.example.coordinadoraapp.presentation.components.ButtonTypeLogin
import com.example.coordinadoraapp.presentation.components.SimpleTextInputComponent
import com.example.coordinadoraapp.presentation.theme.colorScheme
import com.example.coordinadoraapp.presentation.theme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO
import com.example.coordinadoraapp.presentation.screens.main.MainScreen
import com.example.coordinadoraapp.presentation.states.ResponseState
import kotlinx.coroutines.delay

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val loginViewModel = getViewModel<LoginViewModel>()
        val loginState by loginViewModel.stateFlowLogin.collectAsState()
        val navigator: Navigator? = LocalNavigator.current

        LaunchedEffect(loginState) {
            if (loginState.data != null) {
                navigator?.replace(item = MainScreen())
            }
        }

        LoginScreenContent(
            loginState = loginState,
            onLoginClicked = { email, password ->
                loginViewModel.loginUser(email, password)
            }
        )
    }

}

@Composable
fun LoginScreenContent(
    onLoginClicked: (String, String) -> Unit,
    loginState: ResponseState<ResponseLoginUserDTO>
) {
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }
    var invalidCredentials by remember { mutableStateOf(value = false) }
    var showAlertError by remember { mutableStateOf(false) }

    LaunchedEffect(loginState) {
        if (loginState.error != null) {
            showAlertError = true
            email = ""
            password = ""
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp),
            color = Color.White,
            shadowElevation = 70.dp,
            tonalElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFF1464AC)),
                            start = Offset(0f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY)
                        )
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_coordinadorados),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Iniciar sesión",
            color = Color(0xFF1464AC),
            fontSize = 24.sp,
            fontWeight = FontWeight(600)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp)
                .offset(y = 14.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Usuario",
                color = colorScheme.surface,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 26.dp)
                .offset(y = (-6).dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.usuario_icon),
                contentDescription = "Mail Icon",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .offset(y = (32).dp)
            )
            Spacer(modifier = Modifier.width(2.dp))

            SimpleTextInputComponent(
                value = email,
                onValueChange = { email = it },
                placeHolder = "Usuario",
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp)
                .offset(y = 14.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Contraseña",
                color = colorScheme.surface,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 26.dp)
                .offset(y = (-6).dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.llave_contra),
                contentDescription = "Password Icon",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .offset(y = (32).dp)
            )

            SimpleTextInputComponent(
                value = password,
                onValueChange = { password = it },
                placeHolder = "Contraseña",
                isPassword = true,
                iconColor = colorScheme.inverseSurface,
                icon = Icons.Filled.AccountCircle
            )
        }



        Spacer(modifier = Modifier.height(54.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            ButtonComponent(
                onClick = {
                },
                text = "¿Olvidaste tu contraseña?",
                textColor = Color(0xFF1464AC),
                buttonType = ButtonType.TextButton,
                fontSize = 15.sp,
                horizontalArrangement = Arrangement.End,
                buttonStyle = { modifier ->
                    modifier
                        .fillMaxWidth()
                        .padding(start = 184.dp)
                },
                setFontWeight = FontWeight(500)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            ButtonComponentLogin(
                onClick = {
                    onLoginClicked(email, password)
                },
                text = "Login",
                backgroundColor = Color(0xFF1464AC),
                textColor = colorScheme.primary,
                buttonType = ButtonTypeLogin.CircularButton,
                disabled = email.isEmpty() || password.isEmpty(),
                horizontalArrangement = Arrangement.Center,
                buttonStyle = { modifier ->
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                },
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "¿No tienes una cuenta?",
                style = typography().bodyMedium,
                color = colorScheme.surface,
                fontSize = 16.sp
            )
            ButtonComponent(
                onClick = {
                },
                text = "Registrate",
                textColor = Color(0xFF1464AC),
                buttonType = ButtonType.TextButton,
                fontSize = 20.sp,
                setFontWeight = FontWeight(580)
            )
        }
        Text(
            text = "1.0",
            color = Color(0xFF1464AC),
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(
                PaddingValues(top = 4.dp)
            ),
            fontSize = 16.sp
        )

        if (showAlertError) {
            AlertDialog(
                containerColor = colorScheme.primary,
                shape = RoundedCornerShape(6.dp),
                onDismissRequest = {
                    showAlertError = false
                },
                confirmButton = {
                    Button(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 60.dp),
                        border = BorderStroke(0.5.dp, colorScheme.onSecondary),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =  Color(0xFF1464AC)
                        ),
                        onClick = {
                            showAlertError = false
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Aceptar",
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
                            painterResource(id = R.drawable.no_informationn_new2),
                            contentDescription = "Error",
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "¡Lo sentimos!",
                            color = Color(0XFFFFAB00),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Text(
                        text = "${loginState.error}",
                        color = colorScheme.surface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }

}