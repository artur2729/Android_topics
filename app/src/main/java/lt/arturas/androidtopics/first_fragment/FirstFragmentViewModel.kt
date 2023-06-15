package lt.arturas.androidtopics.first_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.arturas.androidtopics.repository.newsapi.NewsApiServiceClient
import lt.arturas.androidtopics.repository.newsapi.NewsApiServiceClientWithOkHttp
import lt.arturas.androidtopics.repository.newsapi.TopHeadlinesResponse
import lt.arturas.androidtopics.repository.reqres.ReqresServiceClient
import lt.arturas.androidtopics.repository.reqres.UsersResponse

class FirstFragmentViewModel : ViewModel() {

    private val _itemsStateFlow: MutableStateFlow<UsersResponse?> =
        MutableStateFlow(UsersResponse())
    val itemsStateFlow = _itemsStateFlow.asStateFlow()

    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = ReqresServiceClient.providesApiService().getUsers()
            _itemsStateFlow.value = resp.body()
        }
    }

    private val _topNewsStateFlow: MutableStateFlow<TopHeadlinesResponse?> =
        MutableStateFlow(TopHeadlinesResponse())
    val topNewsStateFlow = _topNewsStateFlow.asStateFlow()

    fun fetchTopNews(){
        viewModelScope.launch(Dispatchers.IO) {
//            val resp = NewsApiServiceClient.providesApiService().getTopNews("us")
            val resp = NewsApiServiceClientWithOkHttp.providesApiService().getTopNews("us")
            _topNewsStateFlow.value = resp.body()
        }
    }
}