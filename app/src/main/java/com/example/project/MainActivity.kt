package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                MainView()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView()
                    Row() {
                        Greeting("Android")
                    }
                }
            }
        }
    }
}
@Composable
fun addTagButtons(){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    return OutlinedTextField(
        value = text,
        onValueChange = {
            if(it.text.length<20)
                text = it
        },
        label = { Text(text = "Tag") },
        placeholder = { Text(text = "Enter which tags to add") },
    )
}
@Composable
fun MainView(){

    Column(){
        Row(Modifier.weight(1/6f, fill = true)) {

            Button(
                onClick = { /* ... */ },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp,

                    )
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize)
                        .height(50.dp),
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Add TR")
            }
        }
        Row(Modifier.weight(5/6f, fill = true).padding(10.dp)) {
            addTagButtons()
        }
    }



}
@Composable
fun Greeting(name: String) {
    var tr=Transaction("test",15,Etiquette.Tag.Food)

    Text(text = "$tr")

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectTheme {

    }
}