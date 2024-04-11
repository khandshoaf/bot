package storage.model.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatabaseTable {
    @SerializedName("news")
    @Expose
    public String news;
}