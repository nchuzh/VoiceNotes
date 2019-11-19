package com.example.voicenotes.network.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(@SerializedName("api_dev_key") val devKey: String,
                        @SerializedName("api_user_name") val userName: String,
                        @SerializedName("api_user_password") val password: String)