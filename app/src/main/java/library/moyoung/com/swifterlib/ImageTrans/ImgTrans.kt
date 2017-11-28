package library.moyoung.com.swifterlib.ImageTrans

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

/**
 * Created by Swifter on 2017-11-28.
 */

class ImgTrans{

    companion object {

        /**
         * 간단한 (저용량)의 이미지를 인텐트로 넘긴다거나 할때의 용도 비트맵 -> 바이트배열로 변환
         */
        fun simpleBitmapToByteArray(bitmap : Bitmap): ByteArray {

            val stream: ByteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val bytes: ByteArray = stream.toByteArray()

            return bytes
        }

        /**
         * 이미지 파일의 크기가 큰 경우에 사용하는 메소드 사이즈를 정해서 리사이징
         * @param imagaPath  이미지 경로
         * @param targetWidth  리사이징할 너비
         * @param targetHeight  리사이징할 높이
         */
        fun imageSamplingNReturn(imagePath : String, targetWidth : Int, targetHeight: Int):Bitmap{

            //비트맵을 가져온다.
            val bmOption : BitmapFactory.Options = BitmapFactory.Options()
            bmOption.inJustDecodeBounds = true
            BitmapFactory.decodeFile(imagePath, bmOption)

            val photoWidth : Int = bmOption.outWidth
            val photoHeight : Int = bmOption.outHeight

            var resultHeight : Int = 0

            if (targetHeight <= 0){
                resultHeight = (targetWidth * photoHeight) / photoWidth
            }


            var scaleFactor:Int = 1
            if (photoWidth > targetWidth){
                scaleFactor = Math.min(photoWidth / targetWidth, photoHeight / targetHeight)
            }

            bmOption.inJustDecodeBounds = false
            bmOption.inSampleSize = scaleFactor

            return BitmapFactory.decodeFile(imagePath, bmOption)
        }

        /**
         * 이미지를 인텐트 등으로 받아오고 나서 다시 비트맵으로 변환하는 메소드
         */
        fun simpleByteArraytoBitmap(byteArray: ByteArray): Bitmap{

            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }

    }
}