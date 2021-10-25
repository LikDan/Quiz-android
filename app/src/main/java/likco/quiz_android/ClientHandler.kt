package likco.quiz_android

import java.io.DataInputStream
import java.io.EOFException
import java.io.IOException
import java.net.InetAddress
import java.net.Socket


class ClientHandler(host: String, port: String) : Runnable {
    init {
        socket = Socket(InetAddress.getByName(host), port.toInt())
    }

    var onResponse: (String) -> Unit = {}

    override fun run() {
        try {
            DataInputStream(socket!!.getInputStream()).use { ois ->
                var response = ""

                while (response != "quit") {
                    try {
                        response = ois.readUTF()
                        onResponse(response)
                    } catch (ex: EOFException) {
                        ex.printStackTrace()
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        var socket: Socket? = null
    }
}