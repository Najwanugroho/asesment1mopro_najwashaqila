package com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.ui.theme.Asesment1_mopro_najwashaqilaisnan_607062300125Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = Color(0xFF6A1B9A)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.tentang_aplikasi),
                        color = Color(0xFF6A1B9A),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFE1BEE7),
                    titleContentColor = Color(0xFF6A1B9A),
                )
            )
        }
    ) { innerPadding ->
        Text(
            text = stringResource(R.string.copyright),
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF4A148C),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    Asesment1_mopro_najwashaqilaisnan_607062300125Theme {
        AboutScreen(rememberNavController())
    }
}
