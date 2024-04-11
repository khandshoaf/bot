package bot.manga.template;

import bot.BaseBot;

public class TDBCrawl extends BaseBot {
    TDBDatabase database;

    public TDBCrawl(int maxThread, long restTime) {
        super(maxThread, restTime);
        toolkit.appLogger.info("create");
        database = new TDBDatabase();
    }

    @Override
    public void run() {
        super.run();
        complete();
    }
}
