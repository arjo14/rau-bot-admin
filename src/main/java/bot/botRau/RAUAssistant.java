package bot.botRau;

import bot.connection.ConnectionRAU;
import bot.model.dto.UserDetailsDto;
import bot.model.dto.UserDto;
import bot.model.entity.InstituteEntity;
import bot.model.entity.UserEntity;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RAUAssistant extends TelegramLongPollingBot {
    private static Connection conn;
    private static Map<Long, UserDto> userMap = new HashMap<>();

    private static boolean mtelaArden;

    private void sendReply(String text, Long chatId, String topic) throws TelegramApiException {
        UserDto userDto=userMap.get(chatId);
        userDto.setLastQuestionTopic(topic);
        userMap.put(chatId,userDto);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage(sendMessage);
    }

    @Override
    public void onUpdateReceived(Update update) {

        boolean messageNotEmpty = update.hasMessage() && update.getMessage().hasText();
        if (update.hasCallbackQuery()) {
            String lastQuestionTopic=null;
            if(userMap.get(update.getCallbackQuery().getMessage().getChatId())!=null){
                lastQuestionTopic=userMap.get(update.getCallbackQuery().getMessage().getChatId()).getLastQuestionTopic();
            }
            else{
                userMap.put(update.getCallbackQuery().getMessage().getChatId(),new UserDto());
            }

            String message = update.getCallbackQuery().getData();
            String fullName = update.getCallbackQuery().getFrom().getFirstName().toLowerCase() + " " +
                    update.getCallbackQuery().getFrom().getLastName().toLowerCase();
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            if ("Confirm group".equals(lastQuestionTopic)) {
                if (message.equals("Yes")) {

                    Integer chatId = (int) (long) update.getCallbackQuery().getMessage().getChatId();
                    String query = "UPDATE user SET chatId  = ?  WHERE name = '" + fullName + "';";
                    PreparedStatement preparedStmt;
                    try {
                        preparedStmt = conn.prepareStatement(query);
                        preparedStmt.setInt(1, chatId);
                        preparedStmt.executeUpdate();
                        sendReply("You were successfully registered.Now enjoy ! /help", update.getCallbackQuery().getMessage().getChatId(), null);
                    } catch (SQLException | TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (update.getCallbackQuery().getData().equals("No")) {
                    try {
                        //todo get institutes from db and create buttons for them


                        String sqlQuery = "SELECT * FROM institute";
                        ResultSet resultSt = stmt.executeQuery(sqlQuery);

                        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

                        List<List<InlineKeyboardButton>> keyboard = new ArrayList<List<InlineKeyboardButton>>();
                        List<InlineKeyboardButton> row = new ArrayList<InlineKeyboardButton>();


                        markup.setKeyboard(keyboard);
                        while(resultSt.next()){
                            String institute=resultSt.getString("name");
                            InlineKeyboardButton button = new InlineKeyboardButton();
                            button.setText(institute);
                            button.setCallbackData(institute);
                            row.add(button);
                        }
                        keyboard.add(row);

                        SendMessage sendMessage =new SendMessage();
                        sendMessage.setText("Let's register you\nChoose your institute");
                        sendMessage.setReplyMarkup(markup);
                        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                        userMap.put(update.getCallbackQuery().getMessage().getChatId(),"Select institute");
                        sendMessage(sendMessage);
                    } catch (TelegramApiException | SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendReply("Select Yes or No !", update.getCallbackQuery().getMessage().getChatId(), lastQuestionTopic);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println();

            }
        } else if (messageNotEmpty) {
            String lastQuestionTopic = userMap.putIfAbsent(update.getMessage().getChatId(), null);
            String message = update.getMessage().getText();
            String firstName = update.getMessage().getFrom().getFirstName();
            String lastName = update.getMessage().getFrom().getLastName();
            String fullName = firstName.toLowerCase() + " " + lastName.toLowerCase();
            if (!mtelaArden && firstName.toLowerCase().equals("astghik")) {
                try {
                    mtelaArden = true;
                    sendReply("", update.getMessage().getChatId(), null);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (messageNotEmpty && message.equals("/start")) {
                conn = ConnectionRAU.getConnection();

                try {
                    Statement stmt = conn.createStatement();

                    String sqlQuery = "SELECT * FROM user WHERE chatId=" + update.getMessage().getChatId();
                    ResultSet resultSt = stmt.executeQuery(sqlQuery);
                    String name = null;
                    while (resultSt.next()) {
                        name = resultSt.getString("name");
                    }
                    if (name != null) {

                    }


                    String sql = "SELECT * FROM user WHERE name='" + fullName + "';";

                    ResultSet rs = stmt.executeQuery(sql);
                    UserEntity userEntity = null;
                    while (rs.next()) {
                        userEntity = new UserEntity(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                    }
                    if (userEntity == null) {
                        try {
                            sendReply("Let's register you\nChoose your institute\n ", update.getMessage().getChatId(), "Select institute");
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    } else {
                        if (userEntity.getChatId() == 0) {
                            String queryForUserDetails = "SELECT u.name,i.name,d.name,g.groupNum " +
                                    "FROM user u " +
                                    "INNER JOIN `group` g ON u.groupId = g.id " +
                                    "INNER JOIN department d ON g.deptId = d.id " +
                                    "INNER JOIN institute i ON i.id = d.instituteId " +
                                    "WHERE u.name = '" + fullName + "';";
                            ResultSet resultSet = stmt.executeQuery(queryForUserDetails);
                            UserDetailsDto userDetailsDto = null;
                            while (resultSet.next()) {
                                userDetailsDto = new UserDetailsDto(resultSet.getString(1),
                                        resultSet.getString(2), resultSet.getString(3),
                                        resultSet.getInt(4));
                            }
                            String text = "Is this your details?\nFull Name : " + userDetailsDto.getFullName() + "\n" +
                                    "Institute : " + userDetailsDto.getInstName() + "\n" +
                                    "Department : " + userDetailsDto.getDeptName() + "\n" +
                                    "Group : " + userDetailsDto.getGroupNum();
                            String topic = "Confirm group";
                            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

                            List<List<InlineKeyboardButton>> keyboard = new ArrayList<List<InlineKeyboardButton>>();
                            List<InlineKeyboardButton> row = new ArrayList<InlineKeyboardButton>();


                            InlineKeyboardButton buttonYes = new InlineKeyboardButton();
                            buttonYes.setText("Yes");
                            buttonYes.setCallbackData("Yes");
                            row.add(buttonYes);

                            InlineKeyboardButton buttonNo = new InlineKeyboardButton();
                            buttonNo.setText("No");
                            buttonNo.setCallbackData("No");
                            row.add(buttonNo);
                            keyboard.add(row);

                            markup.setKeyboard(keyboard);

                            SendMessage messageBut = new SendMessage();
                            messageBut.setText(text);
                            messageBut.setReplyMarkup(markup);
                            messageBut.setChatId(update.getMessage().getChatId());

                            userMap.put(update.getMessage().getChatId(), topic);

                            sendMessage(messageBut);
                        } else {
                            if (userEntity.getChatId() == (int) (long) update.getMessage().getChatId()) {
                                sendReply("You have already registered! If you want to reset it select /reset",
                                        update.getMessage().getChatId(), "ResetUser");
                                sendReply("You have already registered! If you want to reset it select /reset",
                                        update.getMessage().getChatId(), "ResetUser");
                            }
                        }


                    }

                } catch (SQLException | TelegramApiException e) {
                    e.printStackTrace();
                }

            } else if (messageNotEmpty && message.equals("/reset")) {
                try {
                    String query = "UPDATE user SET chatId  = ?  WHERE name = '" + fullName + "';";
                    PreparedStatement preparedStmt;

                        preparedStmt = conn.prepareStatement(query);
                        preparedStmt.setInt(1, 0);
                        preparedStmt.executeUpdate();

                    sendReply("Let's register you\nChoose your institute", update.getMessage().getChatId(), "Select institute");
                } catch (TelegramApiException | SQLException e) {
                    try {
                        sendReply("Something went wrong ! /help",update.getMessage().getChatId(),null);
                    } catch (TelegramApiException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }

            } else if ("Select institute".equals(lastQuestionTopic)) {

            }

        /*SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        String username = update.getMessage().getFrom().getUserName();


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();*/
        }
    }

    @Override
    public String getBotUsername() {
        return "Rau-bot";
    }

    @Override
    public String getBotToken() {
        return "445801312:AAF6dRhIoOvJ2f48LMXZU3EvpB7Jn_EsSSQ";
    }


}
