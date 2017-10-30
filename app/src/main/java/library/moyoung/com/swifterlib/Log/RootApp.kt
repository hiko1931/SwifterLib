package library.moyoung.com.swifterlib.Log

import android.annotation.SuppressLint
import android.app.Application
import com.jiongbull.jlog.Logger
import com.jiongbull.jlog.constant.LogLevel
import com.jiongbull.jlog.constant.LogSegment
import com.jiongbull.jlog.util.TimeUtils
import java.util.*

/**
 * Application 사용하려면 Manifest에 name추가해줘야함.
 * Created by Swifter on 2017-10-30.
 */
class RootApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var sLogger: Logger? = null

        fun getLogger(): Logger? {
            return sLogger
        }
    }

    override fun onCreate() {
        super.onCreate()
        val logLevels = ArrayList<String>()
        logLevels.add(LogLevel.ERROR)
        logLevels.add(LogLevel.WTF)

        sLogger = Logger.Builder.newBuilder(applicationContext, "jlog")
                /* Properties below are default value, you can modify them or not. */
                .setDebug(true) //true면 로그 찍히고 false면 로그 안찍힘.
                .setWriteToFile(false) //로그 파일 출력 유무
                .setLogDir("jlog")
                .setLogPrefix("")
                .setLogSegment(LogSegment.TWELVE_HOURS)
                .setLogLevelsForFile(logLevels)
                .setZoneOffset(TimeUtils.ZoneOffset.P0800)
                .setTimeFormat("yyyy-MM-dd HH:mm:ss")
                .setPackagedLevel(0)
                .setStorage(null)
                .build()
    }
}