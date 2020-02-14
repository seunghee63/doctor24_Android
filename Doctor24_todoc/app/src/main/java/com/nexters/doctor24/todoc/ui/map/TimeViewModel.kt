package com.nexters.doctor24.todoc.ui.map

import android.widget.TimePicker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.nexters.doctor24.todoc.base.BaseViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


internal class TimeViewModel() : BaseViewModel() {

    private val _nowTime = MutableLiveData<String>()
    val nowTime: LiveData<String> get() = _nowTime

    private val _startTime = MutableLiveData<String>()
    val startTime: LiveData<String> get() = _startTime
    private val _startTempTime = MutableLiveData<String>()
    val startTempTime: LiveData<String> get() = _startTempTime

    private val _endTime = MutableLiveData<String>()
    val endTime: LiveData<String> get() = _endTime
    private val _endTempTime = MutableLiveData<String>()
    val endTempTime: LiveData<String> get() = _endTempTime

/*    private val _startHour = MutableLiveData<String>()
    val startHour: LiveData<String> get() = _startHour
    private val _startMin = MutableLiveData<String>()
    val startMin: LiveData<String> get() = _startMin
    private val _startAmPm = MutableLiveData<String>()
    val startAmPm: LiveData<String> get() = _startAmPm
    private val _endHour = MutableLiveData<String>()
    val endHour: LiveData<String> get() = _endHour
    private val _endMin = MutableLiveData<String>()
    val endMin: LiveData<String> get() = _endMin
    private val _endAmPm = MutableLiveData<String>()
    val endAmPm: LiveData<String> get() = _endAmPm

    */

    private val _isOpen = MutableLiveData<Boolean>()
    val isOpen: LiveData<Boolean> get() = _isOpen

    private val _isSelected =
        MutableLiveData<Boolean>().apply { value = true }     //true - start, false - end
    val isSelected: LiveData<Boolean> get() = _isSelected
    private val _isPickerSelected = MutableLiveData<Boolean>().apply { postValue(false) }
    val isPickerSelected: LiveData<Boolean> get() = _isPickerSelected
    private val _isCompletedTimeSetting = MutableLiveData<Boolean>().apply { postValue(false) }
    val isCompletedTimeSetting: LiveData<Boolean> get() = _isCompletedTimeSetting

    //1) bottomSheet 닫혔을 때, 글자 검정색으로 세팅
    //2) 확인 눌렀을때, bottomSheet 닫히면서 시간 데이터 셋팅
    //3) 그냥 내릴 경우, 변경 전 시간으로 데이터 셋팅
    //4) 숫자 변동사항 있을경우, 확인버튼 활성화

    init {
        getCurrentTime()
    }

    fun storeTemp(isStart : Boolean){
        if(isStart){
            _startTempTime.value = _startTime.value
        }else{
            _endTempTime.value = _endTime.value
        }
    }

    fun setBottomSheetState(state : Int){
        if(state == BottomSheetBehavior.STATE_COLLAPSED)
            _isOpen.value = false
        else
            _isOpen.value = true
    }

    fun setChangeTime(view : TimePicker, isStart: Boolean) {
        Timber.e("setHour: ${view.hour} setMinute ${view.minute}")

        if (isStart) {
            _startTime.value = setTime(view.hour, view.minute)
        } else
            _endTime.value = setTime(view.hour, view.minute)
    }

    fun setTimeSelected(boolean: Boolean) {
        _isSelected.value = boolean
        //_isSelected.postValue(boolean)
    }

    fun onClickTimeSetting(boolean: Boolean) {
        _isPickerSelected.value = boolean
        _isPickerSelected.postValue(boolean)
    }

    fun getCurrentTime() {
        val now = System.currentTimeMillis()
        val mDate = Date(now)

        val timeHour = SimpleDateFormat("HH").format(mDate).toInt()
        val timeMin = SimpleDateFormat("mm").format(mDate).toInt()

        _startTime.value = setTime(timeHour, timeMin)
        _endTime.value = setTime(timeHour + 1, timeMin)
    }

    private fun setAmPm(hour: Int): String {
        return if (hour >= 12)
            "오후"
        else
            "오전"
    }

    private fun setHour(hour: Int): String {
        return if (hour >= 12)
            (hour - 12).toString()
        else
            "$hour"
    }

    private fun setMinute(min: Int): String {
        return if (min >= 10)
            "$min"
        else
            "0$min"
    }

    private fun setTime(hour: Int, minute: Int): String {
        return """${setAmPm(hour)} ${setHour(hour)}:${setMinute(minute)}"""
    }

    private fun validateEndTime(hour: Int, minute: Int) {
/*        if (hour > tp_time_picker.hour) {
            Toast.makeText(context, "시간 설정을 다시 해 주세요1", Toast.LENGTH_SHORT).show()
            return
        } else if ((hour == tp_time_picker.hour) && (minute > tp_time_picker.minute)) {
            Toast.makeText(context, "시간 설정을 다시 해 주세요2", Toast.LENGTH_SHORT).show()
            return
        }
        include_layout_set_time.visibility = View.VISIBLE
        include_layout_set_time_picker.visibility = View.GONE*/
    }


}