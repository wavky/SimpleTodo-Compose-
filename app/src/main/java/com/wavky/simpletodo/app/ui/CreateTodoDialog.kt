package com.wavky.simpletodo.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wavky.simpletodo.R

@Composable
fun CreateTodoDialog(
  onDismiss: () -> Unit,
  onConfirm: (content: String) -> Unit
) {
  var content by remember { mutableStateOf("") }
  AlertDialog(
    { onDismiss() },
    {
      Button(
        { onConfirm(content) },
        enabled = content.isNotEmpty()
      ) { Text(stringResource(R.string.create)) }
    },
    dismissButton = {
      Button({ onDismiss() }) { Text(stringResource(R.string.cancel)) }
    },
    title = {
      Text(
        stringResource(R.string.todo_dialog_title_add),
        Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
      )
    },
    text = {
      Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
          content,
          { content = it },
          Modifier.fillMaxWidth(),
          label = { Text(stringResource(R.string.todo_dialog_content)) }
        )
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
  CreateTodoDialog({}) {}
}
