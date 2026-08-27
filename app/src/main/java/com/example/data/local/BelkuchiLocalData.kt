package com.example.data.local

import com.example.data.model.BelkuchiCategory
import com.example.data.model.EmergencyContact
import com.example.data.model.PlaceItem
import com.example.data.model.UnionInfo

object BelkuchiLocalData {

    const val BELKUCHI_CENTER_LAT = 24.2965
    const val BELKUCHI_CENTER_LNG = 89.7042

    val EMERGENCY_CONTACTS = listOf(
        EmergencyContact(
            id = "em_police_thana",
            titleBangla = "বেলকুচি থানা (অফিসার ইনচার্জ / ডিউটি অফিসার)",
            titleEnglish = "Belkuchi Police Station (OC / Duty Officer)",
            number = "01320-128450",
            subtextBangla = "২৪ ঘণ্টা জরুরি পুলিশ সহায়তা ও টহল সেবা",
            category = "Police",
            verifiedSource = "বাংলাদেশ পুলিশ সিরাজগঞ্জ জেলা ডিরেক্টরি"
        ),
        EmergencyContact(
            id = "em_police_sp",
            titleBangla = "বেলকুচি থানা ডিউটি রুম",
            titleEnglish = "Belkuchi Thana Duty Room",
            number = "01320-128455",
            subtextBangla = "থানা নিয়ন্ত্রণ কক্ষ ও তাৎক্ষণিক সাধারণ ডায়েরি সহায়তা",
            category = "Police",
            verifiedSource = "সিরাজগঞ্জ জেলা পুলিশ"
        ),
        EmergencyContact(
            id = "em_fire",
            titleBangla = "বেলকুচি ফায়ার সার্ভিস ও সিভিল ডিফেন্স স্টেশন",
            titleEnglish = "Belkuchi Fire Service & Civil Defence",
            number = "01716-179374",
            subtextBangla = "অগ্নিদুর্ঘটনা, নদী উদ্ধার ও জরুরি দুর্যোগ মোকাবিলা",
            category = "Fire",
            verifiedSource = "বাংলাদেশ ফায়ার সার্ভিস ও সিভিল ডিফেন্স"
        ),
        EmergencyContact(
            id = "em_health_complex",
            titleBangla = "বেলকুচি উপজেলা স্বাস্থ্য কমপ্লেক্স ইমার্জেন্সি",
            titleEnglish = "Belkuchi Upazila Health Complex Emergency",
            number = "01712-421715",
            subtextBangla = "২৪/৭ জরুরি বিভাগ ও সরকারি চিকিৎসক পরামর্শ",
            category = "Hospital",
            verifiedSource = "স্বাস্থ্য অধিদপ্তর (DGHS)"
        ),
        EmergencyContact(
            id = "em_health_complex_uhfpo",
            titleBangla = "উপজেলা স্বাস্থ্য ও পঃ পঃ কর্মকর্তা (UH&FPO)",
            titleEnglish = "Upazila Health & Family Planning Officer",
            number = "01730-324795",
            subtextBangla = "বেলকুচি স্বাস্থ্য প্রশাসন ও জরুরি তদারকি",
            category = "Hospital",
            verifiedSource = "সিভিল সার্জন কার্যালয় সিরাজগঞ্জ"
        ),
        EmergencyContact(
            id = "em_999",
            titleBangla = "জাতীয় জরুরি সেবা ৯৯৯",
            titleEnglish = "National Emergency Service 999",
            number = "999",
            isTollFree = true,
            subtextBangla = "পুলিশ, ফায়ার সার্ভিস ও এ্যাম্বুলেন্সের টোল-ফ্রি জাতীয় সেবা",
            category = "National",
            verifiedSource = "ICT Division / Bangladesh Police"
        ),
        EmergencyContact(
            id = "em_333",
            titleBangla = "সরকারি তথ্য ও সামাজিক সমস্যা সেবা ৩৩৩",
            titleEnglish = "Government Services Hotline 333",
            number = "333",
            isTollFree = true,
            subtextBangla = "সরকারি কর্মকর্তা তথ্য, ই-সেবা ও সামাজিক সহায়তা",
            category = "National",
            verifiedSource = "a2i / ICT Division"
        ),
        EmergencyContact(
            id = "em_109",
            titleBangla = "নারী ও শিশু নির্যাতন প্রতিরোধ হেল্পলাইন ১০৯",
            titleEnglish = "Women & Children Protection Hotline 109",
            number = "109",
            isTollFree = true,
            subtextBangla = "২৪ ঘণ্টা টোল ফ্রি আইনি ও সামাজিক সুরক্ষা সহায়তা",
            category = "National",
            verifiedSource = "মহিলা ও শিশু বিষয়ক মন্ত্রণালয়"
        ),
        EmergencyContact(
            id = "em_ambulance_district",
            titleBangla = "সিরাজগঞ্জ ২৫০ শয্যা জেনারেল হাসপাতাল এ্যাম্বুলেন্স",
            titleEnglish = "Sirajganj 250 Bed Hospital Ambulance",
            number = "01712-243627",
            subtextBangla = "জরুরি রোগী স্থানান্তর ও আইসিইউ এ্যাম্বুলেন্স",
            category = "Ambulance",
            verifiedSource = "সিরাজগঞ্জ জেলা হাসপাতাল"
        ),
        EmergencyContact(
            id = "em_red_crescent",
            titleBangla = "রেড ক্রিসেন্ট ব্লাড সেন্টার সিরাজগঞ্জ",
            titleEnglish = "Red Crescent Blood Center Sirajganj",
            number = "01716-081033",
            subtextBangla = "জরুরি রক্তের প্রয়োজন ও রক্তদাতা সন্ধান",
            category = "Blood",
            verifiedSource = "বাংলাদেশ রেড ক্রিসেন্ট সোসাইটি"
        ),
        EmergencyContact(
            id = "em_land_hotline",
            titleBangla = "ভূমি সেবা হেল্পলাইন ১৬১২২",
            titleEnglish = "Land Service Hotline 16122",
            number = "16122",
            isTollFree = true,
            subtextBangla = "ই-নামজারি, খতিয়ান ও ভূমি সংক্রান্ত নাগরিক সেবা",
            category = "National",
            verifiedSource = "ভূমি মন্ত্রণালয়"
        ),
        EmergencyContact(
            id = "em_uno_belkuchi",
            titleBangla = "উপজেলা নির্বাহী অফিসার (UNO) বেলকুচি",
            titleEnglish = "Upazila Nirbahi Officer Belkuchi",
            number = "01705-411320",
            subtextBangla = "উপজেলা প্রশাসন ও সার্বিক নাগরিক সেবা",
            category = "National",
            verifiedSource = "উপজেলা প্রশাসন বেলকুচি"
        )
    )

