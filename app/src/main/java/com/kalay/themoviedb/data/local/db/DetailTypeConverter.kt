package com.kalay.themoviedb.data.local.db

import androidx.room.TypeConverter
import com.kalay.themoviedb.domain.enums.DetailType

class DetailTypeConverter {
    @TypeConverter
    fun fromDetailType(detailType: DetailType): String {
        return detailType.name
    }

    @TypeConverter
    fun toDetailType(detailType: String): DetailType {
        return DetailType.valueOf(detailType)
    }
}

