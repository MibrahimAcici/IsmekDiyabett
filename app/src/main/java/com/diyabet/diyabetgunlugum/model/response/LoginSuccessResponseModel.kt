package com.diyabet.diyabetgunlugum.model.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginSuccessResponseModel(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("status")
    var status: String? = null
) : Parcelable