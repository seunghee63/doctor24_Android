package com.nexters.doctor24.todoc.data.mask.response


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class StoreSale(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("addr")
    val addr: String,
    @SerializedName("type") // 약국: '01', 우체국: '02', 농협: '03'
    val type: String,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lng")
    val lng: Float,
    @SerializedName("stock_at") // 입고시간 YYYY/MM/DD HH:mm:ss
    val stockAt : String,
    @SerializedName("remain_stat") // 재고 상태
    val state: String? = "" // 100개 이상(녹색): 'plenty' / 30개 이상 100개미만(노랑색): 'some' / 2개 이상 30개 미만(빨강색): 'few' / 1개 이하(회색): 'empty' / 판매중지: 'break'
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(code)
        parcel.writeString(name)
        parcel.writeString(addr)
        parcel.writeString(type)
        parcel.writeFloat(lat)
        parcel.writeFloat(lat)
        parcel.writeString(stockAt)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoreSale> {
        override fun createFromParcel(parcel: Parcel): StoreSale {
            return StoreSale(parcel)
        }

        override fun newArray(size: Int): Array<StoreSale?> {
            return arrayOfNulls(size)
        }
    }


}