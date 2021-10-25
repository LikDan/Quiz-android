package likco.quiz_android

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp
import likco.quiz_android.ui.theme.QuizandroidTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizandroidTheme {
                Surface(color = MaterialTheme.colors.background) {
                    var ip by remember { mutableStateOf("192.168.100.47") }
                    var port by remember { mutableStateOf("4658") }
                    var text by remember { mutableStateOf("There log will be :)") }
                    var lastResponse by remember { mutableStateOf("") }

                    var isConnected by remember { mutableStateOf(true) }

                    val verticalScrollState = rememberScrollState(0)

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("IPv4: ")
                            TextField(modifier = Modifier.size(Dp(175F), Dp(60F)), value = ip, onValueChange = { ip = it })
                            Spacer(modifier = Modifier.size(Dp(30F), Dp(0F)))
                            Text("Port: ")
                            TextField(
                                modifier = Modifier.size(Dp(75F), Dp(60F)),
                                value = port,
                                onValueChange = { port = it })
                        }
                        Button(enabled = isConnected, onClick = {
                            GlobalScope.launch(Dispatchers.IO) {
                                val clientHandler = ClientHandler(ip, port)
                                isConnected = false
<<<<<<< HEAD

                                clientHandler.run()
=======
                                val exec = Executors.newFixedThreadPool(1)

                                exec.execute(clientHandler)
>>>>>>> cfb92bd (Initial commit)

                                clientHandler.onResponse = {
                                    lastResponse = it
                                    text += "\n$it"
                                }
<<<<<<< HEAD
=======

                                exec.shutdown()
>>>>>>> cfb92bd (Initial commit)
                            }
                        }) {
                            Text("Connect")
                        }

<<<<<<< HEAD
                        Box(Modifier.size(Dp(500F)).fillMaxWidth()) {
=======
                        Box(Modifier.size(Dp(450F)).fillMaxWidth()) {
>>>>>>> cfb92bd (Initial commit)
                            Column(Modifier.verticalScroll(verticalScrollState).fillMaxSize()) {
                                Text(text)
                            }
                        }

<<<<<<< HEAD


                        if (lastResponse.contains("Picture")) {
                            val bitmap = assetsToBitmap(lastResponse.substringAfter(": "))
=======
                        if (lastResponse.contains("Picture")) {
                            val bitmap = assetsToBitmap(lastResponse.substringAfterLast(": "))
>>>>>>> cfb92bd (Initial commit)
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "",
                            )
                        }
                    }
                }
            }
        }
    }
    private fun assetsToBitmap(fileName: String): Bitmap =
        with(assets.open(fileName)){
            BitmapFactory.decodeStream(this)
        }
}