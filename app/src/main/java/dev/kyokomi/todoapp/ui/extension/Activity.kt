package dev.kyokomi.todoapp.ui.extension

import android.app.Activity
import android.app.ActivityManager
import dev.kyokomi.todoapp.ui.main.MainActivity

fun Activity.finishIfNoTaskStartDefault() {
    val tasks = this.getSystemService(ActivityManager::class.java)?.appTasks
    val activitySize: Int = tasks?.firstOrNull()?.taskInfo?.numActivities ?: 0
    val taskSize = tasks?.size ?: 0
    if (taskSize <= 1 && activitySize <= 1) {
        MainActivity.start(this) // 戻る画面がないのでHomeへ
    }
    this.finish() // backで戻れないようにする
}
