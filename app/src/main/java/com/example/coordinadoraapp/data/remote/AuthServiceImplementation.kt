package com.example.coordinadoraapp.data.remote

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO
import com.example.coordinadoraapp.domain.util.Constants.BASE_URL
import com.example.coordinadoraapp.domain.util.Constants.LOGIN
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthServiceImplementation @Inject constructor(
    private val requestQueue: RequestQueue
): AuthService {

    override suspend fun loginUser(userLoginDTO: RequestUserLoginDTO): ResponseLoginUserDTO {

        return suspendCoroutine { continuation ->
            val url = "$BASE_URL/$LOGIN"

            // Convertimos el DTO a JSON
            val jsonObject = JSONObject().apply {
                put("usuario", userLoginDTO.usuario)
                put("password", userLoginDTO.password)
            }

            // Hacemos la petición POST
            val request = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                { response ->
                    try {
                        // Parseamos la respuesta
                        val responseData = response.getString("data")
                        val validationPeriod = response.getInt("perido_validacion")
                        continuation.resume(
                            ResponseLoginUserDTO(
                                isError = false,
                                data = responseData,
                                perido_validacion = validationPeriod
                            )
                        )
                        println("SOY LOS DATOS $responseData")
                    } catch (e: JSONException) {
                        println("SOY EL ERROR $e")
                        continuation.resumeWithException(e)
                    }
                },
                { error ->
                    continuation.resumeWithException(error)
                }
            )

            // Añadimos la solicitud a la cola de Volley
            requestQueue.add(request)
        }
    }
}