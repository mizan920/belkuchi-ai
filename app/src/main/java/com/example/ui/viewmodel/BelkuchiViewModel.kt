package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.BelkuchiApp
import com.example.data.db.SavedPlaceEntity
import com.example.data.db.SearchHistoryEntity
import com.example.data.local.BelkuchiLocalData
import com.example.data.model.BelkuchiCategory
import com.example.data.model.ChatMessage
import com.example.data.model.EmergencyContact
import com.example.data.model.LocationCoordinate
import com.example.data.model.PlaceItem
import com.example.data.model.UnionInfo
import com.example.data.repository.BelkuchiRepository
import com.example.data.service.LocationHelper
import com.example.data.service.SpeechManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BelkuchiViewModel(application: Application) : AndroidViewModel(application) {

    private val app = application as BelkuchiApp
    private val repository: BelkuchiRepository = app.repository
    private val locationHelper: LocationHelper = app.locationHelper
    private val speechManager: SpeechManager = app.speechManager

    private val _userLocation = MutableStateFlow(locationHelper.defaultBelkuchiLocation())
    val userLocation: StateFlow<LocationCoordinate> = _userLocation.asStateFlow()

    private val _isGeneratingResponse = MutableStateFlow(false)
    val isGeneratingResponse: StateFlow<Boolean> = _isGeneratingResponse.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<PlaceItem>>(BelkuchiLocalData.PLACES)
    val searchResults: StateFlow<List<PlaceItem>> = _searchResults.asStateFlow()

    private val _selectedCategory = MutableStateFlow<BelkuchiCategory?>(null)
    val selectedCategory: StateFlow<BelkuchiCategory?> = _selectedCategory.asStateFlow()

    val chatMessages: StateFlow<List<ChatMessage>> = repository.getChatMessages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val savedPlaces: StateFlow<List<SavedPlaceEntity>> = repository.getSavedPlaces()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val recentSearches: StateFlow<List<SearchHistoryEntity>> = repository.getRecentSearches()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val isListening: StateFlow<Boolean> = speechManager.isListening
    val speechText: StateFlow<String> = speechManager.speechText
    val isSpeaking: StateFlow<Boolean> = speechManager.isSpeaking

    private val _uiToastMessage = MutableStateFlow<String?>(null)
    val uiToastMessage: StateFlow<String?> = _uiToastMessage.asStateFlow()

    init {
        refreshLocation()
        // Ensure welcome message in chat if empty
        viewModelScope.launch {
            repository.getChatMessages().collect { list ->
                if (list.isEmpty()) {
                    val welcomeMessage = ChatMessage(
                        id = "welcome_msg",
                        text = "আসসালামু আলাইকুম! আমি **Belkuchi AI** — বেলকুচি উপজেলার তথ্য ও সহায়তাকারী স্মার্ট অ্যাসিস্ট্যান্ট।\n\nআপনি হাসপাতাল, ডাক্তার, পুলিশ, ফায়ার সার্ভিস, মুকুন্দগাঁতী কাপড়ের হাট, স্কুল-কলেজ বা যে কোনো স্থানীয় তথ্য সম্পর্কে প্রশ্ন করতে পারেন।",
                        isUser = false,
                        suggestedActions = listOf(
                            "উপজেলা স্বাস্থ্য কমপ্লেক্স",
                            "জরুরি পুলিশ নম্বর",
                            "মুকুন্দগাঁতী কাপড়ের হাট",
                            "কয়টি ইউনিয়ন আছে?"
                        ),
                        verifiedSource = "Belkuchi AI Local Engine"
                    )
                    repository.saveChatMessage(welcomeMessage)
                }
            }
        }
    }

    fun refreshLocation() {
        viewModelScope.launch {
            val loc = locationHelper.getCurrentLocation()
            _userLocation.value = loc
            performSearch(_searchQuery.value)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        performSearch(query)
    }

    fun performSearch(query: String) {
        viewModelScope.launch {
            val results = repository.searchPlaces(query, _userLocation.value)
            _searchResults.value = results
            if (query.isNotBlank()) {
                repository.addSearchQuery(query)
            }
        }
    }

    fun selectCategory(category: BelkuchiCategory?) {
        _selectedCategory.value = category
        if (category == null) {
            _searchResults.value = BelkuchiLocalData.PLACES
        } else {
            _searchResults.value = repository.getPlacesByCategory(category)
        }
    }

    fun sendMessage(text: String) {
        if (text.isBlank() || _isGeneratingResponse.value) return

        val trimmedText = text.trim()
        val userMsg = ChatMessage(
            text = trimmedText,
            isUser = true
        )

        viewModelScope.launch {
            repository.saveChatMessage(userMsg)
            _isGeneratingResponse.value = true

            val aiResponse = repository.queryBelkuchiAI(trimmedText, _userLocation.value)
            repository.saveChatMessage(aiResponse)
            _isGeneratingResponse.value = false
        }
    }

    fun startVoiceInput(onCompleted: (String) -> Unit) {
        speechManager.startListening(
            onResult = { resultText ->
                onCompleted(resultText)
                sendMessage(resultText)
            },
            onError = { errorMsg ->
                _uiToastMessage.value = errorMsg
            }
        )
    }

    fun stopVoiceInput() {
        speechManager.stopListening()
    }

    fun toggleSpeak(text: String) {
        if (speechManager.isSpeaking.value) {
            speechManager.stopSpeaking()
        } else {
            speechManager.speakText(text)
        }
    }

    fun toggleSavePlace(place: PlaceItem, isCurrentlySaved: Boolean) {
        viewModelScope.launch {
            repository.toggleSavePlace(place, isCurrentlySaved)
        }
    }

    fun clearToast() {
        _uiToastMessage.value = null
    }

    fun clearChatHistory() {
        viewModelScope.launch {
            repository.clearChat()
        }
    }

    fun clearSearchHistory() {
        viewModelScope.launch {
            repository.clearSearchHistory()
        }
    }

    fun getAllPlaces(): List<PlaceItem> = repository.getAllPlaces()
    fun getEmergencyContacts(): List<EmergencyContact> = repository.getEmergencyContacts()
    fun getUnions(): List<UnionInfo> = repository.getUnions()
}
