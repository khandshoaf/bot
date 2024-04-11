package bot.manga.truyendep;

import bot.BaseBot;
import com.google.gson.JsonArray;
import util.AppRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TDBCrawl extends BaseBot {
    public TDBCrawl(int maxThread, long restTime) {
        super(maxThread, restTime);
        toolkit.appLogger.info("create");
    }

    @Override
    public void run() {
        super.run();
        toolkit.appLogger.info("prepare");
//        loadBasicInfo();
        loadInfoByPages();
    }

    private void loadBasicInfo() {
        executor.execute(() -> {
            toolkit.appLogger.info("request basic info");
            appRequest.request(new AppRequest.Callback() {
                @Override
                public String getUrl() {
                    return "https://truyendep.com/wp-content/themes/manga/list-manga-front.js?nocache=1622726658";
                }

                @Override
                public Map<String, String> getHeader() {
                    return null;
                }

                @Override
                public void execute(String data) {
                    // toolkit.appLogger.debug(data);
                    List<TDMInfo> result = new ArrayList<>();
                    JsonArray array = toolkit.gson.fromJson(data, JsonArray.class);
                    for (int i = 0; i < array.size(); i++) {
                        TDMInfo item = toolkit.gson.fromJson(array.get(i), TDMInfo.class);
                        if (item != null) result.add(item);
                    }
                    // toolkit.appLogger.debug(toolkit.gson.toJson(result));
                    toolkit.appLogger.info(String.format("basic info has %s item", result.size()));
                }
            });
        });
    }

    private void loadInfoByPages() {
        executor.execute(() -> {
            toolkit.appLogger.info("request by page");
            appRequest.request(new AppRequest.Callback() {
                @Override
                public String getUrl() {
                    return "https://truyendep.com/wp-content/themes/manga/list-manga-home.js?nocache=1622735016";
                }

                @Override
                public Map<String, String> getHeader() {
                    return new HashMap<>() {{
                        put("Referer", String.format("https://truyendep.com/moi-cap-nhat/page/%s/", 3));
                    }};
                }

                @Override
                public void execute(String data) {
                    toolkit.appLogger.debug(data);
                }
            });
        });
    }
}
