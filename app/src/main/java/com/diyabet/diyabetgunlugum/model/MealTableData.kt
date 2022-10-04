package com.diyabet.diyabetgunlugum.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealTableData(
    var date : String,
    var time : String,
    var mealList : ArrayList<MealTableItemData>
):Parcelable