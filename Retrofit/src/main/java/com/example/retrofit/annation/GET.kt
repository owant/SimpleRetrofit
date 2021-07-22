package com.example.retrofit.annation

/**
 * Author: owant
 * Date: 7/21/21 11:31 AM
 * Description: get请求
 * History:
 * <author> <time> <version> <desc>
 * owant 7/21/21 1.0 首次创建
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class GET(
    val url: String
)
