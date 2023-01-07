package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.MessageDigest
import java.util.*

@Serializable
data class CalculateImageHashRequest(
    @SerialName("base64Image") val base64Image: String
)

@Serializable
data class ImageHashResponse(
    @SerialName("imageHash") val imageHash: String
)

fun Application.configureRouting() {

    routing {
        post("/base64tohash") {
            val imageRequest = try {
                call.receive<CalculateImageHashRequest>()
            }
            catch (e: Exception){
                return@post call.respond(HttpStatusCode.BadRequest)
            }

            val bytes = MessageDigest
                .getInstance("SHA-256")
                .digest(imageRequest.base64Image.toByteArray())
            val result = bytes.toHex().uppercase(Locale.getDefault())

            val imageResponse = ImageHashResponse(imageHash = result)

            call.respond(HttpStatusCode.OK, imageResponse)
        }
        get("/hello"){
            call.respondText("Hello from ktor hash app")
        }
    }
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}