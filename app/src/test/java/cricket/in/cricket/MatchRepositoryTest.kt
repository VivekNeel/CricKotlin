package cricket.`in`.cricket

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import java.io.IOException

import cricket.`in`.cricket.data.entity.Matches
import cricket.`in`.cricket.data.local.CricketDao
import cricket.`in`.cricket.data.local.CricketDatabase
import cricket.`in`.cricket.data.remote.MatchRepository
import cricket.`in`.cricket.data.remote.Resource
import cricket.`in`.cricket.data.remote.api.CricketService

import cricket.`in`.cricket.util.ApiUtil.successCall
import cricket.`in`.cricket.util.InstantAppExecutors
import cricket.`in`.cricket.util.TestDataFactory
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

/**
 * Created by vivek on 26/06/17.
 */

@RunWith(JUnit4::class)
class MatchRepositoryTest {
    private lateinit var repository: MatchRepository
    private lateinit var dao: CricketDao
    private lateinit var service: CricketService

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        dao = mock(CricketDao::class.java)
        service = mock(CricketService::class.java)
        val db = mock(CricketDatabase::class.java)
        `when`(db.provideScoreDao()).thenReturn(dao)
        repository = MatchRepository(dao, service, InstantAppExecutors())
    }

    @Test
    @Throws(IOException::class)
    fun loadMatches() {
        val dbData = MutableLiveData<List<Matches>>()
        `when`(dao.getMatches()).thenReturn(dbData)

        val repo = TestDataFactory.createMatchModel()
        val call = successCall(repo)
        `when`(service.getMatches()).thenReturn(call)

        val data = repository.getAllMatches()
        verify<CricketDao>(dao).getMatches()
        verifyNoMoreInteractions(service)

        val observer = mock(Observer::class.java)
        data.observeForever(observer as Observer<Resource<List<Matches>>>?)
        verifyNoMoreInteractions(service)
        verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<List<Matches>>()
        `when`(dao.getMatches()).thenReturn(updatedDbData)

        val list = TestDataFactory.matchList("ind", "eng")
        dbData.postValue(null)
        verify<CricketService>(service).getMatches()
        verify<CricketDao>(dao).insertItem(list)
        updatedDbData.postValue(list)
        verify(observer).onChanged(Resource.success(list))
    }

}
