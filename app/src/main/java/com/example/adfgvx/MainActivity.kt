package com.example.adfgvx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
    // Initialize the alphabet
    val alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"

    // Initialize the 5x5 matrix
    val matrix = Array(5) { Array(5) { Pair(' ', ' ') } }

    // Initialize the row and column indexes
    val rowIndexes = "ADFGX"
    val colIndexes = "ADFGX"

    // Initialize the index for the alphabet
    var alphabetIndex = 0

    // Loop through the matrix and fill it with letters from the alphabet and indexes
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            // If 'J' is encountered, replace it with 'I'
            val currentChar = alphabet[alphabetIndex]
            if (currentChar == 'J') {
                matrix[i][j] = Pair('I', rowIndexes[i])
            } else {
                matrix[i][j] = Pair(currentChar, colIndexes[j])
            }

            // Move to the next letter in the alphabet
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFEDEDED)
                ) {
                    Activity()
                }
            }
        }
    }
}
@Preview
@Composable
fun Activity() {
    var alphabetMatrix by remember {
        mutableStateOf(generateAlphabetMatrixWithIndexes5x5())
    }
    var activeCipher by remember { mutableStateOf("ADFGX") }

    Column {
        NavigationBar(onCipherSelected = { cipher ->
            when (cipher) {
                "ADFGX" -> {
                    alphabetMatrix = generateAlphabetMatrixWithIndexes5x5()
                }
                "ADFG(V)X" -> {
                    alphabetMatrix = generateAlphabetMatrixWithIndexes6x6()
                }
            }
            activeCipher = cipher
        })

        when (activeCipher) {
            "ADFGX" -> ADFGXCipher(alphabetMatrix)
            "ADFG(V)X" -> ADFGVXCipher(alphabetMatrix)
        }
    }
}

@Composable
fun NavigationBar(onCipherSelected: (String) -> Unit) {
    var activeCipher by remember { mutableStateOf("ADFGX") }

    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .background(color = Color.Black, MaterialTheme.shapes.medium),
        horizontalArrangement = Arrangement.SpaceBetween // Adjusted here
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
            contentColor = if (isActive) androidx.compose.ui.graphics.Color.Black else androidx.compose.ui.graphics.Color.White,
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

@Composable
fun MatrixUI(matrix: Array<Array<Pair<Char, Char>>>) {
    LazyColumn {
        items(matrix.size) { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                matrix[row].forEach { cell ->
                    MatrixCell(cell.first)
                }
            }
        }
    }
}

@Composable
fun MatrixCell(char: Char) {
    Surface(
        modifier = Modifier.size(40.dp),
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = char.toString(), fontWeight = FontWeight.Bold)
        }
    }
}
