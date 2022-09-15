package com.diyabet.diyabetgunlugum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealTableItemData(
    var meal : String
):Parcelable