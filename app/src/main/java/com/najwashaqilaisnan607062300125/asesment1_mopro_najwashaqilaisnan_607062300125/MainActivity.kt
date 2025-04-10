package com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.navigation.SetupNavGraph
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.ui.theme.Asesment1_mopro_najwashaqilaisnan_607062300125Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Asesment1_mopro_najwashaqilaisnan_607062300125Theme {
                SetupNavGraph()
            }
        }
    }
}
