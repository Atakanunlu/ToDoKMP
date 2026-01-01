package org.atakanunlu

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.atakanunlu.data.MongoDB
import org.atakanunlu.presentation.screen.home.HomeScreen
import org.atakanunlu.presentation.screen.home.HomeViewModel
import org.atakanunlu.presentation.screen.task.TaskViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Composable
@Preview
fun App() {
    initializeKoin()

    MaterialTheme {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}

val mongoModule = module {
    single { MongoDB() }
    factory { HomeViewModel(get()) }
    factory { TaskViewModel(get()) }
}

fun initializeKoin() {
    startKoin {
        modules(mongoModule)
    }
}