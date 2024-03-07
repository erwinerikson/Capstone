package com.apm.capstone.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apm.capstone.core.domain.model.User
import com.apm.capstone.core.domain.usecase.UserUseCase
import com.apm.capstone.utils.DataDummy
import com.apm.capstone.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailUserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userUseCase: UserUseCase
    private val dummyDetailData: User = DataDummy.generateDummyDataDetail()

    private lateinit var detailUserViewModel: DetailUserViewModel

    @Before
    fun setUp() {
        detailUserViewModel = DetailUserViewModel(userUseCase)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when a user is made a favorite`() = runTest {
        val favorite = false
        val expectedResponse = true
        `when`(detailUserViewModel.setFavoriteUser(dummyDetailData, favorite)).thenReturn(
            expectedResponse
        )
        val detailUserViewModel = DetailUserViewModel(userUseCase)
        val actualSetFavorite = detailUserViewModel.setFavoriteUser(dummyDetailData, favorite)

        verify(userUseCase).setFavoriteUser(dummyDetailData, favorite)
        assertNotNull(actualSetFavorite)
        assertEquals(expectedResponse, true)
    }

    @Test
    fun `when a user is made not favorite`() = runTest {
        val favorite = true
        val expectedResponse = true
        `when`(detailUserViewModel.setFavoriteUser(dummyDetailData, favorite)).thenReturn(
            expectedResponse
        )
        val detailUserViewModel = DetailUserViewModel(userUseCase)
        val actualSetFavorite = detailUserViewModel.setFavoriteUser(dummyDetailData, favorite)

        verify(userUseCase).setFavoriteUser(dummyDetailData, favorite)
        assertNotNull(actualSetFavorite)
        assertEquals(expectedResponse, true)
    }
}