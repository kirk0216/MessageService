package org.ac.cst8277.kirk.patrick.messageservice.dao;

import org.ac.cst8277.kirk.patrick.messageservice.Utils;
import org.ac.cst8277.kirk.patrick.messageservice.model.Message;
import org.ac.cst8277.kirk.patrick.messageservice.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySQLMessageDatabase implements MessageDatabase {
    private static final String CONNECTION_STRING = "jdbc:mysql://mysql:3306/ms";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    private Connection connection;

    @Override
    public void open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, DB_USER, DB_PASS);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertMessage(Message message) {
        try {
            PreparedStatement statement = connection.prepareStatement(MessageSQL.INSERT);
            statement.setBytes(1, Utils.toBytes(message.getId()));
            statement.setBytes(2, Utils.toBytes(message.getAuthorId()));
            statement.setString(3, message.getContent());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMessage(Message message) {
        try {
            PreparedStatement statement = connection.prepareStatement(MessageSQL.UPDATE);
            statement.setBytes(1, Utils.toBytes(message.getAuthorId()));
            statement.setString(2, message.getContent());
            statement.setBytes(3, Utils.toBytes(message.getId()));
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMessage(UUID messageId) {
        try {
            PreparedStatement statement = connection.prepareStatement(MessageSQL.DELETE);
            statement.setBytes(1, Utils.toBytes(messageId));
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(MessageSQL.GET_ALL);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UUID messageId = Utils.toUUID(result.getBytes("id"));
                UUID authorId = Utils.toUUID(result.getBytes("author_id"));
                String content = result.getString("content");
                Date date = result.getDate("date");

                messages.add((new Message(messageId, authorId, content, date)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public void insertPublisher(UUID id) {

    }

    @Override
    public void deletePublisher(UUID id) {

    }

    @Override
    public boolean isPublisher(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement(PublisherSQL.IS_PUBLISHER);
            statement.setBytes(1, Utils.toBytes(id));
            return statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Message> getMessagesByPublisher(UUID publisherId) {
        List<Message> messages = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(MessageSQL.GET_BY_PUBLISHER);
            statement.setBytes(1, Utils.toBytes(publisherId));
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UUID messageId = Utils.toUUID(result.getBytes("id"));
                UUID authorId = Utils.toUUID(result.getBytes("author_id"));
                String content = result.getString("content");
                Date date = result.getDate("date");

                messages.add((new Message(messageId, authorId, content, date)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public List<Message> getMessagesForSubscriber(UUID subscriberId) {
        List<Message> messages = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(MessageSQL.GET_FOR_SUBSCRIBER);
            statement.setBytes(1, Utils.toBytes(subscriberId));
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UUID messageId = Utils.toUUID(result.getBytes("id"));
                UUID authorId = Utils.toUUID(result.getBytes("author_id"));
                String content = result.getString("content");
                Date date = result.getDate("date");

                messages.add((new Message(messageId, authorId, content, date)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public void insertSubscription(Subscription subscription) {
        try {
            PreparedStatement statement = connection.prepareStatement(SubscriptionSQL.INSERT);
            statement.setBytes(1, Utils.toBytes(subscription.getSubscriberId()));
            statement.setBytes(2, Utils.toBytes(subscription.getPublisherId()));
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubscription(Subscription subscription) {
        try {
            PreparedStatement statement = connection.prepareStatement(SubscriptionSQL.DELETE);
            statement.setBytes(1, Utils.toBytes(subscription.getSubscriberId()));
            statement.setBytes(2, Utils.toBytes(subscription.getPublisherId()));
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UUID> getSubscribersTo(UUID publisherId) {
        List<UUID> ids = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SubscriptionSQL.GET_FOR_PUBLISHER);
            statement.setBytes(1, Utils.toBytes(publisherId));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID subscriberId = Utils.toUUID(resultSet.getBytes("subscriber_id"));
                ids.add(subscriberId);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ids;
    }
}
