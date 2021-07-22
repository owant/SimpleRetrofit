package com.example.retrofit.http

/**
 * Author: owant
 * Date: 7/21/21 3:11 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * owant 7/21/21 1.0 首次创建
 */

data class Response<T>(
    var code: Int,
    var body: String?
)