package com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.R
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.navigation.Screen
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFE1BEE7),
                    titleContentColor = Color(0xFF6A1B9A),
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = Color(0xFF6A1B9A)
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val satuanJam = stringResource(R.string.satuan_jam)
    val satuanMenit = stringResource(R.string.satuan_menit)
    val satuanOptions = listOf(satuanJam, satuanMenit)

    var jarak by rememberSaveable { mutableStateOf("") }
    var jarakError by rememberSaveable { mutableStateOf(false) }

    var waktu by rememberSaveable { mutableStateOf("") }
    var waktuError by rememberSaveable { mutableStateOf(false) }

    var selectedWaktuSatuan by rememberSaveable { mutableStateOf(satuanJam) }
    var kecepatan by rememberSaveable { mutableStateOf(0f) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ⬇️ Logo di bagian atas
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.logo_content_desc),
            modifier = Modifier
                .size(150.dp)
                .padding(top = 8.dp)
        )

        Text(text = stringResource(R.string.speed_intro), style = MaterialTheme.typography.bodyLarge)

        OutlinedTextField(
            value = jarak,
            onValueChange = { jarak = it },
            label = { Text(stringResource(R.string.jarak)) },
            isError = jarakError,
            trailingIcon = { IconPicker(jarakError, "km") },
            supportingText = { ErrorHint(jarakError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = waktu,
            onValueChange = { waktu = it },
            label = { Text(stringResource(R.string.waktu)) },
            isError = waktuError,
            trailingIcon = { IconPicker(waktuError, selectedWaktuSatuan) },
            supportingText = { ErrorHint(waktuError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.pilih_satuan),
                style = MaterialTheme.typography.bodyLarge
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF3E5F5)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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

        Button(
            onClick = {
                jarakError = (jarak.isEmpty() || jarak == "0")
                waktuError = (waktu.isEmpty() || waktu == "0")
                if (jarakError || waktuError) return@Button

                val jarakNum = jarak.toFloatOrNull()
                val waktuNum = waktu.toFloatOrNull()
                if (jarakNum != null && waktuNum != null && waktuNum > 0) {
                    val waktuDalamJam = if (selectedWaktuSatuan == satuanMenit) waktuNum / 60 else waktuNum
                    kecepatan = (jarakNum / waktuDalamJam * 100).roundToInt() / 100f
                }
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8BBD0),
                contentColor = Color(0xFF880E4F)
            )
        ) {
            Text(text = stringResource(R.string.hitung))
        }

        if (kecepatan > 0f) {
            Text(
                text = stringResource(R.string.kecepatan_x, kecepatan),
                style = MaterialTheme.typography.titleLarge
            )

            Button(
                onClick = {
                    val msg = context.getString(R.string.bagikan_template, jarak, waktu, kecepatan)
                    shareData(context, msg)
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8BBD0),
                    contentColor = Color(0xFF880E4F)
                )
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid), color = Color.Red)
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(rememberNavController())
}
