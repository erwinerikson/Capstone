package com.apm.capstone.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.apm.capstone.core.domain.model.User
import com.apm.capstone.core.domain.usecase.UserUseCase
import com.apm.capstone.favorite.utils.DataDummy
import com.apm.capstone.favorite.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class FavoriteModelViewTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var userUseCase: UserUseCase
    private val dummyFavoriteData: Flow<List<User>> = DataDummy.generateDummyListFavorite()

    @Test
    fun `when Get Favorite User Should Not Null and Return Data`() = runTest {
        val expectedFavorite = dummyFavoriteData
        `when`(userUseCase.getFavoriteUser()).thenReturn(expectedFavorite)

        val dataUseCase = userUseCase.getFavoriteUser()
        val actualUser = FavoriteModelView(userUseCase).user.observeForever {
            userUseCase.getFavoriteUser().asLiveData()
        }

        assertNotNull(dataUseCase)
        assertNotNull(actualUser)
        assertEquals(dummyFavoriteData, dataUseCase)
        assertEquals(10, dataUseCase.count())
    }

    @Test
    fun `when Get Favorite User Should no Data`() = runTest {
        val expectedFavorite: MutableList<List<User>> = arrayListOf()
        `when`(userUseCase.getFavoriteUser()).thenReturn(expectedFavorite.asFlow())

        val mockUser = userUseCase.getFavoriteUser()
        val actualUser = FavoriteModelView(userUseCase).user.observeForever {
            userUseCase.getFavoriteUser().asLiveData()
        }

        assertNotNull(mockUser)
        assertNotNull(actualUser)
        assertEquals(0, mockUser.count())
    }
}