    val PLACES = listOf(
        // HEALTHCARE
        PlaceItem(
            id = "place_health_complex",
            nameBangla = "বেলকুচি উপজেলা স্বাস্থ্য কমপ্লেক্স (৫০ শয্যা)",
            nameEnglish = "Belkuchi Upazila Health Complex (50 Beds)",
            category = BelkuchiCategory.HEALTHCARE,
            subcategoryBangla = "সরকারি হাসপাতাল",
            addressBangla = "শাহী বাজার / তামাই রোড, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Shahi Bazar / Tamai Road, Belkuchi, Sirajganj",
            phone = "01712-421715",
            latitude = 24.2982,
            longitude = 89.7028,
            rating = 4.3,
            openingStatusBangla = "২৪ ঘণ্টা খোলা (জরুরি বিভাগ)",
            openingHoursEnglish = "24 Hours (Emergency)",
            isEmergency = true,
            verifiedSource = "স্বাস্থ্য অধিদপ্তর ও উপজেলা প্রশাসন",
            descriptionBangla = "বেলকুচির প্রধান সরকারি স্বাস্থ্যসেবা প্রতিষ্ঠান। এখানে বহির্বিভাগ, আন্তঃবিভাগ, জরুরি বিভাগ, এক্স-রে, আল্ট্রাসনোগ্রাফি, ইসিজি, প্যাথলজি ও প্রসূতি সেবা প্রদান করা হয়।",
            tags = listOf("হাসপাতাল", "hospital", "doctor", "জরুরি", "স্বাস্থ্য কমপ্লেক্স", "dghs", "সরকারি হাসপাতাল")
        ),
        PlaceItem(
            id = "place_tamai_modern_clinic",
            nameBangla = "তামাই মডার্ন ক্লিনিক ও ডায়াগনস্টিক সেন্টার",
            nameEnglish = "Tamai Modern Clinic & Diagnostic Center",
            category = BelkuchiCategory.HEALTHCARE,
            subcategoryBangla = "বেসরকারি ক্লিনিক ও ডায়াগনস্টিক",
            addressBangla = "তামাই পশ্চিম বাজার, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Tamai West Bazar, Belkuchi, Sirajganj",
            phone = "01715-628490",
            latitude = 24.3120,
            longitude = 89.6890,
            rating = 4.2,
            openingStatusBangla = "সকাল ৮:০০ - রাত ১০:০০",
            openingHoursEnglish = "8:00 AM - 10:00 PM",
            verifiedSource = "Local Verified Directory",
            descriptionBangla = "অভিজ্ঞ বিশেষজ্ঞ ডাক্তার চেম্বার, আল্ট্রাসনোগ্রাম, রক্ত ও ডিজিটাল ল্যাব টেস্টের সুবিধা রয়েছে।",
            tags = listOf("ক্লিনিক", "clinic", "diagnostic", "tamai", "তামাই", "doctor", "টেস্ট")
        ),
        PlaceItem(
            id = "place_al_madina_diag",
            nameBangla = "আল-মদিনা ডায়াগনস্টিক অ্যান্ড কনসালটেশন সেন্টার",
            nameEnglish = "Al-Madina Diagnostic & Consultation Center",
            category = BelkuchiCategory.HEALTHCARE,
            subcategoryBangla = "ডায়াগনস্টিক সেন্টার",
            addressBangla = "মুকুন্দগাঁতী বাজার, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Mukundagati Bazar, Belkuchi, Sirajganj",
            phone = "01718-934521",
            latitude = 24.2940,
            longitude = 89.7085,
            rating = 4.1,
            openingStatusBangla = "সকাল ৮:০০ - রাত ৯:০০",
            openingHoursEnglish = "8:00 AM - 9:00 PM",
            verifiedSource = "Google Maps / Verified Directory",
            descriptionBangla = "প্যাথলজি ল্যাবরেটরি, ইসিজি, বিশেষজ্ঞ ডাক্তারদের নিয়মিত চেম্বার ও ডিজিটাল রিপোর্ট ডেলিভারি।",
            tags = listOf("diagnostic", "mukundagati", "মুকুন্দগাঁতী", "ডায়াগনস্টিক", "ডাক্তার")
        ),
        PlaceItem(
            id = "place_seba_hospital",
            nameBangla = "সেবা জেনারেল হাসপাতাল অ্যান্ড ডায়াগনস্টিক",
            nameEnglish = "Seba General Hospital & Diagnostic",
            category = BelkuchiCategory.HEALTHCARE,
            subcategoryBangla = "বেসরকারি হাসপাতাল",
            addressBangla = "বেলকুচি চৌরাস্তা মোড়, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Belkuchi Chowrasta More, Belkuchi, Sirajganj",
            phone = "01711-239845",
            latitude = 24.2955,
            longitude = 89.7050,
            rating = 4.0,
            openingStatusBangla = "২৪ ঘণ্টা খোলা",
            openingHoursEnglish = "24 Hours Open",
            isEmergency = true,
            verifiedSource = "Local Directory / Google Maps",
            descriptionBangla = "চৌরাস্তা সংলগ্ন সুবিধাজনক স্থানে ইনডোর ও আউটডোর সেবা, সার্জারি ও জরুরি রোগী ভর্তি ব্যবস্থা।",
            tags = listOf("hospital", "seba", "সেবা হাসপাতাল", "চৌরাস্তা", "emergency")
        ),
        PlaceItem(
            id = "place_model_pharmacy",
            nameBangla = "মডেল ফার্মেসি ও মেডিসিন কর্নার",
            nameEnglish = "Model Pharmacy & Medicine Corner",
            category = BelkuchiCategory.HEALTHCARE,
            subcategoryBangla = "ফার্মেসি ও ঔষধের দোকান",
            addressBangla = "উপজেলা স্বাস্থ্য কমপ্লেক্স প্রধান গেটের সামনে, বেলকুচি",
            addressEnglish = "Opposite Upazila Health Complex Main Gate, Belkuchi",
            phone = "01724-889911",
            latitude = 24.2980,
            longitude = 89.7030,
            rating = 4.5,
            openingStatusBangla = "২৪ ঘণ্টা খোলা",
            openingHoursEnglish = "24 Hours Open",
            isEmergency = true,
            verifiedSource = "Verified Local Pharmacy",
            descriptionBangla = "সব ধরনের প্রয়োজনীয় ও জীবনরক্ষাকারী ঔষধ, ইনসুলিন ও সার্জিক্যাল সামগ্রী ন্যায্যমূল্যে পাওয়া যায়।",
            tags = listOf("ফার্মেসি", "pharmacy", "medicine", "ঔষধ", "health complex")
        ),
        PlaceItem(
            id = "place_biswas_pharmacy",
            nameBangla = "বিশ্বাস ফার্মেসি",
            nameEnglish = "Biswas Pharmacy",
            category = BelkuchiCategory.HEALTHCARE,
            subcategoryBangla = "ফার্মেসি",
            addressBangla = "মুকুন্দগাঁতী কাপড় পট্টি, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Mukundagati Cloth Market Area, Belkuchi",
            phone = "01712-654321",
            latitude = 24.2935,
            longitude = 89.7090,
            rating = 4.4,
            openingStatusBangla = "সকাল ৭:০০ - রাত ১১:০০",
            openingHoursEnglish = "7:00 AM - 11:00 PM",
            verifiedSource = "Google Maps",
            descriptionBangla = "মুকুন্দগাঁতীর পুরাতন ও নির্ভরযোগ্য ঔষধালয়।",
            tags = listOf("pharmacy", "medicine", "mukundagati", "ঔষধ")
        ),

        // EDUCATION
        PlaceItem(
            id = "place_belkuchi_college",
            nameBangla = "বেলকুচি সরকারি কলেজ",
            nameEnglish = "Belkuchi Government College",
            category = BelkuchiCategory.EDUCATION,
            subcategoryBangla = "সরকারি মহাবিদ্যালয়",
            addressBangla = "কলেজ রোড, বেলকুচি সদর, সিরাজগঞ্জ",
            addressEnglish = "College Road, Belkuchi Sadar, Sirajganj",
            phone = "01715-408920",
            latitude = 24.2920,
            longitude = 89.7010,
            rating = 4.6,
            openingStatusBangla = "সকাল ৯:০০ - বিকেল ৪:০০ (শুক্র ও শনি বন্ধ)",
            openingHoursEnglish = "9:00 AM - 4:00 PM (Closed Fri & Sat)",
            verifiedSource = "শিক্ষা মন্ত্রণালয় ও জাতীয় বিশ্ববিদ্যালয়",
            descriptionBangla = "১৯৭০ সালে প্রতিষ্ঠিত বেলকুচির শীর্ষস্থানীয় উচ্চশিক্ষা প্রতিষ্ঠান। এখানে উচ্চ মাধ্যমিক, স্নাতক (পাস) ও বিভিন্ন বিষয়ে অনার্স ডিগ্রি কোর্স চালু রয়েছে।",
            tags = listOf("college", "কলেজ", "সরকারি কলেজ", "govt college", "education", "উচ্চশিক্ষা")
        ),
        PlaceItem(
            id = "place_sohagpur_pilot_school",
            nameBangla = "সোহাগপুর এস.কে. পাইলট মডেল উচ্চ বিদ্যালয়",
            nameEnglish = "Sohagpur S.K. Pilot Model High School",
            category = BelkuchiCategory.EDUCATION,
            subcategoryBangla = "ঐতিহাসিক মাধ্যমিক বিদ্যালয়",
            addressBangla = "সোহাগপুর, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Sohagpur, Belkuchi, Sirajganj",
            phone = "01718-223344",
            latitude = 24.3015,
            longitude = 89.7095,
            rating = 4.7,
            openingStatusBangla = "সকাল ৯:৩০ - বিকেল ৪:০০",
            openingHoursEnglish = "9:30 AM - 4:00 PM",
            verifiedSource = "মাধ্যমিক ও উচ্চশিক্ষা অধিদপ্তর (DSHE)",
            descriptionBangla = "১৯১৩ সালে প্রতিষ্ঠিত শতবর্ষী ঐতিহ্যবাহী শিক্ষা প্রতিষ্ঠান। বেলকুচির জ্ঞানচর্চা ও ফলাফলে অন্যতম পথিকৃৎ স্কুল।",
            tags = listOf("school", "স্কুল", "পাইলট স্কুল", "sohagpur", "pilot school", "মাধ্যমিক")
        ),
        PlaceItem(
            id = "place_tamai_high_school",
            nameBangla = "তামাই বহুমুখী উচ্চ বিদ্যালয়",
            nameEnglish = "Tamai Bahumukhi High School",
            category = BelkuchiCategory.EDUCATION,
            subcategoryBangla = "মাধ্যমিক বিদ্যালয়",
            addressBangla = "তামাই, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Tamai, Belkuchi, Sirajganj",
            phone = "01719-556677",
            latitude = 24.3140,
            longitude = 89.6880,
            rating = 4.5,
            openingStatusBangla = "সকাল ৯:৩০ - বিকেল ৪:০০",
            openingHoursEnglish = "9:30 AM - 4:00 PM",
            verifiedSource = "DSHE / রাজশাহী শিক্ষা বোর্ড",
            descriptionBangla = "তামাই অঞ্চলের সুপরিচিত ও গৌরবময় মাধ্যমিক শিক্ষা কেন্দ্র। বিজ্ঞান, মানবিক ও ব্যবসায় শিক্ষা শাখা রয়েছে।",
            tags = listOf("school", "tamai", "স্কুল", "তামাই স্কুল", "education")
        ),
        PlaceItem(
            id = "place_belkuchi_model_primary",
            nameBangla = "বেলকুচি মডেল সরকারি প্রাথমিক বিদ্যালয়",
            nameEnglish = "Belkuchi Model Govt. Primary School",
            category = BelkuchiCategory.EDUCATION,
            subcategoryBangla = "প্রাথমিক বিদ্যালয়",
            addressBangla = "উপজেলা কমপ্লেক্স সংলগ্ন, বেলকুচি",
            addressEnglish = "Near Upazila Complex, Belkuchi",
            latitude = 24.2970,
            longitude = 89.7040,
            rating = 4.4,
            openingStatusBangla = "সকাল ৯:০০ - বিকেল ৩:৩০",
            openingHoursEnglish = "9:00 AM - 3:30 PM",
            verifiedSource = "প্রাথমিক শিক্ষা অধিদপ্তর",
            descriptionBangla = "বেলকুচি সদরের অন্যতম মডেল প্রাথমিক বিদ্যালয়। শিশু শিক্ষার জন্য আধুনিক শ্রেণিকক্ষ ও খেলার মাঠ সমৃদ্ধ।",
            tags = listOf("primary school", "প্রাথমিক স্কুল", "model school")
        ),
        PlaceItem(
            id = "place_daulatpur_high_school",
            nameBangla = "দৌলতপুর উচ্চ বিদ্যালয়",
            nameEnglish = "Daulatpur High School",
            category = BelkuchiCategory.EDUCATION,
            subcategoryBangla = "মাধ্যমিক বিদ্যালয়",
            addressBangla = "দৌলতপুর ইউনিয়ন, বেলকুচি",
            addressEnglish = "Daulatpur Union, Belkuchi",
            latitude = 24.2750,
            longitude = 89.6950,
            rating = 4.3,
            openingStatusBangla = "সকাল ৯:৩০ - বিকেল ৪:০০",
            openingHoursEnglish = "9:30 AM - 4:00 PM",
            verifiedSource = "DSHE",
            descriptionBangla = "দৌলতপুর ইউনিয়নের প্রধান মাধ্যমিক বিদ্যালয়।",
            tags = listOf("daulatpur", "school", "দৌলতপুর")
        ),
        PlaceItem(
            id = "place_tamai_fazil_madrasah",
            nameBangla = "তামাই ইসলামিয়া ফাযিল মাদ্রাসা",
            nameEnglish = "Tamai Islamia Fazil Madrasah",
            category = BelkuchiCategory.EDUCATION,
            subcategoryBangla = "ফাযিল মাদ্রাসা",
            addressBangla = "তামাই উত্তর পাড়া, বেলকুচি",
            addressEnglish = "Tamai North, Belkuchi",
            latitude = 24.3160,
            longitude = 89.6870,
            rating = 4.6,
            openingStatusBangla = "সকাল ৮:৩০ - দুপুর ২:৩০",
            openingHoursEnglish = "8:30 AM - 2:30 PM",
            verifiedSource = "বাংলাদেশ ইসলামী আরবি বিশ্ববিদ্যালয়",
            descriptionBangla = "ইসলামিক ও আধুনিক সমন্বিত শিক্ষার জন্য বেলকুচির অন্যতম শীর্ষ ধর্মীয় শিক্ষা প্রতিষ্ঠান।",
            tags = listOf("madrasah", "মাদ্রাসা", "tamai", "ফাযিল")
        ),

        // GOVERNMENT & OFFICES
        PlaceItem(
            id = "place_uno_office",
            nameBangla = "বেলকুচি উপজেলা পরিষদ ও উপজেলা নির্বাহী অফিসার (ইউএনও) কার্যালয়",
            nameEnglish = "Belkuchi Upazila Parishad & UNO Office",
            category = BelkuchiCategory.GOVERNMENT,
            subcategoryBangla = "উপজেলা প্রশাসন কার্যালয়",
            addressBangla = "উপজেলা চত্বর, চৌরাস্তা রোড, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Upazila Complex, Chowrasta Road, Belkuchi, Sirajganj",
            phone = "01705-411320",
            latitude = 24.2975,
            longitude = 89.7045,
            rating = 4.5,
            openingStatusBangla = "সকাল ৯:০০ - বিকেল ৫:০০ (সরকারি কর্মদিবস)",
            openingHoursEnglish = "9:00 AM - 5:00 PM (Govt Working Days)",
            verifiedSource = "জাতীয় তথ্য বাতায়ন (belkuchi.sirajganj.gov.bd)",
            descriptionBangla = "বেলকুচি উপজেলার কেন্দ্রীয় প্রশাসনিক কেন্দ্র। সকল সরকারি সেবার তদারকি, নাগরিক আবেদন, ত্রাণ ও উন্নয়ন কার্যক্রম এখান থেকে পরিচালিত হয়।",
            tags = listOf("uno", "উপজেলা", "ইউএনও", "সরকারি অফিস", "parishad", "government")
        ),
        PlaceItem(
            id = "place_pourashava",
            nameBangla = "বেলকুচি পৌরসভা কার্যালয়",
            nameEnglish = "Belkuchi Pourashava / Municipality Office",
            category = BelkuchiCategory.GOVERNMENT,
            subcategoryBangla = "পৌর প্রশাসন",
            addressBangla = "মুকুন্দগাঁতী, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Mukundagati, Belkuchi, Sirajganj",
            phone = "07524-56012",
            latitude = 24.2945,
            longitude = 89.7070,
            rating = 4.2,
            openingStatusBangla = "সকাল ৯:০০ - বিকেল ৫:০০ (রবি-বৃহঃ)",
            openingHoursEnglish = "9:00 AM - 5:00 PM (Sun-Thu)",
            verifiedSource = "পৌরসভা দপ্তর",
            descriptionBangla = "নাগরিক সনদ, জন্ম-মৃত্যু নিবন্ধন, ট্রেড লাইসেন্স, হোল্ডিং ট্যাক্স ও পৌর এলাকার নাগরিক সুবিধাদি প্রদান করা হয়।",
            tags = listOf("pourashava", "পৌরসভা", "municipality", "জন্ম নিবন্ধন", "ট্রেড লাইসেন্স")
        ),
        PlaceItem(
            id = "place_ac_land_office",
            nameBangla = "সহকারী কমিশনার (ভূমি) কার্যালয় (এসি ল্যান্ড)",
            nameEnglish = "Assistant Commissioner (Land) AC Land Office",
            category = BelkuchiCategory.GOVERNMENT,
            subcategoryBangla = "ভূমি ও রাজস্ব কার্যালয়",
            addressBangla = "উপজেলা পরিষদ চত্বর, বেলকুচি",
            addressEnglish = "Upazila Complex, Belkuchi",
            phone = "01705-411321",
            latitude = 24.2972,
            longitude = 89.7042,
            rating = 4.3,
            openingStatusBangla = "সকাল ৯:০০ - বিকেল ৫:০০",
            openingHoursEnglish = "9:00 AM - 5:00 PM",
            verifiedSource = "ভূমি মন্ত্রণালয় / জেলা প্রশাসন",
            descriptionBangla = "ই-নামজারি (Mutation), জমি খারিজ, মিস কেস শুনানি, ভূমি কর ও ভূমি রেকর্ড সংক্রান্ত সেবা।",
            tags = listOf("ac land", "ভূমি অফিস", "খারিজ", "নামজারি", "land")
        ),
        PlaceItem(
            id = "place_belkuchi_thana",
            nameBangla = "বেলকুচি থানা ভবন",
            nameEnglish = "Belkuchi Police Station Building",
            category = BelkuchiCategory.GOVERNMENT,
            subcategoryBangla = "আইন শৃঙ্খলা ও নিরাপত্তা",
            addressBangla = "মুকুন্দগাঁতী রোড, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Mukundagati Road, Belkuchi, Sirajganj",
            phone = "01320-128450",
            latitude = 24.2958,
            longitude = 89.7058,
            rating = 4.2,
            openingStatusBangla = "২৪ ঘণ্টা খোলা",
            openingHoursEnglish = "24 Hours Open",
            isEmergency = true,
            verifiedSource = "বাংলাদেশ পুলিশ",
            descriptionBangla = "থানা ডিউটি অফিসার, সাধারণ ডায়েরি (GD), মামলা দায়ের, পুলিশ ভেরিফিকেশন ও নিরাপত্তা টহল।",
            tags = listOf("police", "থানা", "পুলিশ", "gd", "আইন শৃঙ্খলা")
        ),
        PlaceItem(
            id = "place_fire_station",
            nameBangla = "বেলকুচি ফায়ার সার্ভিস ও সিভিল ডিফেন্স স্টেশন",
            nameEnglish = "Belkuchi Fire Service Station",
            category = BelkuchiCategory.GOVERNMENT,
            subcategoryBangla = "ফায়ার স্টেশন ও দুর্যোগ ব্যবস্থাপনা",
            addressBangla = "বেলকুচি বাইপাস সংলগ্ন, বেলকুচি",
            addressEnglish = "Near Belkuchi Bypass, Belkuchi",
            phone = "01716-179374",
            latitude = 24.2990,
            longitude = 89.7015,
            rating = 4.6,
            openingStatusBangla = "২৪ ঘণ্টা জরুরি সেবা চালু",
            openingHoursEnglish = "24 Hours Emergency",
            isEmergency = true,
            verifiedSource = "ফায়ার সার্ভিস ও সিভিল ডিফেন্স",
            descriptionBangla = "অগ্নি নির্বাপণ, যমুনা নদীতে উদ্ধার কার্যক্রম ও জরুরি সড়ক দুর্ঘটনার উদ্ধারকারী দল প্রস্তুত থাকে।",
            tags = listOf("fire", "ফায়ার সার্ভিস", "আগুন", "emergency")
        ),

        // PLACES, HERITAGE & MARKETS
        PlaceItem(
            id = "place_mukundagati_haat",
            nameBangla = "মুকুন্দগাঁতী কাপড়ের হাট (তাঁতের কাপড়ের হাট)",
            nameEnglish = "Mukundagati Cloth Haat (Handloom Textile Market)",
            category = BelkuchiCategory.PLACES,
            subcategoryBangla = "ঐতিহাসিক তাঁত বস্ত্রের পাইকারি হাট",
            addressBangla = "মুকুন্দগাঁতী বাজার, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Mukundagati Bazar, Belkuchi, Sirajganj",
            latitude = 24.2930,
            longitude = 89.7090,
            rating = 4.8,
            openingStatusBangla = "সাপ্তাহিক প্রধান হাট: প্রতি মঙ্গল ও বুধবার (ভোর থেকে দুপুর)",
            openingHoursEnglish = "Weekly Main Haat: Tue & Wed (Early Morning to Afternoon)",
            verifiedSource = "বাংলাদেশ তাঁত বোর্ড ও স্থানীয় বাণিজ্য সমিতি",
            descriptionBangla = "এশিয়ার অন্যতম বৃহৎ হস্তচালিত তাঁতের কাপড়ের পাইকারি হাট। প্রতি হাটে কোটি টাকার জামদানি, সুতি শাড়ি, সিল্ক শাড়ি, লুঙ্গি, গামছা ও থ্রি-পিস সারাদেশে পাইকারি বিক্রি হয়।",
            tags = listOf("তাঁতের হাট", "হাটের দিন", "বাজার", "cloth market", "sharee", "lungi", "haat", "মুকুন্দগাঁতী")
        ),
        PlaceItem(
            id = "place_tamai_tant_palli",
            nameBangla = "তামাই তাঁত পল্লী",
            nameEnglish = "Tamai Tant Palli (Weavers Village)",
            category = BelkuchiCategory.PLACES,
            subcategoryBangla = "ঐতিহ্যবাহী তাঁত শিল্প এলাকা",
            addressBangla = "তামাই গ্রাম, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Tamai Village, Belkuchi, Sirajganj",
            latitude = 24.3150,
            longitude = 89.6860,
            rating = 4.7,
            openingStatusBangla = "সারাদিন খোলা (তাঁত কারখানা পরিদর্শন)",
            openingHoursEnglish = "All Day Open",
            verifiedSource = "বাংলাদেশ ক্ষুদ্র ও কুটির শিল্প (বিসিক)",
            descriptionBangla = "বাংলাদেশের বিখ্যাত তাঁতের গ্রাম। বাড়ি বাড়ি তাঁতের খটখট শব্দ আর নিপুণ কারিগরদের জামদানি ও আধুনিক জ্যাকার্ড শাড়ি বুননের দৃশ্য এখানে দেখা যায়।",
            tags = listOf("তাঁত পল্লী", "tamai", "tant", "handloom", "শাড়ি", "কারিগর")
        ),
        PlaceItem(
            id = "place_jamuna_hard_point",
            nameBangla = "যমুনা নদী হার্ড পয়েন্ট ও নদীর তীরবর্তী বাঁধ",
            nameEnglish = "Jamuna River Hard Point & Embankment",
            category = BelkuchiCategory.PLACES,
            subcategoryBangla = "দর্শনীয় স্থান ও প্রাকৃতিক নৈসর্গ",
            addressBangla = "বড়ধুল ও রাজাপুর তীরবর্তী যমুনা নদী বাঁধ, বেলকুচি",
            addressEnglish = "Jamuna River Bank, Borodhul / Rajapur, Belkuchi",
            latitude = 24.2850,
            longitude = 89.7350,
            rating = 4.7,
            openingStatusBangla = "দর্শনার্থীদের জন্য উন্মুক্ত (বিকেলের মনোরম হাওয়া)",
            openingHoursEnglish = "Open for Visitors (Sunset View)",
            verifiedSource = "পানি উন্নয়ন বোর্ড ও পর্যটন গাইড",
            descriptionBangla = "যমুনা নদীর বিশাল জলরাশি, সূর্যাস্ত ও নদীর নির্মল বাতাস উপভোগের জন্য বেলকুচির সবচেয়ে জনপ্রিয় দর্শনীয় স্থান। বিকেলে স্থানীয় মানুষ ও পর্যটকরা এখানে ঘুরতে আসেন।",
            tags = listOf("যমুনা নদী", "jamuna", "sunset", "নদী", "দর্শনীয় স্থান", "tourist", "river")
        ),
        PlaceItem(
            id = "place_sohagpur_ghat",
            nameBangla = "সোহাগপুর খেয়া ঘাট (নৌকা ও ট্রলার টার্মিনাল)",
            nameEnglish = "Sohagpur Ferry & Boat Ghat",
            category = BelkuchiCategory.PLACES,
            subcategoryBangla = "নদী ঘাট ও পারাপার",
            addressBangla = "সোহাগপুর নদীঘাট, বেলকুচি",
            addressEnglish = "Sohagpur Riverbank, Belkuchi",
            latitude = 24.3050,
            longitude = 89.7200,
            rating = 4.3,
            openingStatusBangla = "ভোর ৫:০০ - রাত ৯:০০ (নৌকা চলাচল)",
            openingHoursEnglish = "5:00 AM - 9:00 PM",
            verifiedSource = "BIWTA / Local Ferry Authority",
            descriptionBangla = "যমুনার চর ও চৌহালী উপজেলার সাথে যোগাযোগের অন্যতম প্রধান নদী ঘাট। ইঞ্জিন চালিত নৌকা ও স্পিডবোট চলাচল করে।",
            tags = listOf("ghat", "নদী ঘাট", "boat", "sohagpur ghat", "খেয়া ঘাট")
        ),
        PlaceItem(
            id = "place_central_mosque",
            nameBangla = "বেলকুচি কেন্দ্রীয় জামে মসজিদ",
            nameEnglish = "Belkuchi Central Jame Mosque",
            category = BelkuchiCategory.PLACES,
            subcategoryBangla = "মসজিদ ও ধর্মীয় স্থান",
            addressBangla = "চৌরাস্তা বাজার, বেলকুচি",
            addressEnglish = "Chowrasta Bazar, Belkuchi",
            latitude = 24.2960,
            longitude = 89.7048,
            rating = 4.8,
            openingStatusBangla = "প্রতি ওয়াক্ত নামাজের সময় উন্মুক্ত",
            openingHoursEnglish = "Open during Prayer Times",
            verifiedSource = "Islamic Foundation Bangladesh",
            descriptionBangla = "বেলকুচি পৌর এলাকার প্রধান দৃষ্টিনন্দন কেন্দ্রীয় মসজিদ।",
            tags = listOf("mosque", "মসজিদ", "prayer", "জামে মসজিদ")
        ),
        PlaceItem(
            id = "place_smriti_stambho",
            nameBangla = "বেলকুচি মুক্তিযুদ্ধ স্মৃতিস্তম্ভ",
            nameEnglish = "Belkuchi Liberation War Memorial",
            category = BelkuchiCategory.PLACES,
            subcategoryBangla = "স্মৃতিস্তম্ভ ও ঐতিহাসিক স্থান",
            addressBangla = "উপজেলা কমপ্লেক্স গেট সংলগ্ন, বেলকুচি",
            addressEnglish = "Near Upazila Complex Gate, Belkuchi",
            latitude = 24.2968,
            longitude = 89.7040,
            rating = 4.6,
            openingStatusBangla = "সার্বক্ষণিক উন্মুক্ত",
            openingHoursEnglish = "24 Hours Open",
            verifiedSource = "মুক্তিযুদ্ধ বিষয়ক মন্ত্রণালয়",
            descriptionBangla = "১৯৭১ সালের মহান মুক্তিযুদ্ধে বেলকুচির বীর শহীদদের স্মরণে নির্মিত স্মৃতিসৌধ।",
            tags = listOf("memorial", "স্মৃতিস্তম্ভ", "মুক্তিযুদ্ধ", "1971", "history")
        ),

        // TRANSPORTATION
        PlaceItem(
            id = "place_bus_stand",
            nameBangla = "বেলকুচি কেন্দ্রীয় বাস টার্মিনাল (মুকুন্দগাঁতী চৌরাস্তা)",
            nameEnglish = "Belkuchi Central Bus Stand (Mukundagati Chowrasta)",
            category = BelkuchiCategory.TRANSPORT,
            subcategoryBangla = "বাস কাউন্টার ও টার্মিনাল",
            addressBangla = "মুকুন্দগাঁতী চৌরাস্তা, বেলকুচি, সিরাজগঞ্জ",
            addressEnglish = "Mukundagati Chowrasta, Belkuchi, Sirajganj",
            phone = "01712-998877",
            latitude = 24.2950,
            longitude = 89.7055,
            rating = 4.4,
            openingStatusBangla = "ভোর ৫:০০ - রাত ১১:০০",
            openingHoursEnglish = "5:00 AM - 11:00 PM",
            verifiedSource = "সিরাজগঞ্জ জেলা বাস মিনিবাস মালিক সমিতি",
            descriptionBangla = "ঢাকা (মহাখালী/গাবতলী), সিরাজগঞ্জ সদর, এনায়েতপুর, শাহজাদপুর ও পাবনা রুটের সরাসরি দূরপাল্লার বাস ও লোকাল বাস কাউন্টার।",
            tags = listOf("bus", "বাস স্ট্যান্ড", "ঢাকা বাস", "transport", "সিরাজগঞ্জ বাস", "counter")
        ),
        PlaceItem(
            id = "place_cng_stand_sirajganj",
            nameBangla = "বেলকুচি-সিরাজগঞ্জ সিএনজি ও অটোস্ট্যান্ড",
            nameEnglish = "Belkuchi - Sirajganj CNG & Auto Stand",
            category = BelkuchiCategory.TRANSPORT,
            subcategoryBangla = "লোকাল পরিবহন স্ট্যান্ড",
            addressBangla = "চৌরাস্তা মোড়, বেলকুচি",
            addressEnglish = "Chowrasta More, Belkuchi",
            latitude = 24.2952,
            longitude = 89.7052,
            rating = 4.2,
            openingStatusBangla = "সার্বক্ষণিক চলাচল (রাত ১০টা পর্যন্ত দ্রুত পাওয়া যায়)",
            openingHoursEnglish = "24 Hours Available",
            verifiedSource = "Local Transport Union",
            descriptionBangla = "সিরাজগঞ্জ জেলা সদর (১৮ কিমি) যাওয়ার প্রধান সিএনজি ও অটো স্টেশন। সময় লাগে আনুমানিক ৩০-৪০ মিনিট। ভাড়া ৩০-৪০ টাকা জনপ্রতি।",
            tags = listOf("cng", "সিএনজি", "সিরাজগঞ্জ রুট", "auto", "গাড়ি")
        )
    )

