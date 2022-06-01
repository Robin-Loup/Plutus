
import androidx.lifecycle.*
import com.example.project.Transaction
import com.example.project.database.TransactionRepository
import com.example.project.database.TransactionRoomDatabase
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    val allTransactions: LiveData<List<Transaction>> = repository.allTransactions.asLiveData()
    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }
}

class TransactionViewModelFactory(private val repository: TransactionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}