package library.moyoung.com.swifterlib.HTMLTrans

import android.app.Activity
import android.content.Intent
import library.moyoung.com.swifterlib.Log.RootApp

/**
 * 이 디렉토리는 JSON 통신후 JSP로 반환받고 띄워주기 위한 용도 또한 기타 웹뷰 방식 역시 적어둠.
 *
 * 일단 JSON으로 정보를 요청하고 레트로핏을 통해 JSONMessage로 JSP 자체를 받게 된다면...
 * (방식은 URL 입력하고 직접연결하는 웹뷰 방식이 있다. 혹은 이미지가 없다면
 * 텍스트 뷰에 텍스트뷰.setText(Html.fromHtml(url));  Html.fromHtml을 통해서 넣어주면 된다.)
 * 이 파일에서는 웹뷰 방식만 기재하도록 한다. 레트로핏이나 JSON 요청하고 받는 건 따로 공부해야되서
 * 나중에 등록하기로 한다...
 * Created by Swifter on 2017-11-02.
 */

class WebView : Activity(){

    val title:String = "타이틀 들어가자"

    val testEvent:String = "<!DOCTYPE html><!--HTML5/CSS3 선언-->\n" +
            "                                                          <!--<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">-->\n" +
            "                                                          <html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ko\" lang=\"ko\" dir=\"ltr\">\n" +
            "                                                              <head>\n" +
            "                                                              <meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />\n" +
            "                                                              <meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\" />\n" +
            "                                                              <!-- <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi\" /> -->    \n" +
            "                                                              <!--<meta name=\"viewport\" content=\"width=사이즈, user-scalable=1\" />--><!-- 초기 해상도를 지정할 경우-->\n" +
            "                                                              <meta name=\"viewport\"content=\"width=640,user-scalable=1\" />\n" +
            "                                                              <!-- <meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0\" /> -->\n" +
            "                                                              <!-- <meta name=\"viewport\"content=\"width=640,target-densitydpi=medium-dpi\" /> -->\n" +
            "                                                              <meta name=\"autocomplete\" content=\"off\" />\n" +
            "                                                              <!--모바일 기본 메타태그-->\n" +
            "                                                              <meta name=\"apple-mobile-web-app-capable\" content=\"yes\" />\n" +
            "                                                              <!--사용자 확대기능:yes-->\n" +
            "                                                              <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"ctmapp\" />\n" +
            "                                                              <!--스테이터스바 컬러:black-->\n" +
            "                                                              <title>서비스 이용약관</title>\n" +
            "                                                           <link href=\"/resources/css/egovframework/common.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "                                                           <script type=\"text/javascript\">\n" +
            "                                                           </script>\n" +
            "                                                              </head>\n" +
            "                                                          <style type=\"text/css\">\n" +
            "                                                          /* .introBox {background:url('../images/drvapp/intro_bg.jpg') 0 0 repeat; width:1080px; height:1920px;} */\n" +
            "                                                          /* .introBox .introbtn {} */\n" +
            "                                                          /* .introBox .introbtn dt {width:630px; float:left; display:inline; margin-left:228px; padding-top:750px;} */\n" +
            "                                                          /* .introBox .introbtn dd {width:630px; float:left; padding-top:15px; position:relative; margin-left:228px;} */\n" +
            "                                                          </style>\n" +
            "                                                          \n" +
            "                                                          <body class=\"mainBg\">\n" +
            "                                                          \t\n" +
            "                                                          <p align=center style='text-align:center'><b><span style='font-size:16.0pt'>서비스 이용약관</span></b></p>\n" +
            "                                                          \t\n" +
            "                                                          <p>제 1 조 (목적)</p> \n" +
            "                                                          <p>목적은 없고 그냥 테스트 및 샘플 용이야.</p> \n" +
            "                                                          "

    /**
     * Json메시지 이벤트를 받는 것부터 시작한다.
     */
    fun receiveagree_TOM(event:String){ //여기 원래 문자열 매개변수가 아니라 JsonMessageEvent 객체를 매개변수로 받는다.
        RootApp.getLogger()!!.d("Json 메시지 이벤트를 받음.")

        //코틀린에선 이러한 표현이 가능함. apply 는 객체.메소드 이런형식으로 안해도 되도록 해줌. new가 없기때문에 걍 클래스 갖다가 박아도 댐.
        startActivity(Intent(applicationContext,PrintActivity::class.java).apply {
            putExtra("index", event.toString())
            putExtra("title",title)
        })
    }

}
