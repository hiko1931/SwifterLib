package library.moyoung.com.swifterlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import library.moyoung.com.swifterlib.Log.RootApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //스플래쉬 이미지 처리
        setTheme(R.style.AppTheme);

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logNormal();
    }

    /**
     * 일반 로그 사용 예시
     */
    private void logNormal(){
        String defaultTag = "Default tag";
        RootApp.Companion.getLogger().v(defaultTag);
        RootApp.Companion.getLogger().d(defaultTag);
        RootApp.Companion.getLogger().i(defaultTag);
        RootApp.Companion.getLogger().w(defaultTag);
        RootApp.Companion.getLogger().e(defaultTag);
        RootApp.Companion.getLogger().wtf(defaultTag);
    }

}
