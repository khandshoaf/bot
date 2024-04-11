package bot.manga.truyendep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TDMInfo {
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("img")
    @Expose
    public String img;
}