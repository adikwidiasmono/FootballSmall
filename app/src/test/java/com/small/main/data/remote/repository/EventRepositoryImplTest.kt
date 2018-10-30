package com.small.main.data.remote.repository

import com.google.gson.GsonBuilder
import com.small.main.BuildConfig
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.service.ApiService
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import okhttp3.mockwebserver.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class EventRepositoryImplTest {
    private val leagueId = 4328

    lateinit var eventRepository: EventRepositoryImpl
    lateinit var mockServer: MockWebServer
    lateinit var apiService: ApiService

    @Before
    @Throws
    fun setUp() {
        // Initialize mock webserver
        mockServer = MockWebServer()
        // Start the local server
        mockServer.start()

        val okHttpClient = OkHttpClient.Builder().build()
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        // Get an instance of blogService
        apiService = retrofit.create(ApiService::class.java)
        // Initialized repository
        eventRepository = EventRepositoryImpl(apiService)
    }

    @After
    @Throws
    fun tearDown() {
        // We're done with tests, shut it down
        mockServer.shutdown()
    }

    @Test
    fun testLoadLastMatchesByLeagueId() {
        val path = "/blogs"

        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(getJson("json/blog/blogs.json"))
        // Enqueue request
        mockServer.enqueue(mockResponse)

        // Call the API
        eventRepository.loadLastMatch(leagueId)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // No errors
        testObserver.assertNoErrors()
        // One list emitted
        testObserver.assertValueCount(1)

        // Get the request that was just made
        val request = mockServer.takeRequest()
        // Make sure we made the request to the required path
        assertEquals(path, request.path)

    }

}