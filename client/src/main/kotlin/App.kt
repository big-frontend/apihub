package com.electrolytej.vi.app

import com.electrolytej.network.api.GrpcSignInServiceClient
import com.electrolytej.network.model.SignInReq
import com.electrolytej.vi.utils.Printer
import com.squareup.wire.GrpcClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Protocol

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    val message = "Hello, " + name + "!"
    val printer = Printer(message)
    printer.printMessage()

    for (i in 1..5) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        println("i = $i")
    }
    GlobalScope.launch {
        val serverUrl = ""
        val grpcClient =
            GrpcClient.Builder()
                .client(
                    OkHttpClient.Builder().protocols(listOf(Protocol.H2_PRIOR_KNOWLEDGE))
                        .build()
                )
                .baseUrl(serverUrl).build()
        val client = GrpcSignInServiceClient(grpcClient)
        val request = SignInReq()
        client.signin().execute(request)
    }

}
