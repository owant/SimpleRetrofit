package com.example.retrofit.http

import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL


/**
 * Author: owant
 * Date: 7/21/21 2:10 PM
 * Description: 网络请求
 * History:
 * <author> <time> <version> <desc>
 * owant 7/21/21 1.0 首次创建
 */

class HttpGetRequest<T>(private val url: String) {

    fun request(): Response<T> {
        val urlEntity = URL(url);
        val httpURLConnection = urlEntity.openConnection() as HttpURLConnection
        httpURLConnection.connectTimeout = 1200
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.connect()

        val contextBuffer = StringBuffer()
        if (httpURLConnection.responseCode == 200) {
            val buffer = ByteArray(1024)
            var r: Int = -1
            while (httpURLConnection.inputStream.read(buffer).also { r = it } != -1) {
                contextBuffer.append(String(buffer, 0, r))
            }
            val body = contextBuffer.toString()
            return Response(200, body)
        }
        return Response(httpURLConnection.responseCode, null)
    }
}