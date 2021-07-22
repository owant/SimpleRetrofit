package com.example.retrofit.main

import com.example.retrofit.annation.GET
import com.example.retrofit.annation.PATH
import com.example.retrofit.http.HttpGetRequest
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Author: owant
 * Date: 7/21/21 3:35 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * owant 7/21/21 1.0 首次创建
</desc></version></time></author> */
class SimpleRetrofit {

    var baseURL = ""

    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(service.classLoader, arrayOf<Class<*>>(service),
            object : InvocationHandler {

                /**
                 * 代理对象
                 * 函数
                 * 输入
                 * 返回值
                 */
                @Throws(Throwable::class)
                override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {
                    //构造方法直接回去
                    if (method.declaringClass == Any::class.java) {
                        return method.invoke(this, args)
                    }

                    var path = ""
                    val annotation = method.getAnnotation(GET::class.java)
                    if (annotation != null) {
                        var urlFormat = annotation.url
                        for (parameter in method.parameters) {
                            val pathValue = parameter.getAnnotation(PATH::class.java)
                            urlFormat = urlFormat.replace("{" + pathValue.input + "}", "%s")
                        }
                        path = String.format(urlFormat, *args)
                        return HttpGetRequest<Any>(baseURL + path);
                    }
                    return null
                }

            }) as T
    }
}