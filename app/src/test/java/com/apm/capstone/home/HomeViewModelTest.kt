package com.apm.capstone.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apm.capstone.core.domain.usecase.UserUseCase
import com.apm.capstone.utils.DataDummy
import com.apm.capstone.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var userUseCase: UserUseCase
    private val dummyUserData = DataDummy.generateDummyListUser()
    private val dummyUserNoData = DataDummy.generateDummyNoUser()

    @Test
    fun `when Get All User Should Return Data`() = runTest {
        val expectedUser = dummyUserData

        `when`(userUseCase.getAllUser()).thenReturn(expectedUser)

        val dataUseCase = userUseCase.getAllUser()
        val actualUser = HomeViewModel(userUseCase).user

        assertNotNull(actualUser)
        assertNotNull(dataUseCase)
        assertEquals(dataUseCase, expectedUser)
    }

    @Test
    fun `when Get All User Should No Data`() = runTest {
        val expectedUser = dummyUserNoData
        `when`(userUseCase.getAllUser()).thenReturn(expectedUser)

        val dataUseCase = userUseCase.getAllUser()
        val actualUser = HomeViewModel(userUseCase).user

        assertNotNull(actualUser)
        assertNotNull(dataUseCase)
        assertEquals(expectedUser, dataUseCase)
    }
}
