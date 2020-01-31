package com.nexters.doctor24.todoc.ui.map

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.naver.maps.geometry.LatLng
import com.nexters.doctor24.todoc.data.map.response.ResAddressData
import com.nexters.doctor24.todoc.data.map.response.ResArea
import com.nexters.doctor24.todoc.data.map.response.ResMapAddress
import com.nexters.doctor24.todoc.data.map.response.ResRegion
import com.nexters.doctor24.todoc.data.marker.MarkerTypeEnum
import com.nexters.doctor24.todoc.data.marker.response.OperatingDate
import com.nexters.doctor24.todoc.data.marker.response.ResMapMarker
import com.nexters.doctor24.todoc.repository.MarkerRepository
import com.nexters.doctor24.todoc.rule.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jiyoung on 13/01/2020
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NaverMapViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var testCoroutineRule = CoroutineTestRule()

    private lateinit var viewModel : NaverMapViewModel
    @Mock
    private lateinit var markerRepo : MarkerRepository

    @Mock
    private lateinit var mapRepo : MapRepository

    @Mock
    lateinit var resources: Resources

    @Before
    fun init() {
//        MockitoAnnotations.initMocks(this)
//        startKoin { modules(listOf(networkModule, repositoryModule)) }
        viewModel = NaverMapViewModel(testCoroutineRule.testDispatcherProvider, markerRepo, mapRepo)
    }

    @Test
    fun `(Given) Map 화면 진입 시 (When) 현재 위치 받아와서 (Then) 주변 병원 정보 마커 표시`() {
        val expectedResult = listOf(MarkerUIData(
            location = LatLng(0.0, 0.0),
            name = "강남병원"
        ))

        testCoroutineRule.testDispatcher.runBlockingTest {
            Mockito. `when` (markerRepo.getMarkers("0.0", "0.0", MarkerTypeEnum.HOSPITAL)).thenReturn(
                listOf(
                    ResMapMarker(
                        placeName = "강남병원", latitude = 0.0, longitude = 0.0, medicalType = "hospital",
                        placeAddress = "", placePhone = "01000000000",
                        days = listOf(OperatingDate(
                            dayType = "MONDAY",
                            startTime = "00:00:00",
                            endTime = "00:00:00"))
                    ))
            )
            viewModel.reqMarker(0.0,0.0)
            viewModel.hospitalMarkerDatas.observeForever {
                println("hospitalMarkerDatas : $it")
                assertEquals(expectedResult, it)
            }
        }
    }

    @Test
    fun `(Given) Map화면에서 (When) 구단위 주소 버튼 누르면 (Then) api 호출해서 주소받아오기`() {
        val expectedResult = "인제군"

        testCoroutineRule.testDispatcher.runBlockingTest {
            Mockito. `when` (mapRepo.getAddress("128.12345,37.98776")).thenReturn(
                ResMapAddress(
                    addressData = listOf(
                        ResAddressData(
                            typeName = "legalcode",
                            region = ResRegion(
                                area2 = ResArea(
                                    areaName = "인제군",
                                    coords = null
                                )
                            )
                        )
                    )
                )
            )
            viewModel.currentLocation.value = LatLng(128.12345, 37.98776)
            viewModel.onClickGetAddress()
            viewModel.mapAddressData.observeForever{
                println("mapAddressData : $it")
                assertEquals(expectedResult, it)
            }
        }
    }

    /*@Test
    fun `(Given) Map 화면에서 (When) 병원 탭을 눌렀을 때 (Then) 하단에 주변 병원 리스트 텍스트가 노출`() {
        val expectedResult = "주변 병원 리스트"
        viewModel.onChangeTab(0)
        viewModel.tabChangeEvent.observeForever {
            viewModel.onChangeBottomTitle(String.format("주변 %s 리스트", viewModel.tabList[it].title))
        }
        viewModel.bottomTitle.observeForever {
            println("bottomTitle : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) Map 화면에서 (When) 약국 탭을 눌렀을 때 (Then) 하단에 주변 약국 리스트 텍스트가 노출`() {
        val expectedResult = "주변 약국 리스트"
        viewModel.onChangeTab(1)
        viewModel.tabChangeEvent.observeForever {
            viewModel.onChangeBottomTitle(String.format("주변 %s 리스트", viewModel.tabList[it].title))
        }
        viewModel.bottomTitle.observeForever {
            println("bottomTitle : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) Map 화면에서 (When) 동물병원 탭을 눌렀을 때 (Then) 하단에 주변 동물병원 리스트 텍스트가 노`() {
        val expectedResult = "주변 동물병원 리스트"
        viewModel.onChangeTab(2)
        viewModel.tabChangeEvent.observeForever {
            viewModel.onChangeBottomTitle(String.format("주변 %s 리스트", viewModel.tabList[it].title))
        }
        viewModel.bottomTitle.observeForever {
            println("bottomTitle : $it")
            assertEquals(expectedResult, it)
        }
    }*/
}