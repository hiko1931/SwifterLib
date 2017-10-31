package library.moyoung.com.swifterlib.TedPermission

import com.gun0912.tedpermission.PermissionListener


/**
 * TedPermission 사용법임
 * 사실 블로그에도 잘 설명 되어있음. (http://gun0912.tistory.com/61)
 * API 23 이전에는 Manifest에 권한이 필요한 것들을 등록해주면 기능이 정상적으로 안될지언정 에러는
 * 출력하지 않는데...
 * API 23 이상부터는 권한이 필요한 것들에 대해서 런타임 퍼미션 요청을 안하면 에러를 출력함.
 * 고로 api23이상부터는 사용자가 그 기능을 사용하는 시점에서 사용자에게 권한허가를 받고
 * 그 기능의 권한을 부여받지 않는다면 켤 수 있도록 안내하도록 설계가 되야한다.
 *
 * 사용방법은
 * 1. dependencies에 compile 'gun0912.ted:tedpermission:2.0.0' 추가
 * 2. PermissionListener를 추가
 * 3. 주석처리 부분한 부분 주석 풀고 실행시켜줍니다.
 * Created by Swifter on 2017-10-30.
 */

class ExampleRP {

    fun ExampleRP(){

        val permissionlistener:PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                //권한을 부여받았을 때 작업
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                //권한을 부여받지 못했을 때의 작업
            }

        }

//        TedPermission.with(this) //Context 정보를 담는다.
//                .setPermissionListener(permissionlistener)
//                .setRationaleMessage("안내 메시지 출력창")
//                .setDeniedMessage("거부했을 때의 메시지")
//                .setPermissions(Manifest.permission.READ_CONTACTS) // 요구 권한 등록
//                .check()

        //기타 메소드 설명
        //setRationaleConfirmText() //권한이 필요한 이유에 대해서 설명하는 다이얼로그에서 [확인]버튼 텍스트를 설정
        //setDeniedMessage() //사용자가 권한을거부했을 때 보여지는 메세지를 설정합니다.
        //setDeniedCloseButtonText() //거부했을 때 보여지는 메세지 다이얼로그에서 [닫기] 버튼 텍스트를 설정합니다.
        //setGotoSettingButton () //권한을 거부했을 떄 보여지는 메시지에서 '설정'화면으로 갈 수 있는 버튼을 보여줄지 여부 결정
        //setGotoSettingbuttonText() // '설정'화면으로 갈 수 있는 버튼을 보여주는 경우 해당 버튼의 텍스트를 설정.

    }

}
