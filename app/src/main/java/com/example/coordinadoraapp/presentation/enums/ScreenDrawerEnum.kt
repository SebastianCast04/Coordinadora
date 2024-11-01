package com.example.coordinadoraapp.presentation.enums

import com.example.coordinadoraapp.R

enum class ScreensDrawerEnum (val order: Int, val title: String, val image: Int) {

    Main(
        order = 0,
        title = "Menú principal",
        image = R.drawable.casita),

    Track(
        order = 1,
        title = "Consultar guia",
        image = R.drawable.cotiza),
    AirTransport(
        order = 2,
        title = "Transporte aereo",
        image = R.drawable.transporte_aereo),
    EspecialServices(
        order = 3,
        title = "Servicios especiales",
        image = R.drawable.servicios_especiales),
    SignOff(
        order = 4,
        title = "Cerra sesión",
        image = R.drawable.cerrar
    )
}