package library.moyoung.com.swifterlib.Log

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.jiongbull.jlog.JLog
import com.jiongbull.jlog.Settings
import com.jiongbull.jlog.constant.LogLevel
import com.jiongbull.jlog.printer.DefaultPrinter
import com.jiongbull.jlog.printer.JsonPrinter
import com.jiongbull.jlog.printer.Printer
import com.jiongbull.jlog.util.LogUtils
import java.io.PrintWriter
import java.io.StringWriter

/**
 * 로그관리 클래스
 * JLog 로그 라이브러리 재활용 로그 껐다 켰다 넣고 싶어서... 사실 디버그 모드만 추가함...
 * 2017.10.27
 * Create by Swifter
 */

class JLogview {

    companion object {
        private val LOG_CLASS_NAME = JLog::class.java.name
        private val LOG_PRINT_METHOD_NAME = "printLog"

        private var sDefaultPrinter: DefaultPrinter? = null
        private var sJsonPrinter: JsonPrinter? = null
        private var sSettings: Settings? = null

        private var DEBUG_MODE = true

        fun init(context: Context): Settings {
            sDefaultPrinter = DefaultPrinter()
            sJsonPrinter = JsonPrinter()
            sSettings = Settings()
            return sSettings!!.setContext(context)
        }

        fun getSettings(): Settings? {
            return sSettings
        }

        fun setSettings(settings: Settings) {
            sSettings = settings
        }

        /**
         * VERBOSE 관련
         * @param tag
         * @param message
         */
        fun v(tag: String, message: String) {
            printLog(LogLevel.VERBOSE, tag, null, message)
        }

        /**
         * @param message
         */
        fun v(message: String) {
            printLog(LogLevel.VERBOSE, null, null, message)
        }

        /**
         * Debug 관련
         * @param tag
         * @param message
         */
        fun d(tag: String, message: String) {
            printLog(LogLevel.DEBUG, tag, null, message)
        }

        /**
         * @param message
         */
        fun d(message: String) {
            printLog(LogLevel.DEBUG, null, null, message)
        }

        /**
         * Info 관련
         * @param tag
         * @param message
         */
        fun i(tag: String, message: String) {
            printLog(LogLevel.INFO, tag, null, message)
        }

        /**
         * @param message
         */
        fun i(message: String) {
            printLog(LogLevel.INFO, null, null, message)
        }

        /**
         * Warn관련
         * @param tag
         * @param message
         */
        fun w(tag: String, message: String) {
            printLog(LogLevel.WARN, tag, null, message)
        }

        /**
         * @param message
         */
        fun w(message: String) {
            printLog(LogLevel.WARN, null, null, message)
        }

        /**
         * ERROR관련
         * @param tag
         * @param t       [Throwable]
         * @param message
         */
        fun e(tag: String, t: Throwable, message: String) {
            printLog(LogLevel.ERROR, tag, t, message)
        }

        /**
         * @param t       [Throwable]
         * @param message
         */
        fun e(t: Throwable, message: String) {
            printLog(LogLevel.ERROR, null, t, message)
        }

        /**
         * @param tag
         * @param message
         */
        fun e(tag: String, message: String) {
            printLog(LogLevel.ERROR, tag, null, message)
        }

        /**
         * @param message
         */
        fun e(message: String) {
            printLog(LogLevel.ERROR, null, null, message)
        }

        /**
         * @param tag
         * @param t   [Throwable]
         */
        fun e(tag: String, t: Throwable) {
            printLog(LogLevel.ERROR, tag, t, null)
        }

        /**
         * @param t [Throwable]
         */
        fun e(t: Throwable) {
            printLog(LogLevel.ERROR, null, t, null)
        }


        /**
         * JSON디버그

         * @param tag
         * *
         * @param json json
         */
        fun json(tag: String, json: String) {
            printLog(LogLevel.JSON, tag, null, json)
        }


        /**
         * JSON디버그

         * @param json 信息
         */
        fun json(json: String) {
            printLog(LogLevel.JSON, null, null, json)
        }

        /**
         * @param level   [LogLevel]
         * @param tag
         * @param t       [Throwable]
         * @param message
         */
        private fun printLog(level: LogLevel, tag: String?, t: Throwable?, message: String?) {
            //디버그 모드일때만 출력한다.
            if (DEBUG_MODE) {
                var tag = tag
                var message = message
                if (TextUtils.isEmpty(message)) {
                    message = null
                }
                if (message == null) {
                    if (t == null) {
                        return  // 不记录没有信息和异常的日志
                    }
                    message = Log.getStackTraceString(t)
                } else {
                    if (t != null) {
                        message += Printer.LINE_SEPARATOR + getStackTraceString(t)
                    }
                }
                val elements = Throwable().stackTrace
                val index = getStackIndex(elements)
                if (index == -1) {
                    throw IllegalStateException("set -keep class com.jiongbull.jlog.** { *; } in your proguard config file")
                }
                val element = elements[index]
                if (TextUtils.isEmpty(tag)) {
                    tag = getTag(element)
                }
                val settings = JLog.getSettings()
                val isOutputToConsole = settings.isDebug
                val isOutputToFile = settings.isWriteToFile && settings.logLevelsForFile.contains(level)
                when (level) {
                    LogLevel.VERBOSE, LogLevel.DEBUG, LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR, LogLevel.WTF -> {
                        if (isOutputToConsole) {
                            sDefaultPrinter!!.printConsole(level, tag, message, element)
                        }
                        if (isOutputToFile) {
                            sDefaultPrinter!!.printFile(level, tag, message, element)
                        }
                    }
                    LogLevel.JSON -> {
                        if (isOutputToConsole) {
                            sJsonPrinter!!.printConsole(level, tag, message, element)
                        }
                        if (isOutputToFile) {
                            sJsonPrinter!!.printFile(level, tag, message, element)
                        }
                    }
                    else -> {
                    }
                }
            }else
                return
        }

        /**
         * @param element
         * @return TAG
         */
        private fun getTag(element: StackTraceElement): String {
            return LogUtils.getSimpleClassName(element.className)
        }

        /**
         * @param elements
         * @return
         */
        private fun getStackIndex(elements: Array<StackTraceElement>): Int {
            var isChecked = false
            var element: StackTraceElement
            for (i in elements.indices) {
                element = elements[i]
                if (LOG_CLASS_NAME == element.className && LOG_PRINT_METHOD_NAME == element.methodName) {
                    isChecked = true
                }
                if (isChecked) {
                    val index: Int = i + 2 + getSettings()!!.packagedLevel
                    if (index < elements.size) {
                        return index
                    }
                }
            }
            return -1
        }

        /**
         * @param t [Throwable]
         * @return
         */
        private fun getStackTraceString(t: Throwable): String {
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            t.printStackTrace(pw)
            pw.flush()
            return sw.toString()
        }
    }
}

