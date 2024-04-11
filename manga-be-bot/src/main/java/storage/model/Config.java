package storage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import storage.model.database.Database;
import storage.model.manga.Manga;
import storage.model.setting.Setting;

public class Config {
    @SerializedName("database")
    @Expose
    public Database database;
    @SerializedName("setting")
    @Expose
    public Setting setting;
    @SerializedName("logger")
    @Expose
    public Logger logger;

    @SerializedName("manga")
    @Expose
    public Manga manga;
}