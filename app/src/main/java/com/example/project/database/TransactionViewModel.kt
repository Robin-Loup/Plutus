
import android.app.Application
import androidx.lifecycle.*
import com.example.project.Transaction
import com.example.project.TransactionDAO
import com.example.project.database.TransactionRepository
import com.example.project.database.TransactionRoomDatabase
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    val allTransactions: LiveData<List<Transaction>>
    private var transactionDao : TransactionDAO = TransactionRoomDatabase.getDatabase(application).TransactionDao()

    init{
        allTransactions = transactionDao.getAllTr()
    }

    fun insert(transaction: Transaction) = viewModelScope.launch {
        transactionDao.insertTr(transaction)
    }
}

class TransactionViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}