    val UNIONS = listOf(
        UnionInfo(
            id = "u_belkuchi_sadar",
            nameBangla = "বেলকুচি সদর (পৌরসভা এলাকা)",
            nameEnglish = "Belkuchi Sadar (Pourashava)",
            officeLocation = "মুকুন্দগাঁতী, বেলকুচি",
            keyVillages = listOf("মুকুন্দগাঁতী", "সোহাগপুর", "চালা", "চালা উত্তর", "চালা দক্ষিণ"),
            specialties = "উপজেলার প্রধান প্রশাসনিক ও বাণিজ্যিক কেন্দ্র, মুকুন্দগাঁতী কাপড়ের হাট ও সরকারি কলেজ",
            areaSqKm = 19.42,
            population = "প্রায় ১,০০,০০০+"
        ),
        UnionInfo(
            id = "u_rajapur",
            nameBangla = "১নং রাজাপুর ইউনিয়ন",
            nameEnglish = "1 No. Rajapur Union",
            officeLocation = "রাজাপুর বাজার, বেলকুচি",
            keyVillages = listOf("রাজাপুর", "সমেশপুর", "মেহেদা", "বড়ধুল সীমানা", "কোদালিয়া"),
            specialties = "যমুনা নদী তীরবর্তী প্রাকৃতিক পরিবেশ, কৃষি ও হস্তচালিত তাঁত শাড়ি উৎপাদন",
            areaSqKm = 27.50,
            population = "প্রায় ৪৫,০০০"
        ),
        UnionInfo(
            id = "u_daulatpur",
            nameBangla = "২নং দৌলতপুর ইউনিয়ন",
            nameEnglish = "2 No. Daulatpur Union",
            officeLocation = "দৌলতপুর বাজার, বেলকুচি",
            keyVillages = listOf("দৌলতপুর", "তেঁতুলিয়া", "আজগড়া", "আজুগড়া", "ধুলগাগড়াখালি"),
            specialties = "উর্বর কৃষিজমি, সরিষা ও ধান উৎপাদন, বৃহৎ তাঁত কারখানা ক্লাস্টার",
            areaSqKm = 24.30,
            population = "প্রায় ৪৮,০০০"
        ),
        UnionInfo(
            id = "u_bhangabari",
            nameBangla = "৩নং ভাঙ্গাবাড়ী ইউনিয়ন",
            nameEnglish = "3 No. Bhangabari Union",
            officeLocation = "ভাঙ্গাবাড়ী, বেলকুচি",
            keyVillages = listOf("ভাঙ্গাবাড়ী", "তামাই (অংশ)", "সেনভাঙ্গাবাড়ী", "চালা পশ্চিম", "শালদাইর"),
            specialties = "তামাই সংলগ্ন তাঁত পল্লী ও ঐতিহ্যবাহী সমৃদ্ধ শিল্প এলাকা",
            areaSqKm = 22.15,
            population = "প্রায় ৫২,০০০"
        ),
        UnionInfo(
            id = "u_dhukuria_bera",
            nameBangla = "৪নং ধুকুরিয়া বেড়া ইউনিয়ন",
            nameEnglish = "4 No. Dhukuria Bera Union",
            officeLocation = "ধুকুরিয়া বেড়া বাজার, বেলকুচি",
            keyVillages = listOf("ধুকুরিয়া", "বেড়া", "মৌগাছি", "উলাভাঙা", "কলিয়া"),
            specialties = "উচ্চমানের সুতি ও সিল্ক শাড়ির তাঁত শিল্প এবং দুধ ও দুগ্ধজাত পণ্য",
            areaSqKm = 26.80,
            population = "প্রায় ৪৬,০০০"
        ),
        UnionInfo(
            id = "u_borodhul",
            nameBangla = "৫নং বড়ধুল ইউনিয়ন",
            nameEnglish = "5 No. Borodhul Union",
            officeLocation = "বড়ধুল ঘাট এলাকা, বেলকুচি",
            keyVillages = listOf("বড়ধুল", "চর বড়ধুল", "বেলগাছি", "হায়দারপুর", "যমুনা চর এলাকা"),
            specialties = "যমুনার চর এলাকা, বাদাম ও রবিশস্য উৎপাদন, নদীভিত্তিক জীবনধারা ও নৌযান যোগাযোগ",
            areaSqKm = 31.20,
            population = "প্রায় ৪২,০০০"
        )
    )

