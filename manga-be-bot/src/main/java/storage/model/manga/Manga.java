package storage.model.manga;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Manga {
    @SerializedName("maxThread")
    @Expose
    public Integer maxThread;
    @SerializedName("restTime")
    @Expose
    public Integer restTime;
}