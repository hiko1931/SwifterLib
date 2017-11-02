package library.moyoung.com.swifterlib.HTMLTrans

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_printactivity.*
import library.moyoung.com.swifterlib.Log.RootApp

/**
 * 레이아웃에서 렌더링 에러 출력하는 경우가 있는데
 * Style에서  <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar"></style> 을
 * <style name="AppTheme" parent="Base.Theme.AppCompat.Light.DarkActionBar"></style> 로 교체해주면 된다.
 * 참고 url : https://stackoverflow.com/questions/44223687/render-error-in-android-studio-3-0-layout-editor
 * Created by Swifter on 2017-11-02.
 */

class PrintActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(activity_provision_view) //kotlin extension

        RootApp.getLogger()!!.d("PrintActiviy 클래스 접근")

//        val title: TextView = findViewById(타이틀아이디)
//        title.setText(intent.getStringExtra("title"))

        //아래는 HTML 태그 긁어와서 뿌려주는 방식
        val idx = intent.getStringExtra("index")

        webProvision.settings.javaScriptEnabled = true
        webProvision.loadData(idx,"text/html","UTF-8")

        //그리고 이번엔 URL 뿌려주는 방식
        val url = "http://주소입력.co.kr"

        webProvision.webViewClient = WebViewClient()
        val webSetting : WebSettings = webProvision.settings
        webProvision.loadUrl(url)
    }

}