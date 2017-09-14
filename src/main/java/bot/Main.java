package bot;

import bot.botRau.RAUAssistant;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


public class Main {
    public static void main(String[] args) throws TelegramApiRequestException {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi =new TelegramBotsApi();
        RAUAssistant rauAssistant =new RAUAssistant();

        telegramBotsApi.registerBot(rauAssistant);
    }
}
