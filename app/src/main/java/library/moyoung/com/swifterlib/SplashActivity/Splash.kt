package library.moyoung.com.swifterlib.SplashActivity

/**
 * 스플래쉬 액티비티 만들어서 하는건 여러모로 안좋다함.
 * 일단 액티비티에서 러너블로 시간지연 시키는 방식으로 이루어지는데
 * 이러면 실제로 아무것도 안하지만 유저는 무작정 기다려아하는 문제가 있음.
 * Application이나 실행 액티비티에서 수행하는 내용이 많다면 이는 점점더 길어진다.
 * 이러한 문제점을 보안하기 위해서 스플래쉬 이미지는 이와 같이 구현하는 것이 좋다.
 *
 * 사실 코드상으로 할 건 없고... 설명만 적어 놓는다.
 *
 * 1. res/drawable 폴더에 splash.xml 복붙하고 주석 확인
 * 2. res/values/styles 에 가서 SplashTheme 를 추가 한다. NoTitleTheme 도 같이!
 * 3. 어플 최초 실행 지점에 가서 혹은 실행시키고 싶은 지점에 가서 setTheme(R.style.AppTheme); 를 호출해준다. 끗
 * Created by Admin on 2017-10-31.
 */

class Splash{

}