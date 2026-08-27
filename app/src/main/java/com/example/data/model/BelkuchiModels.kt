package com.example.data.model

enum class BelkuchiCategory(
    val id: String,
    val titleBangla: String,
    val titleEnglish: String,
    val iconName: String,
    val description: String
) {
    EMERGENCY(
        id = "emergency",
        titleBangla = "জরুরি সেবা",
        titleEnglish = "Emergency",
        iconName = "emergency",
        description = "পুলিশ, ফায়ার সার্ভিস, এ্যাম্বুলেন্স ও সরকারি জরুরি হটলাইন"
    ),
    HEALTHCARE(
        id = "healthcare",
        titleBangla = "হাসপাতাল ও স্বাস্থ্য",
        titleEnglish = "Healthcare",
        iconName = "hospital",
        description = "উপজেলা স্বাস্থ্য কমপ্লেক্স, ক্লিনিক, ডায়াগনস্টিক ও ফার্মেসি"
    ),
    EDUCATION(
        id = "education",
        titleBangla = "শিক্ষা প্রতিষ্ঠান",
        titleEnglish = "Education",
        iconName = "school",
        description = "সরকারি কলেজ, পাইলট স্কুল, মাদ্রাসা ও ঐতিহ্যবাহী শিক্ষা প্রতিষ্ঠান"
    ),
    GOVERNMENT(
        id = "government",
        titleBangla = "সরকারি দপ্তর ও ইউনিয়ন",
        titleEnglish = "Government",
        iconName = "government",
        description = "উপজেলা পরিষদ, পৌরসভা, ভূমি অফিস ও ইউনিয়ন পরিষদ সমূহ"
    ),
    PLACES(
        id = "places",
        titleBangla = "দর্শনীয় স্থান ও তাঁত শিল্প",
        titleEnglish = "Places & Heritage",
        iconName = "places",
        description = "মুকুন্দগাঁতী কাপড়ের হাট, তামাই তাঁত পল্লী, যমুনা নদীর হার্ড পয়েন্ট ও পার্ক"
    ),
    TRANSPORT(
        id = "transport",
        titleBangla = "যাতায়াত ও পরিবহন",
        titleEnglish = "Transportation",
        iconName = "transport",
        description = "বাস স্ট্যান্ড, সিরাজগঞ্জ ও ঢাকা রুট, সিএনজি ও খেয়া ঘাট"
    ),
    ABOUT(
        id = "about",
        titleBangla = "বেলকুচি পরিচিতি",
        titleEnglish = "About Belkuchi",
        iconName = "info",
        description = "ইতিহাস, তাঁতের ঐতিহ্য, ভূগোল, জনসংখ্যা ও ভৌগোলিক তথ্য"
    )
}

data class PlaceItem(
    val id: String,
    val nameBangla: String,
    val nameEnglish: String,
    val category: BelkuchiCategory,
    val subcategoryBangla: String,
    val addressBangla: String,
    val addressEnglish: String,
    val phone: String? = null,
    val latitude: Double,
    val longitude: Double,
    val rating: Double? = null,
    val openingStatusBangla: String? = null,
    val openingHoursEnglish: String? = null,
    val isEmergency: Boolean = false,
    val verifiedSource: String = "Official Public Directory / Google Maps",
    val descriptionBangla: String,
    val tags: List<String> = emptyList(),
    val imageUrl: String? = null
)

data class UnionInfo(
    val id: String,
    val nameBangla: String,
    val nameEnglish: String,
    val chairmanName: String? = null,
    val officeLocation: String,
    val contactNumber: String? = null,
    val keyVillages: List<String>,
    val specialties: String,
    val areaSqKm: Double,
    val population: String
)

data class EmergencyContact(
    val id: String,
    val titleBangla: String,
    val titleEnglish: String,
    val number: String,
    val isTollFree: Boolean = false,
    val subtextBangla: String,
    val category: String, // Police, Fire, Ambulance, National, Blood
    val verifiedSource: String = "Government Hotline Directory"
)

data class LocationCoordinate(
    val latitude: Double,
    val longitude: Double,
    val locationName: String = "Belkuchi, Sirajganj"
)

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val locations: List<PlaceItem> = emptyList(),
    val suggestedActions: List<String> = emptyList(),
    val verifiedSource: String? = null,
    val isError: Boolean = false,
    val isStreaming: Boolean = false
)
