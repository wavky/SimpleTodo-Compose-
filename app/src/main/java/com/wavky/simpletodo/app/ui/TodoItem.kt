package com.wavky.simpletodo.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wavky.simpletodo.app.ui.theme.Colors

@Composable
fun TodoItem(
  title: String,
  isCompleted: Boolean,
  modifier: Modifier = Modifier,
  onContentClick: () -> Unit,
  onCheckedChange: (Boolean) -> Unit
) {
  Box(modifier.fillMaxWidth()) {
    val checkedColor = remember { Colors.CompletedColor }
    Row(
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Colors.TodoItemBackground)
        .clickable { onContentClick() }
        .padding(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      val checkboxColors = CheckboxDefaults.colors(checkedColor = checkedColor)
      Checkbox(checked = isCompleted, onCheckedChange = { isChecked ->
        onCheckedChange(isChecked)
      }, colors = checkboxColors)
      Text(
        title,
        modifier = Modifier.weight(1f),
        textAlign = TextAlign.Start,
        color = if (isCompleted) checkedColor else Color.Black
      )
    }
    if (isCompleted) {
      HorizontalDivider(
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
          .align(Alignment.Center),
        thickness = 4.dp, color = checkedColor
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun preview() {
  Column {
    TodoItem(title = "买手机", isCompleted = false, onContentClick = {}) {}
    TodoItem(title = "换电脑、更新系统", isCompleted = true, onContentClick = {}) {}
    TodoItem(title = "煮饭炒菜洗衣服遛狗\n拖地洗澡侍寝", isCompleted = false, onContentClick = {}) {}
  }
}
