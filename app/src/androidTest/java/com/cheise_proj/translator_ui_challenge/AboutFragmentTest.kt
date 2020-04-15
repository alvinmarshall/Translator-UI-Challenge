package com.cheise_proj.translator_ui_challenge


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AboutFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun aboutFragmentTest() {
        val tabView = onView(
            allOf(
                withContentDescription("A-Z"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_layout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val tabView2 = onView(
            allOf(
                withContentDescription("ABOUT"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_layout),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.tv_name), withText("Kelvin Birikorang"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_1),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Kelvin Birikorang")))

        val textView2 = onView(
            allOf(
                withId(R.id.tv_job_title), withText("Mobile Application Engineer"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_1),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Mobile Application Engineer")))

        val textView3 = onView(
            allOf(
                withId(R.id.tv_description),
                withText("Hey developers, I'm Kelvin from Ghana working at Infordas Ghana Limited"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_1),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Hey developers, I'm Kelvin from Ghana working at Infordas Ghana Limited")))

        val textView4 = onView(
            allOf(
                withId(R.id.tv_header), withText("This is a challenge by ardejob community"),
                childAtPosition(
                    allOf(
                        withId(R.id.root),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("This is a challenge by ardejob community")))

        val textView5 = onView(
            allOf(
                withId(R.id.btn_link_1), withText("GITHUB"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_1),
                        0
                    ),
                    5
                )
            )
        )
        textView5.perform(scrollTo(), click())

        val textView6 = onView(
            allOf(
                withId(R.id.btn_link_2), withText("TWITTER"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_1),
                        0
                    ),
                    6
                )
            )
        )
        textView6.perform(scrollTo(), click())

        val textView7 = onView(
            allOf(
                withId(R.id.btn_link_3), withText("LINKEDIN"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_1),
                        0
                    ),
                    7
                )
            )
        )
        textView7.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
