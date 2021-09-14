package com.example.mlkit_pose

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.lang.Exception

class convertBitMap {
    fun BitmapToString(bitmap: Bitmap): String {
        val baos =
            ByteArrayOutputStream() //바이트 배열을 차례대로 읽어 들이기위한 ByteArrayOutputStream클래스 선언
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos) //bitmap을 압축 (숫자 70은 70%로 압축한다는 뜻)
        val bytes = baos.toByteArray() //해당 bitmap을 byte배열로 바꿔준다.
        return Base64.encodeToString(bytes, Base64.DEFAULT) //Base 64 방식으로byte 배열을 String으로 변환
        //String을 retrurn
    }
    fun StringToBitmap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(
                encodedString,
                Base64.DEFAULT
            ) // String 화 된 이미지를  base64방식으로 인코딩하여 byte배열을 만듬
            BitmapFactory.decodeByteArray(
                encodeByte,
                0,
                encodeByte.size
            ) //byte배열을 bitmapfactory 메소드를 이용하여 비트맵으로 바꿔준다.
            //만들어진 bitmap을 return
        } catch (e: Exception) {
            e.message
            null
        }
    }
}