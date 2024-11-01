package com.example.coordinadoraapp.data.remote

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.example.coordinadoraapp.data.models.response.PositionDTO
import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO
import org.json.JSONException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MenuServiceImplementation @Inject constructor(
    private val requestQueue: RequestQueue
) : MenuService {
    override suspend fun getImage(): ResponseGetPdfDTO {
        return suspendCoroutine { continuation ->
            val url = "https://noderedtest.coordinadora.com/api/v1/obtenerimagen/"
            val request = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    try {
                        val isError = response.getBoolean("isError")
                        val base64 = response.getString("base64")
                        val posicionesJson = response.getJSONArray("posiciones")
                        val posiciones = mutableListOf<PositionDTO>()

                        for (i in 0 until posicionesJson.length()) {
                            val posicionJson = posicionesJson.getJSONObject(i)
                            val latitud = posicionJson.getDouble("latitud")
                            val longitud = posicionJson.getDouble("longitud")
                            posiciones.add(PositionDTO(latitud, longitud))
                        }

                        continuation.resume(ResponseGetPdfDTO(isError, base64, posiciones))
                    } catch (e: JSONException) {
                        continuation.resumeWithException(e)
                    }
                },
                { error ->
                    continuation.resumeWithException(error)
                }
            )
            requestQueue.add(request)
        }
    }
}