package com.alawiyaa.mydiabetes.ui.`2home`

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.DataDummy
import com.alawiyaa.mydiabetes.data.utils.EspressoIdlingResource
import com.alawiyaa.mydiabetes.ui.home.HomeActivity
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {

    private val dummyTypeDiabetes = DataDummy.dummyTypeDiabetes()
    private val dummyTypeDrug = DataDummy.dummyTypeNaturalMedicine()
    private val dummyFactorDiabetes = DataDummy.dummyfactorDiabetes()
    private val dummyFactorRisk = DataDummy.dummyfactorRisk()
    private val dummyResultPositive =
        "Hasil Deteksi Dini Diabetes adalah Positive. Anda mungkin menderita Diabetes. Silakan berkonsultasi dengan Dokter."

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }


    @Test
    fun test2LoadAllNews() {
        Espresso.onView(withId(R.id.rv_type_diabetes))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_factor_diabetes))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTypeDiabetes.size))

        Espresso.onView(withId(R.id.rv_type_drug))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_type_drug))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTypeDrug.size))

        Espresso.onView(withId(R.id.scrollNews)).perform(ViewActions.swipeUp())

        Espresso.onView(withId(R.id.rv_factor_diabetes))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_factor_diabetes))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyFactorDiabetes.size))

        Espresso.onView(withId(R.id.rv_factor_risk))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_factor_risk))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyFactorRisk.size))
    }

    @Test
    fun test3ViewDetailNews(){
        Espresso.onView(withId(R.id.rv_type_diabetes))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.rv_type_diabetes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,
            click()
        ))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_detail))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_source))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        Espresso.onView(withId(R.id.rv_type_drug))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_type_drug)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7,
            click()
        ))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_detail))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_source))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        Espresso.onView(withId(R.id.scrollNews)).perform(ViewActions.swipeUp())

        Espresso.onView(withId(R.id.rv_factor_diabetes))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_factor_diabetes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,
            click()
        ))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_detail))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_source))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        Espresso.onView(withId(R.id.rv_factor_risk))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_factor_risk)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7,
            click()
        ))
        Espresso.onView(withId(R.id.tv_detail_title))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_detail))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_source))
            .check(matches(isDisplayed()))
        Espresso.pressBack()


    }

    @Test
    fun test4InsertUpdateFavorite(){
        Espresso.onView(withId(R.id.rv_type_diabetes))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_type_diabetes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()
        ))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.rv_type_drug))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_type_drug)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()
        ))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.scrollNews)).perform(ViewActions.swipeUp())

        Espresso.onView(withId(R.id.rv_factor_diabetes))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_factor_diabetes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()
        ))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.rv_factor_risk))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_factor_risk)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()
        ))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withText(R.string.tab_favorite)).perform(click())

        for (i in 0..3){
            Espresso.onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
            Espresso.onView(withId(R.id.fab_favorite)).perform(click())
            Espresso.pressBack()
        }



    }

        @Test
    fun test5UserPrediction(){
        Espresso.onView(withId(R.id.navigation_history)).perform(click())
        Espresso.onView(withId(R.id.fab_diagnosis)).perform(click())

        for (i in 0..14){
            Espresso.onView(withId(R.id.rb_option1))
                .perform(click())
            Espresso.onView(withId(R.id.btn_next)).perform(click())
        }
        Espresso.onView(withId(R.id.btn_next)).perform(click())

        Espresso.onView(withId(R.id.btn_process)).perform(click())
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.btn_process)).perform(setTextViewVisibility(false))

        Espresso.onView(withId(R.id.tv_information)).perform(setTextViewVisibility(true))
        Espresso.onView(withId(R.id.tv_information)).check(matches(withText(dummyResultPositive)))


        Espresso.onView(withId(R.id.btn_save)).perform(setTextViewVisibility(true))
        Espresso.onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.btn_save)).perform(click())
        //del

        Espresso.onView(withId(R.id.rv_result)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.btn_delete)).perform(click())
        Espresso.pressBack()




    }

    @Test
    fun test6Profile() {
        Espresso.onView(withId(R.id.navigation_history)).perform(click())
        Espresso.onView(withId(R.id.navigation_profile)).perform(click())
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.tv_profile_username))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_full_name))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_profile_gender))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btn_log_out)).perform(click())
    }

    private fun setTextViewVisibility(value: Boolean): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun perform(uiController: UiController?, view: View) {
                view.visibility = if (value) View.VISIBLE else View.GONE
            }

            override fun getDescription(): String {
                return "Show / Hide View"
            }
        }
    }


}

