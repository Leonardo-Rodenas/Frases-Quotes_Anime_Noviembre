package com.exampleMockWebServ.animequotesv2.cliente

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import okhttp3.Dispatcher
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import okhttp3.mockwebserver.RecordedRequest




@RunWith(AndroidJUnit4::class)
@MediumTest
class FraseClienteTest {


   /* @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)
*/

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val mockWebServer = MockWebServer()

    @Before
    fun setUp() {

        mockWebServer.start(8080)

        val dispatcher: Dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            fun dispatch(request: RecordedRequest): MockResponse? {
                when (request.path) {
                    "random" -> return success_response.random
                    "/quotes" -> return MockResponse().setResponseCode(200)
                        .setBody("version=9")
                    "/v1/profile/info" -> return MockResponse().setResponseCode(200)
                        .setBody("{\\\"anime\\\":\\\"Kamisama No Memochou\\\",\\\"character\\\":\\\"Shionji Yuuko\\\",\\\"quote\\\":\\\"NEETs aren't people who can't do or won't do things. The only difference is the rules. Because we're playing with different rules, we're forced to do things differently. The participants are disoriented. They frown. Then they are discouraged by labeling and stereotyping and find comfort in isolation.\\\"}")
                }
                return MockResponse().setResponseCode(404)
            }
        }
        mockWebServer.setDispatcher(dispatcher)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}