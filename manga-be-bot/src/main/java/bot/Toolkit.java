package bot;

import com.google.gson.Gson;
import storage.AppStorage;
import util.AppLogger;

import java.util.Random;

public class Toolkit {
    public Random random;
    public AppLogger appLogger;
    public AppStorage appStorage;
    public Gson gson;

    public Toolkit() {
        this.random = new Random();
        this.appLogger = AppLogger.getInstance();
        this.appStorage = AppStorage.getInstance();
        this.gson = new Gson();
    }
}
