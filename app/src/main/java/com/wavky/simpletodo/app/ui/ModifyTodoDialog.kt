package com.wavky.simpletodo.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wavky.simpletodo.R
import com.wavky.simpletodo.domain.model.Todo

@Composable
fun ModifyTodoDialog(
  todo: Todo,
  onDismiss: () -> Unit,
  onConfirm: (todo: Todo) -> Unit
) {
  var content by remember { mutableStateOf(todo.title) }
  var isDone by remember { mutableStateOf(todo.isDone) }
  AlertDialog(
    { onDismiss() },
    {
      Button(
        {
          onConfirm(
            todo.copy(
              title = content,
              isDone = isDone,
              doneAt = if (isDone) System.currentTimeMillis() else -1L
            )
          )
        },
        enabled = todo.title.isNotEmpty()
      ) { Text(stringResource(R.string.save)) }
    },
    dismissButton = {
      Button({ onDismiss() }) { Text(stringResource(R.string.cancel)) }
    },
    title = {
      Text(
        stringResource(R.string.todo_dialog_title_edit),
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
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(stringResource(R.string.todo_dialog_done))
          Checkbox(
            checked = isDone,
            onCheckedChange = { isDone = it },
          )
        }
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun preview() {
  ModifyTodoDialog(
    todo = Todo(
      0,
      "Content",
      "Description",
      false,
      System.currentTimeMillis(),
      -1L
    ),
    {})
  {}
}
