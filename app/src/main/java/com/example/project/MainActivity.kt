package com.example.project

import TransactionViewModel
import TransactionViewModelFactory
import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.Etiquette.Etiquette
import com.example.project.Transaction.Transaction
import com.example.project.ui.theme.ProjectTheme
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    private val newActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var database = Room.databaseBuilder(this, TransactionRoomDatabase::class.java, "plutus.db").build()
//        val repository by lazy { TransactionRepository(database.TransactionDao()) }
//        var viewModel=TransactionViewModel(repository)
        setContent {
//            CallDatabase()
//            var plutus=PlutusApp()
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView( )
                    Row() {
                        //Greeting("Android")
                    }
                }
            }
        }
    }
}
// Test function, directly adds into database test data
@Composable
fun CallDatabase(){
    val context= LocalContext.current
    val viewModel : TransactionViewModel = viewModel (factory = TransactionViewModelFactory(context.applicationContext as Application))
//    viewModel.dropAll()
//    viewModel.insert(Transaction(0,0,"test",0, now().format(DateTimeFormatter.ofPattern("dd-MM-yy"))))
    val a =viewModel.getAll().observeAsState(listOf()).value
    Column() {
        for(e in a){
            Card() {
                Text(text = e.toString())
            }
        }
    }
    println(a)
}

@Composable
fun addTagButtons() {
    val context= LocalContext.current
    val viewModel : TransactionViewModel = viewModel (factory = TransactionViewModelFactory(context.applicationContext as Application))

    // Variables to create new transaction
    var list by remember { mutableStateOf(arrayListOf<Etiquette.Tag>()) }
    var tagText by remember { mutableStateOf(TextFieldValue("")) }
    var descText by remember { mutableStateOf(TextFieldValue("")) }
    var montant by remember { mutableStateOf(TextFieldValue(""))}
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
                        /**
                         * HERE WE ADD TRANSACTION INTO DATABASE
                         */

                        var tr= Transaction(0,0,descText.text,valMontant,now().format(DateTimeFormatter.ofPattern("dd-MM-yy")))
                        viewModel.insert(tr)
//                        Toast.makeText(context,tr.toString(),Toast.LENGTH_SHORT).show()
                        list.clear()
                        tagText.text.removeRange(0,tagText.text.length)
                        descText.text.removeRange(0,descText.text.length)
                        montant.text.removeRange(0,montant.text.length)
                    }

                }
            },
        ) {
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Add Transaction")
        }
    }
    Row {
        CallDatabase()
    }
}
@Composable
fun MainView() {
    Column(){
        addTagButtons()

    }



}
@Composable
fun Greeting(name: String) {
//    var tr = Transaction(0,0,"test",10, now(),arrayListOf<Etiquette.Tag>(Etiquette.Tag.Food))

//    Text(text = "$tr")

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectTheme {

    }
}