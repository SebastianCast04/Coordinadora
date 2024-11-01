package com.example.coordinadoraapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.example.coordinadoraapp.R
import com.example.coordinadoraapp.presentation.theme.colorScheme


@Composable
fun closeSesionDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(dismissOnBackPress = false),
            modifier = Modifier.height(220.dp),
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Cerrar sesión",
                        color = Color(0xFF1464AC),
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 10.dp),
                        fontSize = 20.sp
                    )
                }
            },
            text = {
                Text(
                    text = "Estás a punto de cerrar la aplicación. ¿Deseas salir?",
                    color = colorScheme.surface,
                    modifier = Modifier.padding(top = 6.dp),
                    textAlign = TextAlign.Center
                )
            },
            containerColor = colorScheme.primary,
            textContentColor = colorScheme.inverseSurface,
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(0.5.dp, colorScheme.onSecondary),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1464AC),
                        ),
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text(
                            text = "Aceptar",
                            color = colorScheme.primary
                        )
                    }

                    Button(
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(0.5.dp, colorScheme.onSecondary),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorScheme.primary
                        ),
                        onClick = {
                            onCancel()
                        }
                    ) {
                        Text(
                            text = "Cancelar",
                            color = colorScheme.onSecondary
                        )
                    }
                }
            },
            dismissButton = {
                // Puedes dejarlo vacío o agregar algún contenido si es necesario
            }
        )
    }
}
