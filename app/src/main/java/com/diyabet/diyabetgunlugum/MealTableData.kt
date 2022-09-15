package com.diyabet.diyabetgunlugum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealTableData(
    var date : String,
    var time : String,
    var mealList : ArrayList<MealTableItemData>
):Parcelable