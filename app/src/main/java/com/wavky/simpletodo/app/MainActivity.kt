package com.wavky.simpletodo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wavky.simpletodo.app.ui.MainScreen
import com.wavky.simpletodo.app.ui.theme.SimpleTodoTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      SimpleTodoTheme {
        MainScreen(Modifier.padding(16.dp))
      }
    }
  }

  @Preview(showBackground = true)
  @Composable
  private fun Preview() {
    MainScreen()
  }
}
