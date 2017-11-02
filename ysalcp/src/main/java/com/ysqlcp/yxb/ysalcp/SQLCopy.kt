package com.ysqlcp.yxb.ysalcp

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun copySQL(dbName: String, dbFileId: Int, context: Context): Boolean {
    val packageName = context.packageName
    val dirPath = "/data/data/$packageName/databases/"
    val dir = File(dirPath)
    if (!dir.exists())
        dir.mkdirs()
    else if (!dir.isDirectory)
        return false
    val sqlPath = dirPath + dbName
    val file = File(sqlPath)
    if (file.exists()) return false
    val outStream = FileOutputStream(file.absolutePath)
    val inStream = context.resources.openRawResource(dbFileId)
    val buffer = ByteArray(8192)
    var count = 0
    try {
        count = inStream.read(buffer)
        while (count > 0) {
            count = inStream.read(buffer)
            outStream.write(buffer, 0, count)
            outStream.flush()
        }
    } catch (e: IOException) {
        return false
    } finally {
        try {
            inStream.close()
            outStream.close()
        } catch (e: IOException) {
            //e.printStackTrace()
            return false
        }
    }
    return true
}