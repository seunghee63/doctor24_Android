package com.nexters.doctor24.todoc.ui.map.preview

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.doctor24.todoc.R
import com.nexters.doctor24.todoc.data.marker.response.ResMapMarker
import com.nexters.doctor24.todoc.databinding.PreviewFragmentBinding
import kotlinx.android.synthetic.main.preview_fragment.*
import timber.log.Timber

internal class PreviewFragment : BottomSheetDialogFragment() {
    companion object {
        @JvmField
        val TAG: String = this::class.java.simpleName

        const val KEY_MEDICAL: String = "KEY_MEDICAL"
    }
    private lateinit var binding : PreviewFragmentBinding
    private var previewData: PreviewUiData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = arguments?.let{
            it.getParcelable<ResMapMarker>(KEY_MEDICAL)
        }
        data?.let {
            previewData = PreviewUiData(
                id = it.id,
                isEmergency = it.emergency,
                isNight = it.nightTimeServe,
                placeName = it.placeName,
                todayHour = "${it.day?.startTime} ~ ${it.day?.endTime}",
                phoneNumber = it.placePhone ?: "",
                address = it.placeAddress ?: ""
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate<PreviewFragmentBinding>(inflater, R.layout.preview_fragment, container, false)
        return binding.root//inflater.inflate(R.layout.preview_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setCanceledOnTouchOutside(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.item = previewData
        /*btn_daum_map.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("daummaps://route?sp=37.4640070,37.4640070&ep=37.4764356,126.9618302&by=FOOT")))
        }
        btn_naver_map.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("nmap://route/walk?slat=37.4640070&slng=37.4640070&sname=%EC%84%9C%EC%9A%B8%EB%8C%80%ED%95%99%EA%B5%90&dlat=37.4764356&dlng=126.9618302&dname=%EB%8F%99%EC%9B%90%EB%82%99%EC%84%B1%EB%8C%80%EC%95%84%ED%8C%8C%ED%8A%B8&appname=com.example.myapp")))
        }
        btn_google_map.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.537229,127.005515?q=34.99,-106.61(Treasure)")))
        }*/

    }

    override fun dismiss() {
        super.dismiss()
    }

    data class PreviewUiData(
        val id: String,
        val isEmergency : Boolean = false,
        val isNight : Boolean = false,
        val isNormal : Boolean = !isEmergency && !isNight,
        val placeName : String = "",
        val todayHour : String = "",
        val categories : String = "",
        val phoneNumber : String = "",
        val address : String = "",
        val distance : String = "${1}km"
    )
}