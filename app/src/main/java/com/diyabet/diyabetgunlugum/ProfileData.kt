package com.diyabet.diyabetgunlugum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileData (
    var profile_name : String,
    var profile_surname : String,
    var profile_number : String,
    var profile_mail : String,
    var profile_date : String,
    var profile_gender : String,
    var profile_height : String,
    var profile_weight : String,
): Parcelable