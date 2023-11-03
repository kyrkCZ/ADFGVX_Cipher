package com.example.adfgvx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adfgvx.ui.theme.ADFGVXTheme


fun encryptADFGX(text: String): String {
    // Your ADFGX encryption code goes here
    // Replace this with your actual encryption logic
    return text
}

fun encryptADFGVX(text: String): String {
    // Your ADFGX encryption code goes here
    // Replace this with your actual encryption logic
    return text
}


fun generateAlphabetMatrixWithIndexes5x5(): Array<Array<Pair<Char, Char>>> {
    // Inicializujeme abecedu
    val alphabet = "ABCDEFGHIKLMNOPQRSTUVXYZ"

    // Inicializujeme matici 5x5
    val matrix = Array(5) { Array(5) { Pair(' ', ' ') } }

    // Inicializujeme indexy pro řádky a sloupce
    val rowIndexes = "ADFGX"
    val colIndexes = "ADFGX"

    // Inicializujeme index pro abecedu
    var alphabetIndex = 0

    // Projdeme matici a naplníme ji písmeny z abecedy a indexy
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            // Pokud jsme narazili na 'J' nebo 'W', nahradíme je 'I' resp. 'V'
            val currentChar = alphabet[alphabetIndex]
            if (currentChar == 'J') {
                matrix[i][j] = Pair('I', rowIndexes[i])
            } else if (currentChar == 'W') {
                matrix[i][j] = Pair('V', colIndexes[j])
            } else {
                matrix[i][j] = Pair(currentChar, rowIndexes[i])
            }

            // Posuneme se na další písmeno v abecedě
            alphabetIndex++
        }
    }
    return matrix
}

fun generateAlphabetMatrixWithIndexes6x6(): Array<Array<Pair<Char, Char>>> {
    // Inicializujeme abecedu
    val alphabet = "ABCDEFGHIKLMNOPQRSTUVXYZ0123456789"

    // Inicializujeme matici 5x5
    val matrix = Array(6) { Array(6) { Pair(' ', ' ') } }

    // Inicializujeme indexy pro řádky a sloupce
    val rowIndexes = "ADFGX"
    val colIndexes = "ADFGX"

    // Inicializujeme index pro abecedu
    var alphabetIndex = 0

    // Projdeme matici a naplníme ji písmeny z abecedy a indexy
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            // Pokud jsme narazili na 'J' nebo 'W', nahradíme je 'I' resp. 'V'
            val currentChar = alphabet[alphabetIndex]
            if (currentChar == 'J') {
                matrix[i][j] = Pair('I', rowIndexes[i])
            } else if (currentChar == 'W') {
                matrix[i][j] = Pair('V', colIndexes[j])
            } else {
                matrix[i][j] = Pair(currentChar, rowIndexes[i])
            }

            // Posuneme se na další písmeno v abecedě
            alphabetIndex++
        }
    }
    return matrix
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ADFGVXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationBar(onCipherSelected = { cipher ->
                        when (cipher) {
                            "ADFGX" -> {
                                ADFGXCipher(
                                    alphabetMatrix = generateAlphabetMatrixWithIndexes5x5()
                                )
                            }
                            "ADFG(V)X" -> {
                                ADFGVXCipher(
                                    alphabetMatrix = generateAlphabetMatrixWithIndexes6x6()
                                )
                            }
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun NavigationBar(onCipherSelected: (String) -> Unit) {
    var activeCipher by remember { mutableStateOf("ADFGX") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(androidx.compose.ui.graphics.Color.Gray)
    ) {
        NavigationButton("ADFGX", activeCipher == "ADFGX") {
            activeCipher = "ADFGX"
            onCipherSelected(activeCipher)
        }

        NavigationButton("ADFG(V)X", activeCipher == "ADFG(V)X") {
            activeCipher = "ADFG(V)X"
            onCipherSelected(activeCipher)
        }
    }
}

@Composable
fun NavigationButton(text: String, isActive: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        content = { Text(text) },
        colors = ButtonDefaults.buttonColors(
            contentColor = if (isActive) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color.Black,
        )
    )
}

@Composable
fun ADFGXCipher(alphabetMatrix: Array<Array<Pair<Char, Char>>>) {
    var inputText by remember { mutableStateOf("") }
    var encryptedText by remember { mutableStateOf("") }

    Column {
        BasicTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                // Implement the encryption logic here
                encryptedText = encryptADFGX(it)
            },
            singleLine = true,
            modifier = Modifier.padding(16.dp)
        )

        BasicTextField(
            value = encryptedText,
            onValueChange = {},
            singleLine = true,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ADFGVXCipher(alphabetMatrix: Array<Array<Pair<Char, Char>>>) {
    var inputText by remember { mutableStateOf("") }
    var encryptedText by remember { mutableStateOf("") }

    Column {
        BasicTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                // Implement the encryption logic here
                encryptedText = encryptADFGVX(it)
            },
            singleLine = true,
            modifier = Modifier.padding(16.dp)
        )

        BasicTextField(
            value = encryptedText,
            onValueChange = {},
            singleLine = true,
            modifier = Modifier.padding(16.dp)
        )
    }
}