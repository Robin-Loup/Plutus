package com.example.project

import android.R.attr.singleLine
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project.ui.theme.ProjectTheme
import java.time.LocalDateTime


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
                        //Greeting("Android")
                    }
                }
            }
        }
    }
}
@Composable
fun addTagButtons(){
    // Variables to create new transaction
    var list by remember { mutableStateOf(arrayListOf<Etiquette.Tag>()) }
    val context=LocalContext.current
    var tagText by remember { mutableStateOf(TextFieldValue("")) }
    var descText by remember { mutableStateOf(TextFieldValue("")) }
    var montant by remember { mutableStateOf(TextFieldValue(""))}
    val db = DBHelper(context, null)
    Row(
        Modifier
            .padding(10.dp)) {
        // Textfield to add tags
        Column(
            Modifier
        ) {
            OutlinedTextField(
                value = tagText,
                onValueChange = {
                    if(it.text.length<=20)
                        tagText = it
                    else Toast.makeText(context,"too long, max = 20 characters",Toast.LENGTH_SHORT).show()
                },
                label = { Text(text = "Tag") },
                placeholder = { Text(text = "Enter which tags to add") },
                singleLine=true,
            )
        }
        // Button to add tags
        Column(
            Modifier
                .padding(7.dp)
        ) {
            Button(
                onClick = {
                    if(Etiquette.getTag(tagText.text)!=null)
                        list.add(Etiquette.getTag(tagText.text))
                    Toast.makeText(context,list.toString(),Toast.LENGTH_SHORT).show()
                },
            ) {
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Add Tag")
            }
        }

    }
    Row(
        Modifier
            .padding(10.dp)){
        OutlinedTextField(
            value = descText,
            onValueChange = {
                if(it.text.length<20)
                    descText = it
                else Toast.makeText(context,"too long, max = 20 characters",Toast.LENGTH_SHORT).show()
            },
            label = { Text(text = "Description") },
            placeholder = { Text(text = "Enter the transaction description") },
            singleLine=true,
        )
    }
    Row(
        Modifier
            .padding(10.dp)){
        OutlinedTextField(
            value = montant,
            onValueChange = {
                if(it.text.length<9)
                    montant = it
                else Toast.makeText(context,"Way too expensive, come on",Toast.LENGTH_SHORT).show()
            },
            label = { Text(text = "Montant") },
            placeholder = { Text(text = "How much is it") },
            singleLine=true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
    Row(
        Modifier
            .padding(10.dp)){
        Button(
            onClick = {

                if(list.isEmpty()) Toast.makeText(context,"Need at least 1 tag",Toast.LENGTH_SHORT).show()
                else if(descText.text=="") Toast.makeText(context,"Description requise",Toast.LENGTH_SHORT).show()
                else if(montant.text=="") Toast.makeText(context,"Montant incorrect",Toast.LENGTH_SHORT).show()
                else {
                    var valMontant=montant.text.toString().toInt()
                    if(valMontant<0) Toast.makeText(context,"Montant incorrect",Toast.LENGTH_SHORT).show()
                    else{
                        var tr=Transaction(descText.text, valMontant,list)
                        db.addTransaction(tr)
                        list.clear()
                        tagText.text.removeRange(0,tagText.text.length)
                        descText.text.removeRange(0,tagText.text.length)
                        montant.text.removeRange(0,tagText.text.length)
                        Toast.makeText(context,tr.toString(),Toast.LENGTH_SHORT).show()
                    }

                }
            },
        ) {
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Add Transaction")
        }
    }
}
@Composable
fun MainView(){
    Column(){
        addTagButtons()

    }



}
@Composable
fun Greeting(name: String) {
    var tr = Transaction(0,0,"test",10, LocalDateTime.now(),arrayListOf<Etiquette.Tag>(Etiquette.Tag.Food))

    Text(text = "$tr")

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectTheme {

    }
}