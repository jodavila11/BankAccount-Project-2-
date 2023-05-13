package com.example.bankaccount.DB;

import androidx.room.TypeConverter;

import java.util.Date;

public class DataTypeConverter {

    @TypeConverter
    public long convertDateToLong(Date date ){
        return date.getTime();
    }

    @TypeConverter
    public Date convertLongToDate(long epoch){
        return new Date(epoch);

    }

}
