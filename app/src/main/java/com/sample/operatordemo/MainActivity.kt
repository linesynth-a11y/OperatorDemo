package com.sample.operatordemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sample.operatordemo.ui.theme.OperatorDemoTheme
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OperatorDemoApp()
                }
            }
        }
    }
}

@Composable
fun OperatorDemoApp() {
    val context = LocalContext.current
    //conditions
    var input by remember { mutableStateOf("")}
    var result by remember { mutableStateOf("")}
    var isFinished by remember {mutableStateOf(false) }
    //creation
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "введите символ:",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = input,
            onValueChange = {
                if (it.length <= 1) input = it
            },
            enabled = !isFinished,
            label = { Text("например: а") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val ch = input.firstOrNull()
                if (ch == null) {
                    result = "введите символ!"
                    return@Button
                }

                when {
                    !(ch in 'a'..'z') -> {
                        result = "ошибка: введена не латинская строчная буква.\nпрограмма завершена."
                        Toast.makeText(context, "программа завершена", Toast.LENGTH_SHORT).show()
                        isFinished = true
                    }

                    ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' -> {
                        result = "это гласные буквы"
                    }
                    else -> {
                        result = "возможно это согласные буквы"
                    }
                }
            },
            enabled = !isFinished,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("проверить")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = result,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (isFinished) {
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(onClick = {
                input = ""
                result = ""
                isFinished = false
            }) {
                Text("Запустить снова")
            }
        }
    }
}