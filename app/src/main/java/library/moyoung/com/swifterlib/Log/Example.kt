package library.moyoung.com.swifterlib.Log

import android.content.res.AssetManager
import android.view.View
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * 사용방법 :
 * 1. 빌드 그래들의 allprojects 영역에 maven { url "https://jitpack.io" } 추가
 * 2. 해당 모듈 그래들 dependencies 에 compile 'com.github.JiongBull:jlog:0.1.0' 추가
 * 3. Application 영역을 상속받은 클래스를 만들어주고 거기에다 logger를 빌드해준다. RootApp.kt 참고
 * 4. AndroidManifest에 방금 만든 클래스를 알려준다.
 * 5. 로그를 찍고 싶은 곳에다 아래와 같은 방법으로 호출하면 된다.
 * Created by Swifter on 2017-10-30.
 */

/**
 * 일반 로그 사용 예시
 */
private fun logNormal() {
    val defaultTag = "Default tag"
    RootApp.getLogger()!!.v(defaultTag)
    RootApp.getLogger()!!.d(defaultTag)
    RootApp.getLogger()!!.i(defaultTag)
    RootApp.getLogger()!!.w(defaultTag)
    RootApp.getLogger()!!.e(defaultTag)
    RootApp.getLogger()!!.wtf(defaultTag)
}

/**
 * 태그 들어간 로그 사용 예시
 */
private fun logTag() {
    val tag = "LOG_WITH_TAG"
    RootApp.getLogger()!!.v(tag, "Custom tag")
    RootApp.getLogger()!!.d(tag, "Custom tag")
    RootApp.getLogger()!!.i(tag, "Custom tag")
    RootApp.getLogger()!!.w(tag, "Custom tag")
    RootApp.getLogger()!!.e(tag, "Custom tag")
    RootApp.getLogger()!!.wtf(tag, "Custom tag")
}

/**
 * 로그 파일 출력 사용 예시
 */
fun logMoreThan4000(v:View) {

    val am:AssetManager = v.context.assets //자바에서는 그냥 getAssets 지원하지만 코틀린은 콘텍스트 영역을 명시해줘야함.

    try {
        val `is` = am.open("4000.txt")
        val br = BufferedReader(InputStreamReader(`is`))
        var line: String = ""
        var content = ""
        //자바와 달리 할당하는 것 (line = br.readLine()) 은 코틀린의 표현이 X
        //사이드 이펙트가 있을 수 있는 방식은 권장안함  (함수형 프로그래밍)
        //고로 아래의 while문의 조건식은 주석처리하고 다른 방식으로 대체한다.
//        while ((line = br.readLine()) != null) {
        while(br.readLine().let { line = it; it != null }){ //while(br.readLine().let { line = br.readLine(); br.readLine() != null })
            content += line
        }

        RootApp_Java.getLogger().i(content)
        br.close()
        `is`.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

/**
 * 로그 제이슨 사용 예시
 */
fun logJson() {
    //아래는 JSON형식대로 일일이 입력했지만 걍 json 형식의 객체 넣으면 됨
    RootApp.getLogger()!!.json(
            "{\"location\":{\"id\":\"C23NB62W20TF\",\"name\":\"西雅图\",\"country\":\"US\","
                    + "\"path\":\"西雅图,华盛顿州,美国\",\"timezone\":\"America/Los_Angeles\","
                    + "\"timezone_offset\":\"-08:00\"}}")
}