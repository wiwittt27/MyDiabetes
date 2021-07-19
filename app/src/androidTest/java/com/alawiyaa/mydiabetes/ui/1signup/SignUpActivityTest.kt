package com.alawiyaa.mydiabetes.ui.`1signup`

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.EspressoIdlingResource
import com.alawiyaa.mydiabetes.ui.signup.SignupActivity
import com.alawiyaa.mydiabetes.ui.utils.ToastMatcher
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SignUpActivityTest{
    private val dummyFullName = "Ahmad Fatoni"
    private val dummyGender = "Male"
    private val dummyUsername = "ahmad212"
    private val dummyPassword = "12345"

    @Before
    fun setUp() {
        ActivityScenario.launch(SignupActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun test1SignUpUser(){
        onView(withText(R.string.tab_Sign_up)).perform(click())
        onView(withId(R.id.edt_full_name)).perform(
            typeText(dummyFullName),
            closeSoftKeyboard()
        )
        onView(withId(R.id.spin_gender)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(dummyGender))).perform(click())
        onView(withId(R.id.spin_gender)).check(matches(withSpinnerText(containsString(dummyGender))))


        onView(withId(R.id.edt_register_username)).perform(
            typeText(dummyUsername),
            closeSoftKeyboard()
        )
        onView(withId(R.id.edt_register_password)).perform(
            typeText(dummyPassword),
            closeSoftKeyboard()
        )

        onView(withId(R.id.btn_register_regist)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_register_regist)).perform(click())

        onView(withText(R.string.register_sukses)).inRoot(ToastMatcher()).check(matches(isDisplayed()))

        //Login
        onView(withText(R.string.tab_Sign_in)).perform(click())
        onView(withId(R.id.edt_username)).perform(
            typeText(dummyUsername),
            closeSoftKeyboard()
        )
        onView(withId(R.id.edt_password)).perform(
            typeText(dummyPassword),
            closeSoftKeyboard())

        onView(withId(R.id.btn_login)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_login)).perform(click())





    }

}