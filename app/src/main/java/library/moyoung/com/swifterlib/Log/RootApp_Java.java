package library.moyoung.com.swifterlib.Log;

import android.annotation.SuppressLint;
import android.app.Application;

import com.jiongbull.jlog.Logger;
import com.jiongbull.jlog.constant.LogLevel;
import com.jiongbull.jlog.constant.LogSegment;
import com.jiongbull.jlog.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017-10-30.
 */

public class RootApp_Java extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Logger sLogger;

    @Override
    public void onCreate() {
        super.onCreate();
        List<String> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.WTF);

        sLogger = Logger.Builder.newBuilder(getApplicationContext(), "jlog")
                /* properties below are default value, you can modify them or not. */
                .setDebug(true)
                .setWriteToFile(false)
                .setLogDir("jlog")
                .setLogPrefix("")
                .setLogSegment(LogSegment.TWELVE_HOURS)
                .setLogLevelsForFile(logLevels)
                .setZoneOffset(TimeUtils.ZoneOffset.P0800)
                .setTimeFormat("yyyy-MM-dd HH:mm:ss")
                .setPackagedLevel(0)
                .setStorage(null)
                .build();
    }

    public static Logger getLogger() {
        return sLogger;
    }
}
