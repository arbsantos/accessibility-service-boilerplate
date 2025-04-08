package pt.lasige.tnp.accessibilityserviceboilerplate.accessibleservice

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class AccessibilityService: AccessibilityService() {

    val TAG: String = "AccessibilityService"

    override fun onInterrupt() {}

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let{
            Log.d(TAG, String.format("type: %s", it.eventType))
            Log.d(TAG, String.format("package: %s", it.packageName))
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        val info: AccessibilityServiceInfo = AccessibilityServiceInfo()
        info.apply {
            // Set the type of events that this service wants to listen to. Others
            // aren't passed to this service.
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or
                    AccessibilityEvent.TYPE_VIEW_FOCUSED or
                    AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or
                    AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED

            // If you only want this service to work with specific apps, set their
            // package names here. Otherwise, when the service is activated, it
            // listens to events from all apps.
            // packageNames = arrayOf("com.domain.appname")

            // Set the type of feedback your service provides.
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            // Default services are invoked only if no package-specific services are
            // present for the type of AccessibilityEvent generated. This service is
            // app-specific, so the flag isn't necessary. For a general-purpose
            // service, consider setting the DEFAULT flag.

            flags = AccessibilityServiceInfo.DEFAULT;

            notificationTimeout = 100
        }

        this.serviceInfo = info

    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}