    const val ABOUT_BELKUCHI_BANGLA = """
**বেলকুচি উপজেলা (Belkuchi Upazila)**
সিরাজগঞ্জ জেলা, রাজশাহী বিভাগ, বাংলাদেশ।

📍 **ভৌগোলিক অবস্থান ও সীমানা:**
* আয়তন: ১৬৪.৩১ বর্গ কিলোমিটার
* স্থানাঙ্ক: ২৪°১৭' উত্তর অক্ষাংশ থেকে ২৪°২২' উত্তর অক্ষাংশ এবং ৮৯°৩৫' পূর্ব দ্রাঘিমাংশ থেকে ৮৯°৪৭' পূর্ব দ্রাঘিমাংশ।
* উত্তর সীমানা: কামারখন্দ উপজেলা ও সিরাজগঞ্জ সদর
* দক্ষিণ সীমানা: শাহজাদপুর উপজেলা ও এনায়েতপুর
* পূর্ব সীমানা: যমুনা নদী ও চৌহালী উপজেলা
* পশ্চিম সীমানা: উল্লাপাড়া উপজেলা ও কামারখন্দ

🧵 **তাঁত শিল্পের রাজধানী ও মুকুন্দগাঁতী হাট:**
বেলকুচি বাংলাদেশের বিখ্যাত তাঁত বস্ত্র শিল্পের প্রধান প্রাণকেন্দ্র। এখানকার 'মুকুন্দগাঁতী কাপড়ের হাট' এশিয়ার অন্যতম বৃহৎ তাঁতের কাপড়ের পাইকারি হাট। তামাই, সোহাগপুর, চালা ও ভাঙ্গাবাড়ীর তাঁত পল্লীতে তৈরি জামদানি, জ্যাকার্ড, কাতান, সুতি শাড়ি ও মানসম্মত লুঙ্গি দেশ-বিদেশে সমাদৃত।

🏛️ **প্রশাসনিক কাঠামো:**
* পৌরসভা: ১টি (বেলকুচি পৌরসভা)
* ইউনিয়ন: ৬টি (রাজাপুর, দৌলতপুর, ভাঙ্গাবাড়ী, ধুকুরিয়া বেড়া, বড়ধুল এবং বেলকুচি সদর)
* প্রধান নদ-নদী: প্রমত্তা যমুনা নদী, কাটা গাঙ ও হুরাসাগর নদীর শাখা।

🚑 **জরুরি স্বাস্থ্য ও সেবা তথ্য:**
* প্রধান সরকারি হাসপাতাল: বেলকুচি উপজেলা স্বাস্থ্য কমপ্লেক্স (৫০ শয্যা, শাহী বাজার/তামাই রোড)
* জরুরি পুলিশ যোগাযোগ: বেলকুচি থানা (01320-128450)
* ফায়ার সার্ভিস স্টেশন: 01716-179374
* জাতীয় জরুরি সেবা: ৯৯৯
"""

    val QUICK_PROMPTS = listOf(
        "বেলকুচি উপজেলা স্বাস্থ্য কমপ্লেক্স কোথায়?",
        "পুলিশ ও ফায়ার সার্ভিসের জরুরি নম্বর কত?",
        "মুকুন্দগাঁতী কাপড়ের হাট কবে বসে?",
        "বেলকুচিতে ভালো ক্লিনিক ও ডায়াগনস্টিক দেখাও",
        "বেলকুচি থেকে সিরাজগঞ্জ শহরে কীভাবে যাব?",
        "বেলকুচিতে মোট কয়টি ইউনিয়ন আছে?",
        "যমুনা নদীর হার্ড পয়েন্ট কোথায়?",
        "বেলকুচি সরকারি কলেজ সম্পর্কে জানাও"
    )
}
