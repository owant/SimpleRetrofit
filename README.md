# SimpleRetrofit
简易版的注解示范和动态代理的retrofit

```
package com.example.retrofit

import com.example.retrofit.annation.GET
import com.example.retrofit.annation.PATH
import com.example.retrofit.http.HttpGetRequest
import com.example.retrofit.main.SimpleRetrofit

class MainApp {

    interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        fun contributors(
            @PATH("owner") owner: String?,
            @PATH("repo") repo: String?
        ): HttpGetRequest<List<Contributor>>
    }

    companion object {

        const val API_URL = "https://api.github.com"

        @JvmStatic
        fun main(args: Array<String>) {
            val simpleRetrofit = SimpleRetrofit()
            simpleRetrofit.baseURL = API_URL
            val gitHub = simpleRetrofit.create(GitHub::class.java)
            val contributors = gitHub.contributors("owant", "ThinkMap")
            val baseResponse = contributors.request()
            println(baseResponse.body)
        }
    }
}

```
