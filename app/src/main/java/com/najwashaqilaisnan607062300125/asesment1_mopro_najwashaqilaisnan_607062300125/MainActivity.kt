package com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.ui.theme.Asesment1_mopro_najwashaqilaisnan_607062300125Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Asesment1_mopro_najwashaqilaisnan_607062300125Theme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var jarak by remember { mutableStateOf("") }
    var waktu by remember { mutableStateOf("") }
    var selectedWaktuSatuan by remember { mutableStateOf("jam") }

    val satuanOptions = listOf("jam", "menit")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kalkulator Kecepatan") },
                actions = {
                    // nanti bisa isi icon info di sini
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = jarak,
                onValueChange = { jarak = it },
                label = { Text("Jarak (km)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = waktu,
                onValueChange = { waktu = it },
                label = { Text("Waktu") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Text("Pilih satuan waktu:")
            satuanOptions.forEach { satuan ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedWaktuSatuan == satuan,
                        onClick = { selectedWaktuSatuan = satuan }
                    )
                    Text(text = satuan)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Asesment1_mopro_najwashaqilaisnan_607062300125Theme {
        MainScreen()
    }
}
