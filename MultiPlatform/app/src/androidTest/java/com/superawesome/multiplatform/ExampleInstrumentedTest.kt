package com.superawesome.multiplatform

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.superawesome.multiplatform", appContext.packageName)
    }

    @Test
    fun verifyMessageSentToMessageActivity() {

        // Types a message into a EditText element.
        onView(withId(R.id.taskItem))
            .perform(click())

        // Clicks a button to send the message to another
        // activity through an explicit intent.
//        onView(withId(R.id.send_message)).perform(click())

        // Verifies that the DisplayMessageActivity received an intent
        // with the correct package name and message.
//        intended(
//            allOf(
//                hasComponent(hasShortClassName(".DisplayMessageActivity")),
//                toPackage(PACKAGE_NAME),
//                hasExtra(MainActivity.EXTRA_MESSAGE, MESSAGE)
//            )
//        )

    }
}
