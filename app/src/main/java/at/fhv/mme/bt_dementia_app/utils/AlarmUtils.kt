package at.fhv.mme.bt_dementia_app.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import at.fhv.mme.bt_dementia_app.model.Activity

object AlarmUtils {
    fun setupAlarm(activityId: Long, activity: Activity, requireActivity: FragmentActivity) {
        val alarmManager = requireActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(requireActivity, AlarmReceiver::class.java).apply {
            putExtra("activity_name", activity.name)
            putExtra("activity_info", activity.additionalInfo)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            requireActivity,
            activityId.toInt(),
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val alarmTime = System.currentTimeMillis() + 10000

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            pendingIntent
        )
    }

    fun cancelAlarm(activity: Activity, requireActivity: FragmentActivity) {
        val alarmManager = requireActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(requireActivity, AlarmReceiver::class.java).apply {
            putExtra("activity_name", activity.name)
            putExtra("activity_info", activity.additionalInfo)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            requireActivity,
            activity.id!!.toInt(),
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
    }
}