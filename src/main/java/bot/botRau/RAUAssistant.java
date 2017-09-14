package bot.botRau;

import bot.connection.ConnectionRAU;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RAUAssistant extends TelegramLongPollingBot {
    private static Connection conn;

    @Override
    public void onUpdateReceived(Update update) {

        System.out.println(update.getMessage().getFrom().getFirstName() + " : " + update.getMessage().getText());

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        String username = update.getMessage().getFrom().getUserName();
        String firstName=update.getMessage().getFrom().getFirstName();
        String message=update.getMessage().getText();

        conn = ConnectionRAU.getConnection();
        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM dayoff");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getDate(3));
            sendMessage(sendMessage);

        } catch (SQLException | TelegramApiException e) {
            e.printStackTrace();

        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "445801312:AAF6dRhIoOvJ2f48LMXZU3EvpB7Jn_EsSSQ";
    }
}
