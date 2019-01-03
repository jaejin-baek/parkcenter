package com.apollo.android.cleanarchitecture.util

import android.Manifest
import android.content.Context
import android.content.CursorLoader
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import java.util.regex.Pattern

object ImageUtils {
    private val TAG = ImageUtils::class.java.simpleName
    private val PATTERN_GIF = Pattern.compile("[^?#]*\\.gif")

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Throws(SecurityException::class)
    fun getPathFromUri(context: Context, uri: Uri): String {
        var filePath = ""
        if (DocumentsContract.isDocumentUri(context, uri)) {
            val wholeID = DocumentsContract.getDocumentId(uri)
            // Split at colon, use second item in the array
            val splits = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (splits.size == 2) {
                val id = splits[1]

                val column = arrayOf(MediaStore.Images.Media.DATA)
                // where id is equal to
                val sel = MediaStore.Images.Media._ID + "=?"
                val cursor = context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, arrayOf(id), null
                )
                val columnIndex = cursor!!.getColumnIndex(column[0])
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex)
                }
                cursor.close()
            }
        } else {
            filePath = uri.path
        }
        return filePath
    }

    private fun getPathFromUriUnderApiLv19(context: Context, contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        var result: String? = null
        val cursorLoader = CursorLoader(
            context,
            contentUri, proj, null, null, null
        )
        val cursor = cursorLoader.loadInBackground()
        if (cursor != null) {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            result = cursor.getString(columnIndex)
        }
        return result
    }

    fun getPathFromUriUnderApiLv11(context: Context, contentUri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(contentUri, proj, null, null, null)
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }

    @RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    @Throws(SecurityException::class)
    fun getAbsoulutPathFromUri(context: Context, uri: Uri): String? {
        var path =
            getPathFromUriUnderApiLv19(
                context,
                uri
            )
        path?.let {
            if (path!!.isEmpty() && Build.VERSION.SDK_INT > 19) {
                path =
                        getPathFromUri(
                            context,
                            uri
                        )
            }
        }

        return path
    }

    fun isGifUrl(url: String?): Boolean {
        return PATTERN_GIF.matcher(url).find()
    }

    fun removeThumbnailTypeFromGifUrl(url: String?): String? {
        val matcher = PATTERN_GIF.matcher(url)
        return if (matcher.find()) {
            matcher.group()
        } else {
            url
        }
    }
}
