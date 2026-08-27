package com.example.data.repository

import com.example.BuildConfig
import com.example.data.db.AppDatabase
import com.example.data.db.ChatMessageEntity
import com.example.data.db.SavedPlaceEntity
import com.example.data.db.SearchHistoryEntity
import com.example.data.local.BelkuchiLocalData
import com.example.data.model.BelkuchiCategory
import com.example.data.model.ChatMessage
import com.example.data.model.EmergencyContact
import com.example.data.model.LocationCoordinate
import com.example.data.model.PlaceItem
import com.example.data.model.UnionInfo
import com.example.data.remote.GeminiContent
import com.example.data.remote.GeminiGenerationConfig
import com.example.data.remote.GeminiPart
import com.example.data.remote.GeminiRequest
import com.example.data.remote.RetrofitClient
import com.example.data.service.LocationHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.Locale

class BelkuchiRepository(
    private val database: AppDatabase,
    private val locationHelper: LocationHelper
) {

    fun getAllPlaces(): List<PlaceItem> = BelkuchiLocalData.PLACES

    fun getPlacesByCategory(category: BelkuchiCategory): List<PlaceItem> {
        return BelkuchiLocalData.PLACES.filter { it.category == category }
    }

    fun getEmergencyContacts(): List<EmergencyContact> = BelkuchiLocalData.EMERGENCY_CONTACTS

    fun getUnions(): List<UnionInfo> = BelkuchiLocalData.UNIONS

    fun getSavedPlaces(): Flow<List<SavedPlaceEntity>> {
        return database.savedPlaceDao().getAllSavedPlaces()
    }

    fun isPlaceSaved(placeId: String): Flow<Boolean> {
        return database.savedPlaceDao().isPlaceSaved(placeId)
    }

    suspend fun toggleSavePlace(place: PlaceItem, isCurrentlySaved: Boolean) {
        withContext(Dispatchers.IO) {
            if (isCurrentlySaved) {
                database.savedPlaceDao().deleteSavedPlace(place.id)
            } else {
                database.savedPlaceDao().savePlace(
                    SavedPlaceEntity(
                        id = place.id,
                        nameBangla = place.nameBangla,
                        nameEnglish = place.nameEnglish,
                        categoryId = place.category.id,
                        address = place.addressBangla,
                        phone = place.phone,
                        latitude = place.latitude,
                        longitude = place.longitude
                    )
                )
            }
        }
    }

    fun getRecentSearches(): Flow<List<SearchHistoryEntity>> {
        return database.searchHistoryDao().getRecentSearches()
    }

    suspend fun addSearchQuery(query: String) {
        if (query.isBlank()) return
        withContext(Dispatchers.IO) {
            database.searchHistoryDao().insertSearch(SearchHistoryEntity(query = query.trim()))
        }
    }

    suspend fun clearSearchHistory() {
        withContext(Dispatchers.IO) {
            database.searchHistoryDao().clearSearchHistory()
        }
    }

    fun getChatMessages(): Flow<List<ChatMessage>> {
        return database.chatMessageDao().getAllMessages().map { entities ->
            entities.map { entity ->
                val placeIds = entity.locationIdsJson?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                val matchedPlaces = BelkuchiLocalData.PLACES.filter { it.id in placeIds }
                ChatMessage(
                    id = entity.id,
                    text = entity.text,
                    isUser = entity.isUser,
                    timestamp = entity.timestamp,
                    locations = matchedPlaces,
                    verifiedSource = entity.verifiedSource,
                    isError = entity.isError
                )
            }
        }
    }

    suspend fun saveChatMessage(message: ChatMessage) {
        withContext(Dispatchers.IO) {
            val placeIds = message.locations.joinToString(",") { it.id }
            database.chatMessageDao().insertMessage(
                ChatMessageEntity(
                    id = message.id,
                    text = message.text,
                    isUser = message.isUser,
                    timestamp = message.timestamp,
                    locationIdsJson = if (placeIds.isNotBlank()) placeIds else null,
                    verifiedSource = message.verifiedSource,
                    isError = message.isError
                )
            )
        }
    }

    suspend fun clearChat() {
        withContext(Dispatchers.IO) {
            database.chatMessageDao().clearAllMessages()
        }
    }

    suspend fun searchPlaces(query: String, userLocation: LocationCoordinate): List<PlaceItem> {
        val q = query.trim().lowercase(Locale.ROOT)
        if (q.isBlank()) return BelkuchiLocalData.PLACES

        return BelkuchiLocalData.PLACES.filter { place ->
            place.nameBangla.contains(q, ignoreCase = true) ||
            place.nameEnglish.contains(q, ignoreCase = true) ||
            place.addressBangla.contains(q, ignoreCase = true) ||
            place.addressEnglish.contains(q, ignoreCase = true) ||
            place.subcategoryBangla.contains(q, ignoreCase = true) ||
            place.tags.any { it.contains(q, ignoreCase = true) } ||
            // Banglish query normalization
            isBanglishMatch(q, place)
        }.sortedBy { place ->
            LocationHelper.calculateDistanceKm(
                userLocation.latitude, userLocation.longitude,
                place.latitude, place.longitude
            )
        }
    }

    private fun isBanglishMatch(query: String, place: PlaceItem): Boolean {
        val q = query.lowercase()
        return when {
            (q.contains("haspatal") || q.contains("hospital") || q.contains("daktar") || q.contains("doctor")) &&
                    place.category == BelkuchiCategory.HEALTHCARE -> true
            (q.contains("police") || q.contains("thana") || q.contains("fire") || q.contains("joruri") || q.contains("emergency")) &&
                    (place.category == BelkuchiCategory.EMERGENCY || place.category == BelkuchiCategory.GOVERNMENT) -> true
            (q.contains("school") || q.contains("college") || q.contains("madrasa") || q.contains("sikha")) &&
                    place.category == BelkuchiCategory.EDUCATION -> true
            (q.contains("haat") || q.contains("tant") || q.contains("shari") || q.contains("bazar") || q.contains("market")) &&
                    (place.id == "place_mukundagati_haat" || place.id == "place_tamai_tant_palli") -> true
            (q.contains("jamuna") || q.contains("river") || q.contains("sunset") || q.contains("hard point") || q.contains("badh")) &&
                    place.id == "place_jamuna_hard_point" -> true
            (q.contains("bus") || q.contains("dhaka") || q.contains("sirajganj") || q.contains("cng") || q.contains("stand")) &&
                    place.category == BelkuchiCategory.TRANSPORT -> true
            (q.contains("uno") || q.contains("pourashava") || q.contains("land") || q.contains("khariz") || q.contains("shorkari")) &&
                    place.category == BelkuchiCategory.GOVERNMENT -> true
            else -> false
        }
    }

    suspend fun queryBelkuchiAI(
        userQuery: String,
        userLocation: LocationCoordinate
    ): ChatMessage = withContext(Dispatchers.IO) {
        val matchedPlaces = findRelevantPlaces(userQuery)
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        // Check if real Gemini API key is configured
        if (apiKey.isNotBlank() && apiKey != "MY_GEMINI_API_KEY" && !apiKey.startsWith("YOUR_")) {
            try {
                val systemPrompt = buildSystemPrompt(matchedPlaces)
                val request = GeminiRequest(
                    contents = listOf(
                        GeminiContent(
                            role = "user",
                            parts = listOf(GeminiPart(text = userQuery))
                        )
                    ),
                    systemInstruction = GeminiContent(
                        parts = listOf(GeminiPart(text = systemPrompt))
                    ),
                    generationConfig = GeminiGenerationConfig(
                        temperature = 0.3f,
                        maxOutputTokens = 600
                    )
                )

                val response = RetrofitClient.geminiService.generateContent(apiKey, request)
                val responseText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text

                if (!responseText.isNullOrBlank()) {
                    return@withContext ChatMessage(
                        text = responseText.trim(),
                        isUser = false,
                        locations = matchedPlaces,
                        verifiedSource = "Google Gemini AI & Verified Belkuchi Directory",
                        suggestedActions = buildSuggestedActions(userQuery)
                    )
                }
            } catch (e: Exception) {
                // If API fails, fall back to offline intelligent local knowledge base smoothly
            }
        }

        // Local Intelligent Offline Engine (Matches query intent, union info, emergency contacts & locations)
        val localResponse = generateLocalAIResponse(userQuery, matchedPlaces, userLocation)
        return@withContext localResponse
    }

    private fun findRelevantPlaces(query: String): List<PlaceItem> {
        val q = query.lowercase(Locale.ROOT)
        return BelkuchiLocalData.PLACES.filter { place ->
            val inBangla = place.nameBangla.lowercase().contains(q) || place.addressBangla.lowercase().contains(q) || place.descriptionBangla.lowercase().contains(q)
            val inEnglish = place.nameEnglish.lowercase().contains(q) || place.addressEnglish.lowercase().contains(q)
            val inTags = place.tags.any { tag -> q.contains(tag) || tag.contains(q) }
            val inSubcategory = q.contains(place.subcategoryBangla.lowercase())
            val matchesCategory = when {
                (q.contains("হাসপাতাল") || q.contains("ক্লিনিক") || q.contains("ডাক্তার") || q.contains("ফার্মেসি") || q.contains("hospital") || q.contains("clinic")) && place.category == BelkuchiCategory.HEALTHCARE -> true
                (q.contains("পুলিশ") || q.contains("থানা") || q.contains("ফায়ার") || q.contains("আগুন") || q.contains("police") || q.contains("fire")) && (place.category == BelkuchiCategory.EMERGENCY || place.category == BelkuchiCategory.GOVERNMENT) -> true
                (q.contains("স্কুল") || q.contains("কলেজ") || q.contains("মাদ্রাসা") || q.contains("school") || q.contains("college")) && place.category == BelkuchiCategory.EDUCATION -> true
                (q.contains("ইউএনও") || q.contains("পৌরসভা") || q.contains("ভূমি") || q.contains("সরকারি") || q.contains("uno") || q.contains("ac land")) && place.category == BelkuchiCategory.GOVERNMENT -> true
                (q.contains("হাট") || q.contains("কাপড়") || q.contains("তাঁত") || q.contains("শাড়ি") || q.contains("লুঙ্গি") || q.contains("বাজার") || q.contains("haat")) && (place.id == "place_mukundagati_haat" || place.id == "place_tamai_tant_palli") -> true
                (q.contains("যমুনা") || q.contains("নদী") || q.contains("হার্ড পয়েন্ট") || q.contains("দর্শনীয়") || q.contains("ঘুরতে") || q.contains("sunset")) && place.id == "place_jamuna_hard_point" -> true
                (q.contains("বাস") || q.contains("সিরাজগঞ্জ") || q.contains("ঢাকা") || q.contains("যাব") || q.contains("ভাড়া") || q.contains("সিএনজি") || q.contains("bus")) && place.category == BelkuchiCategory.TRANSPORT -> true
                else -> false
            }
            inBangla || inEnglish || inTags || inSubcategory || matchesCategory
        }.take(4)
    }

    private fun generateLocalAIResponse(
        query: String,
        matchedPlaces: List<PlaceItem>,
        userLocation: LocationCoordinate
    ): ChatMessage {
        val q = query.trim().lowercase(Locale.ROOT)

        val (replyText, source) = when {
            // Emergency Queries
            q.contains("পুলিশ") || q.contains("থানা") || q.contains("police") -> {
                Pair(
                    "🚨 **বেলকুচি থানা পুলিশ জরুরি নম্বর:**\n" +
                    "• ডিউটি অফিসার / ওসি: **01320-128450**\n" +
                    "• থানা ডিউটি রুম: **01320-128455**\n" +
                    "• জাতীয় জরুরি হটলাইন: **৯৯৯** (টোল-ফ্রি)\n\n" +
                    "যে কোনো তাৎক্ষণিক আইন-শৃঙ্খলা বা জরুরি সহায়তায় সরাসরি ডায়াল করতে পারেন।",
                    "বাংলাদেশ পুলিশ সিরাজগঞ্জ ডিরেক্টরি"
                )
            }
            q.contains("ফায়ার") || q.contains("আগুন") || q.contains("fire") -> {
                Pair(
                    "🔥 **বেলকুচি ফায়ার সার্ভিস ও সিভিল ডিফেন্স:**\n" +
                    "• স্টেশন মোবাইল: **01716-179374**\n" +
                    "• টিঅ্যান্ডটি নম্বর: **07524-56199**\n" +
                    "• জাতীয় সেবা: **৯৯৯**\n\n" +
                    "অগ্নিদুর্ঘটনা, সড়ক দুর্ঘটনা বা নদী উদ্ধারে অবিলম্বে যোগাযোগ করুন।",
                    "বাংলাদেশ ফায়ার সার্ভিস ও সিভিল ডিফেন্স"
                )
            }
            q.contains("হাসপাতাল") || q.contains("স্বাস্থ্য") || q.contains("hospital") || q.contains("ডাক্তার") -> {
                Pair(
                    "🏥 **বেলকুচি উপজেলা স্বাস্থ্য কমপ্লেক্স ও স্বাস্থ্যসেবা:**\n" +
                    "• সরকারি ৫০ শয্যা স্বাস্থ্য কমপ্লেক্স: **01712-421715** (২৪ ঘণ্টা জরুরি বিভাগ খোলা)\n" +
                    "• অবস্থান: শাহী বাজার / তামাই রোড, বেলকুচি সদর\n" +
                    "• এছাড়াও তামাই ও মুকুন্দগাঁতীতে অভিজ্ঞ ডাক্তার ও ডায়াগনস্টিক সুবিধা রয়েছে।",
                    "স্বাস্থ্য অধিদপ্তর (DGHS) ও উপজেলা প্রশাসন"
                )
            }
            q.contains("ফার্মেসি") || q.contains("ঔষধ") || q.contains("pharmacy") || q.contains("medicine") -> {
                Pair(
                    "💊 **বেলকুচিতে ফার্মেসি ও ঔষধের দোকান:**\n" +
                    "• উপজেলা স্বাস্থ্য কমপ্লেক্সের প্রধান ফটকের সামনে ২৪ ঘণ্টা মডেল ফার্মেসি খোলা থাকে।\n" +
                    "• মুকুন্দগাঁতী বাজার ও চৌরাস্তা মোড়ে প্রয়োজনীয় সকল মেডিসিন ও ইনসুলিন পাওয়া যায়।",
                    "স্থানীয় ফার্মেসি ডিরেক্টরি"
                )
            }
            q.contains("ইউনিয়ন") || q.contains("union") || q.contains("কয়টি ইউনিয়ন") -> {
                Pair(
                    "🏛️ **বেলকুচি উপজেলায় মোট ৬টি ইউনিয়ন ও ১টি পৌরসভা রয়েছে:**\n\n" +
                    "১. **বেলকুচি সদর (পৌরসভা)** - প্রধান বাণিজ্যিক কেন্দ্র ও মুকুন্দগাঁতী\n" +
                    "২. **১নং রাজাপুর ইউনিয়ন** - যমুনাতীর ও সমৃদ্ধ তাঁত পল্লী\n" +
                    "৩. **২নং দৌলতপুর ইউনিয়ন** - উর্বর কৃষি ও তাঁত শিল্প\n" +
                    "৪. **৩নং ভাঙ্গাবাড়ী ইউনিয়ন** - তামাই তাঁত পল্লী এলাকা\n" +
                    "৫. **৪নং ধুকুরিয়া বেড়া ইউনিয়ন** - সুতি ও সিল্ক শাড়ি বুনন\n" +
                    "৬. **৫নং বড়ধুল ইউনিয়ন** - যমুনার চর ও নদী অববাহিকা\n\n" +
                    "উপজেলার মোট আয়তন প্রায় ১৬৪.৩১ বর্গ কিমি।",
                    "জাতীয় তথ্য বাতায়ন (belkuchi.sirajganj.gov.bd)"
                )
            }
            q.contains("হাট") || q.contains("কাপড়") || q.contains("তাঁত") || q.contains("শাড়ি") || q.contains("লুঙ্গি") || q.contains("haat") -> {
                Pair(
                    "🧵 **মুকুন্দগাঁতী কাপড়ের হাট ও তামাই তাঁত পল্লী:**\n\n" +
                    "• **মুকুন্দগাঁতী হাট:** প্রতি **মঙ্গলবার ও বুধবার** ভোর থেকে দুপুর পর্যন্ত বসে। এটি এশিয়ার অন্যতম বৃহৎ তাঁত কাপড়ের পাইকারি হাট।\n" +
                    "• **তামাই তাঁত পল্লী:** বাংলাদেশের অন্যতম প্রধান তাঁতের গ্রাম। ঐতিহ্যবাহী জামদানি, জ্যাকার্ড ও সুতি শাড়ির কারিগরদের বুনন দেখতে দর্শনার্থীরা আসেন।",
                    "বাংলাদেশ তাঁত বোর্ড ও স্থানীয় বাণিজ্য সমিতি"
                )
            }
            q.contains("দর্শনীয়") || q.contains("ঘুরতে") || q.contains("যমুনা") || q.contains("হার্ড পয়েন্ট") || q.contains("tourist") || q.contains("place") -> {
                Pair(
                    "🌅 **বেলকুচির জনপ্রিয় দর্শনীয় ও ঐতিহাসিক স্থান:**\n\n" +
                    "১. **যমুনা নদী হার্ড পয়েন্ট ও বাঁধ:** সূর্যাস্ত ও নদীর মুক্ত বাতাস উপভোগের প্রধান স্থান।\n" +
                    "২. **মুকুন্দগাঁতী কাপড়ের হাট:** তাঁতের বিশাল কর্মযজ্ঞ ও ঐতিহ্য।\n" +
                    "৩. **তামাই তাঁত পল্লী:** বাড়ি বাড়ি তাঁতের খটখট শব্দ ও নকশিকাজ।\n" +
                    "৪. **সোহাগপুর এস.কে. পাইলট মডেল হাই স্কুল:** ১৯১৩ সালে প্রতিষ্ঠিত শতবর্ষী স্থাপত্য।\n" +
                    "৫. **মুক্তিযুদ্ধ স্মৃতিস্তম্ভ:** উপজেলা পরিষদ গেট চত্বর।",
                    "বেলকুচি পর্যটন ও স্থানীয় ঐতিহ্য গাইড"
                )
            }
            q.contains("সিরাজগঞ্জ") && (q.contains("কীভাবে") || q.contains("যাব") || q.contains("পথ") || q.contains("how")) -> {
                Pair(
                    "🚗 **বেলকুচি থেকে সিরাজগঞ্জ জেলা শহর যাতায়াত:**\n\n" +
                    "• **দূরত্ব:** প্রায় ১৮ কিলোমিটার (উত্তর দিকে)।\n" +
                    "• **যানবাহন:** বেলকুচি চৌরাস্তা বা মুকুন্দগাঁতী থেকে নিয়মিত সিএনজি ও অটো পাওয়া যায়।\n" +
                    "• **ভ্রমণ সময়:** আনুমানিক ৩০ থেকে ৪০ মিনিট।\n" +
                    "• **ভাড়া:** সাধারণত ৩০-৪০ টাকা জনপ্রতি।",
                    "সিরাজগঞ্জ জেলা পরিবহন নির্দেশিকা"
                )
            }
            q.contains("ঢাকা") && (q.contains("বাস") || q.contains("যাব") || q.contains("রুট")) -> {
                Pair(
                    "🚌 **বেলকুচি থেকে ঢাকা যাতায়াত:**\n\n" +
                    "• বেলকুচি মুকুন্দগাঁতী বাস কাউন্টার থেকে নিয়মিত সরাসরি দূরপাল্লার বাস চলাচল করে (বঙ্গবন্ধু যমুনা সেতু হয়ে)।\n" +
                    "• গন্তব্য: গাবতলী ও মহাখালী টার্মিনাল।\n" +
                    "• প্রধান বাস সার্ভিস: এসআই এন্টারপ্রাইজ, শ্যামলী, আবাবিল পরিবহন।",
                    "বাস মালিক সমিতি কাউন্টার"
                )
            }
            q.contains("ইউএনও") || q.contains("uno") || q.contains("পৌরসভা") || q.contains("ac land") || q.contains("সরকারি") -> {
                Pair(
                    "🏛️ **বেলকুচি সরকারি ও প্রশাসনিক কার্যালয়:**\n\n" +
                    "• **ইউএনও কার্যালয়:** উপজেলা পরিষদ চত্বর (01705-411320)\n" +
                    "• **পৌরসভা কার্যালয়:** মুকুন্দগাঁতী সদর (07524-56012)\n" +
                    "• **এসি ল্যান্ড (ভূমি) অফিস:** উপজেলা কমপ্লেক্স চত্বর (ই-নামজারি ও জমি সংক্রান্ত সেবা)\n" +
                    "• কর্মদিবস: রবি থেকে বৃহস্পতিবার সকাল ৯:০০ - বিকেল ৫:০০।",
                    "জাতীয় তথ্য বাতায়ন"
                )
            }
            q.contains("স্কুল") || q.contains("কলেজ") || q.contains("মাদ্রাসা") || q.contains("school") || q.contains("education") -> {
                Pair(
                    "🎓 **বেলকুচির প্রধান শিক্ষা প্রতিষ্ঠান সমূহ:**\n\n" +
                    "• **বেলকুচি সরকারি কলেজ:** কলেজ রোড, স্নাতক ও অনার্স কোর্স চালু।\n" +
                    "• **সোহাগপুর এস.কে. পাইলট মডেল হাই স্কুল:** ১৯১৩ সালে প্রতিষ্ঠিত শতবর্ষী স্কুল।\n" +
                    "• **তামাই বহুমুখী উচ্চ বিদ্যালয় ও তামাই ইসলামিয়া ফাযিল মাদ্রাসা।**\n" +
                    "• **বেলকুচি মডেল সরকারি প্রাথমিক বিদ্যালয়।**",
                    "মাধ্যমিক ও উচ্চশিক্ষা অধিদপ্তর"
                )
            }
            else -> {
                val placeSummary = if (matchedPlaces.isNotEmpty()) {
                    "আপনার প্রশ্নের সাথে সম্পর্কিত প্রয়োজনীয় স্থান ও যোগাযোগের তথ্য নিচে প্রদর্শন করা হলো:"
                } else {
                    "আমি বেলকুচি উপজেলার তথ্যভাণ্ডার থেকে আপনার প্রশ্নের উত্তর খুঁজে দেখছি। আপনি হাসপাতাল, জরুরি নম্বর, স্কুল-কলেজ, মুকুন্দগাঁতী হাট, যমুনা হার্ড পয়েন্ট বা যাতায়াত রুট সম্পর্কে যে কোনো প্রশ্ন করতে পারেন।"
                }
                Pair(placeSummary, "বেলকুচি লোকাল ডিরেক্টরি")
            }
        }

        return ChatMessage(
            text = replyText,
            isUser = false,
            locations = matchedPlaces,
            verifiedSource = source,
            suggestedActions = buildSuggestedActions(query)
        )
    }

    private fun buildSuggestedActions(query: String): List<String> {
        val q = query.lowercase()
        return when {
            q.contains("হাসপাতাল") || q.contains("স্বাস্থ্য") -> listOf("জরুরি অ্যাম্বুলেন্স", "ফার্মেসি খুঁজুন", "ম্যাপে দেখুন")
            q.contains("পুলিশ") || q.contains("জরুরি") -> listOf("৯৯৯ ডায়াল করুন", "ফায়ার সার্ভিস", "থানা লোকেশন")
            q.contains("হাট") || q.contains("কাপড়") -> listOf("তামাই তাঁত পল্লী", "মুকুন্দগাঁতী ম্যাপ", "হাটের দিন")
            q.contains("সিরাজগঞ্জ") -> listOf("বাস কাউন্টার", "সিএনজি স্ট্যান্ড", "রাস্তার রুট")
            else -> listOf("জরুরি সেবা", "হাসপাতাল", "ম্যাপ দেখুন")
        }
    }

    private fun buildSystemPrompt(matchedPlaces: List<PlaceItem>): String {
        val placesContext = if (matchedPlaces.isNotEmpty()) {
            "Verified relevant locations in Belkuchi:\n" + matchedPlaces.joinToString("\n") {
                "- ${it.nameBangla} (${it.nameEnglish}), Address: ${it.addressBangla}, Phone: ${it.phone ?: "N/A"}, Lat: ${it.latitude}, Lng: ${it.longitude}"
            }
        } else {
            "Use verified Belkuchi knowledge: Belkuchi is an Upazila in Sirajganj District, Bangladesh. Known for Mukundagati Cloth Haat (Tuesday/Wednesday), Tamai Tant Palli, Jamuna River Hard Point, Upazila Health Complex (Shahi Bazar), Police Thana (01320-128450), 6 Unions (Rajapur, Daulatpur, Bhangabari, Dhukuria Bera, Borodhul, Belkuchi Sadar/Pourashava)."
        }

        return """
You are "Belkuchi AI" (বেলকুচির স্মার্ট লোকাল অ্যাসিস্ট্যান্ট), a helpful, friendly, respectful, fast, and neutral AI assistant specifically built for Belkuchi Upazila, Sirajganj, Bangladesh.

Response Guidelines:
1. Always respond in the language the user asked in (Bangla for Bangla queries, English for English queries, friendly Bangla for Banglish).
2. Provide concise, clear, and highly useful answers. Avoid unnecessary long paragraphs.
3. NEVER invent phone numbers, addresses, opening hours, or official details. Rely strictly on verified facts.
4. If asked about locations (hospitals, schools, pharmacies, markets, offices), mention specific landmarks in Belkuchi (e.g., Mukundagati, Tamai, Sohagpur, Chowrasta, Shahi Bazar).
5. Always maintain a polite and local tone.

Context data:
$placesContext
"""
    }
}
