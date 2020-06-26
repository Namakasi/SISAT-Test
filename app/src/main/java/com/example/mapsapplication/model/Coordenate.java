package com.example.mapsapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mapsapplication.util.AppConstants;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coordenate implements Parcelable, Serializable {

    private Double latitude;
    private Double longitude;
    private String titleNumber;



    public Coordenate(Double latitude, Double longitude, String titleNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.titleNumber = titleNumber;
    }

    protected Coordenate(Parcel in) {
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        titleNumber = in.readString();
    }

    public static final Creator<Coordenate> CREATOR = new Creator<Coordenate>() {
        @Override
        public Coordenate createFromParcel(Parcel in) {
            return new Coordenate(in);
        }

        @Override
        public Coordenate[] newArray(int size) {
            return new Coordenate[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (latitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(latitude);
        }
        if (longitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(longitude);
        }
        parcel.writeString(titleNumber);
    }

    public boolean isValid(){
        return latitude != null && isLatitudeValuesValid()
                && longitude != null && isLongitudeValuesValid();
    }

    public boolean isLongitudeValuesValid() {
        return longitude > AppConstants.MIN_VALUE_LONGITUDE &&
                longitude < AppConstants.MAX_VALUE_LONGITUDE;
    }

    public boolean isLatitudeValuesValid(){
        return latitude > AppConstants.MIN_VALUE_LATITUDE &&
                latitude <  AppConstants.MAX_VALUE_LATITUDE;
    }


}
