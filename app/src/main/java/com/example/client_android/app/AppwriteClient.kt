package com.example.client_android.app

import android.content.Context
import io.appwrite.Client
import io.appwrite.services.Account
import io.appwrite.services.Databases

class AppwriteClient(context: Context) {
    private val client: Client
    private val account: Account
    private val database: Databases

    init {
        client = Client(context).setEndpoint("http://192.168.0.12/v1")
            .setProject("64630ca891aa41432929")
            .setSelfSigned(true)

        account = Account(client)
        database = Databases(client)
    }
}