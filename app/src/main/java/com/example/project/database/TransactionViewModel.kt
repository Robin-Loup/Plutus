
import android.app.Application
import androidx.lifecycle.*
import com.example.project.Transaction
import com.example.project.database.TransactionRepository
import com.example.project.database.TransactionRoomDatabase
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TransactionViewModel(application : Application) : AndroidViewModel(application) {
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    private val readAllData: LiveData<List<Transaction>>
    private val repository : TransactionRepository

    init{
        val dao=TransactionRoomDatabase.getDatabase(application).TransactionDao()
        repository= TransactionRepository((dao))
        readAllData=repository.allTransactions
    }

    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }
    fun getAll() : LiveData<List<Transaction>> {
        return repository.getAll()
    }
    fun dropAll() = viewModelScope.launch {
        repository.dropAll()
    }
}

class TransactionViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(TransactionViewModel::class.java)){
            return TransactionViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")

    }
}