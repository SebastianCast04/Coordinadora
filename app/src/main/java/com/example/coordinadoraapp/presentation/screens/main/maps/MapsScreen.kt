package com.example.coordinadoraapp.presentation.screens.main.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.coordinadoraapp.data.models.response.PositionDTO
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MapsScreen(
    val positions: List<PositionDTO>,
    val onMarkerClick: (PositionDTO) -> Unit
): Screen {
    @Composable
    override fun Content() {
        MapsScreenContent(
            positions = positions,
            onMarkerClick = onMarkerClick
        )
    }


}

@Composable
fun MapsScreenContent(positions: List<PositionDTO>, onMarkerClick: (PositionDTO) -> Unit){

    val cameraPositionState = rememberCameraPositionState {
        positions.firstOrNull()?.let {
            position = CameraPosition.fromLatLngZoom(LatLng(it.latitud, it.longitud), 10f)
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        positions.forEach { position ->
            Marker(
                state = MarkerState(position = LatLng(position.latitud, position.longitud)),
                title = "Posición",
                onClick = {
                    onMarkerClick(position)
                    true
                }
            )
        }
    }

}