package com.wavky.simpletodo.app.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wavky.simpletodo.R
import com.wavky.simpletodo.app.MainViewModel
import com.wavky.simpletodo.app.MainViewModelFunc
import com.wavky.simpletodo.app.ui.theme.Colors
import com.wavky.simpletodo.domain.model.Todo
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = koinViewModel()) {
  val todoList by viewModel.todoList.collectAsState()

  MainScreenContent(modifier, todoList, viewModel)
}

@Composable
fun MainScreenContent(
  modifier: Modifier = Modifier,
  todoList: List<Todo>,
  viewModel: MainViewModelFunc
) {
  var duration by remember { mutableStateOf(Duration.TODAY) }
  var showCreateTodoDialog by remember { mutableStateOf(false) }
  var showModifyTodoDialog by remember { mutableStateOf<Todo?>(null) }
  var isTodoActivated by remember { mutableStateOf(true) }
  var isDoneActivated by remember { mutableStateOf(false) }
  Scaffold(floatingActionButton = {
    FloatingActionButton({
      showCreateTodoDialog = true
    }, shape = CircleShape) {
      Icon(Icons.Default.Add, contentDescription = null)
    }
  }) { innerPadding ->
    ConstraintLayout(
      modifier = modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      val (title, durationButton, content) = createRefs()
      Title(Modifier.constrainAs(title) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
      })
      DurationButton(duration, Modifier.constrainAs(durationButton) {
        top.linkTo(parent.top)
        end.linkTo(parent.end)
      }) {
        duration = it
      }
      Column(modifier = Modifier.constrainAs(content) {
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
        top.linkTo(durationButton.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
      }) {
        FilterButtonRow(
          isTodoActivated,
          isDoneActivated,
          { isTodoActivated = !isTodoActivated },
          { isDoneActivated = !isDoneActivated })
        val filteredTodoList = remember(todoList, isTodoActivated, isDoneActivated) {
          todoList.filter { todo ->
            when {
              isTodoActivated && isDoneActivated -> true
              isTodoActivated -> !todo.isDone
              isDoneActivated -> todo.isDone
              else -> false
            }
          }
        }
        TodoList(
          todoList = filteredTodoList,
          modifier = Modifier.weight(1f),
          onCheckChange = {
            viewModel.updateTodo(it)
          },
          onContentClick = {
            showModifyTodoDialog = it
          }
        )
      }
      if (showCreateTodoDialog) {
        CreateTodoDialog({ showCreateTodoDialog = false }) { todoContent ->
          viewModel.addTodo(
            todoContent,
            ""
          )
          showCreateTodoDialog = false
        }
      }
      val modifyTodo: Todo? = showModifyTodoDialog
      if (modifyTodo != null) {
        ModifyTodoDialog(modifyTodo, {
          showModifyTodoDialog = null
        }) { updatedTodo ->
          showModifyTodoDialog = null
          viewModel.updateTodo(updatedTodo)
        }
      }
    }
  }
}

private enum class Duration(@StringRes val stringId: Int) {
  TODAY(R.string.duration_today),
  TOMORROW(R.string.duration_tomorrow),
  THIS_WEEK(R.string.duration_this_week),
  THIS_MONTH(R.string.duration_this_month),
  THIS_YEAR(R.string.duration_this_year),
  ALL(R.string.duration_all)
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
  Text(
    stringResource(id = R.string.title),
    modifier = modifier,
    fontSize = 52.sp,
    fontWeight = FontWeight.Bold
  )
}

@Composable
private fun DurationButton(
  duration: Duration,
  modifier: Modifier = Modifier,
  onClickDuration: (Duration) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }
  Box(modifier = modifier) {
    Surface(
      onClick = { expanded = true }
    ) {
      Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Text(
          stringResource(id = duration.stringId),
          style = MaterialTheme.typography.bodySmall
        )
        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
      }
    }
    DropdownMenu(expanded = expanded, { expanded = false }) {
      Duration.entries.forEach { duration ->
        DropdownMenuItem(
          text = {
            Text(
              stringResource(id = duration.stringId),
              modifier = Modifier.padding(8.dp)
            )
          },
          onClick = {
            expanded = false
            onClickDuration(duration)
          }
        )
      }
    }
  }
}

@Composable
private fun FilterButtonRow(
  isTodoActive: Boolean,
  isDoneActive: Boolean,
  onClickTodo: () -> Unit,
  onClickDone: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier
      .fillMaxWidth()
      .padding(24.dp)
  ) {
    val activatedColor = Colors.FilterButtonActive
    val todoBgColor = if (isTodoActive) activatedColor else Color.Unspecified
    val doneBgColor = if (isDoneActive) activatedColor else Color.Unspecified
    val todoColors = ButtonDefaults.outlinedButtonColors(containerColor = todoBgColor)
    val doneColors = ButtonDefaults.outlinedButtonColors(containerColor = doneBgColor)
    OutlinedButton(onClickTodo, colors = todoColors) {
      Text(stringResource(id = R.string.filter_button_todo))
    }
    OutlinedButton(onClickDone, colors = doneColors) {
      Text(stringResource(id = R.string.filter_button_done))
    }
  }
}

@Composable
private fun TodoList(
  todoList: List<Todo>,
  modifier: Modifier = Modifier,
  onCheckChange: (Todo) -> Unit,
  onContentClick: (Todo) -> Unit
) {
  LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(todoList, key = { item -> item.id }) { item ->
      TodoItem(item.title, item.isDone, onContentClick = {
        onContentClick(item)
      }) { isChecked ->
        onCheckChange(item.copy(isDone = isChecked))
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainScreen() {
  MainScreenContent(
    todoList = (1..10).map {
      Todo(it.toLong(), "Todo $it", "", Random.nextBoolean(), -1, -1)
    },
    viewModel = object : MainViewModelFunc {
      override fun addTodo(title: String, description: String) {}
      override fun updateTodo(todo: Todo) {}
      override fun deleteTodo(todo: Todo) {}
    },
    modifier = Modifier.padding(16.dp)
  )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDurationButton(modifier: Modifier = Modifier) {
  var duration by remember { mutableStateOf(Duration.TODAY) }
  DurationButton(duration, modifier.padding(16.dp)) {
    duration = it
  }
}
