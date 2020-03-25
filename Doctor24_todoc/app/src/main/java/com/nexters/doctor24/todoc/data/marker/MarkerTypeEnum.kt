package com.nexters.doctor24.todoc.data.marker

import com.nexters.doctor24.todoc.R

/**
 * Created by jiyoung on 13/01/2020
 */
enum class MarkerTypeEnum(val type: String, val title: String) {
    HOSPITAL("hospital", "병원"),
    PHARMACY("pharmacy", "약국"),
    CORONA("corona", "코로나진료소"),
    SECURE("secure", "안심병원"),
    MASK("mask", "마스크");

    companion object {
        fun getMarkerType(type: String) : MarkerTypeEnum? = when(type) {
            HOSPITAL.type -> HOSPITAL
            PHARMACY.type -> PHARMACY
            CORONA.type -> CORONA
            SECURE.type -> SECURE
            MASK.type -> MASK
            else -> null
        }
    }
}

enum class MaskTypeEnum(val type: String, val title: String) {
    MASK_PHARMACY("01", "약국"),
    MASK_POST("02", "우체국"),
    MASK_NH("03", "농협");

    companion object {
        fun getMaskType(type: String) : MaskTypeEnum? = when(type) {
            MASK_PHARMACY.type -> MASK_PHARMACY
            MASK_POST.type -> MASK_POST
            MASK_NH.type -> MASK_NH
            else -> null
        }
    }
}

enum class MaskStateEnum(val state: String, val title : String, val drawable: Int) {
    REMAIN_PLENTY("plenty","충분", R.drawable.and_mask_small_enough_marker_withoutshadow),
    REMAIN_SOME("some","보통", R.drawable.and_mask_small_nomal_marker_withoutshadow),
    REMAIN_FEW("few","부족", R.drawable.and_mask_small_shortage_marker_withoutshadow),
    REMAIN_EMPTY("empty","품절", R.drawable.and_mask_small_soldout_marker_withoutshadow),
    REMAIN_BREAK("break","중단", -1);

    companion object {
        fun getMaskState(state: String) : MaskStateEnum? = when(state) {
            REMAIN_PLENTY.state -> REMAIN_PLENTY
            REMAIN_SOME.state -> REMAIN_SOME
            REMAIN_FEW.state -> REMAIN_FEW
            REMAIN_EMPTY.state -> REMAIN_EMPTY
            REMAIN_BREAK.state -> REMAIN_BREAK
            else -> null
        }
    }
}

sealed class MedicalMarkerBundleEnum(val count: Int) {
    class Bundle(total : Int) : MedicalMarkerBundleEnum(total)
    class Piece() : MedicalMarkerBundleEnum(1)
}