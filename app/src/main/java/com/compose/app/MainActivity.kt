package com.compose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.compose.app.navigation.nav_graph.root.RootNavGraph
import com.compose.app.ui.theme.ComposeApplicationTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    RootNavGraph(navController = navController)
}

/*
side effects = hooks in react
SideEffect -> To share Compose state with objects not managed by compose,
and  it's invoked on every successful recomposition. use this effect if
on successful recomposition you want to update the properties of that object.

LaunchedEffect -> used To call suspend functions safely from inside a composable.
and it will recompose when the key changed
rememberCoroutineScope -> used to launch a coroutine outside the composable,
but scoped to the composable, which mean coroutine will be automatically canceled
once it leaves the composition,
rememberUpdatedState -> when you want to save the current value of a lambda function,
and use as a reference to other effects which will not update on Recomposition. so
those effects bcz of reference will get the new value.
DisposableEffect -> when a subscription i have to dispose
produceState -> convert non-Compose state into Compose state
derivedStateOf -> convert one or multiple state objects into another state and cached it. So,
next time compose will receive the cached value until or unless the state of compose changed.
snapshotFlow -> convert Compose's State into Flows
 */
