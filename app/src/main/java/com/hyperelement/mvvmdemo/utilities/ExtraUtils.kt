package com.hyperelement.mvvmdemo.utilities

import android.content.Context
import java.io.IOException
import java.io.InputStream


object ExtraUtils {
    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        val jsonString: String
        jsonString = try {
            val mInputStream: InputStream = context.assets.open(fileName)
            val size: Int = mInputStream.available()
            val buffer = ByteArray(size)
            mInputStream.read(buffer)
            mInputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}