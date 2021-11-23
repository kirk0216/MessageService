package org.ac.cst8277.kirk.patrick.messageservice.dao;

public final class MessageSQL {
    public static final String INSERT =
            "INSERT INTO ms.message (id, author_id, content) VALUES (?, ?, ?);";
    public static final String UPDATE =
            "UPDATE ms.message SET author_id = ?, content = ? WHERE id = ?;";
    public static final String DELETE =
            "DELETE FROM ms.message WHERE id = ?;";
    public static final String GET_ALL =
            "SELECT * FROM ms.message;";
    public static final String GET_BY_PUBLISHER =
            "SELECT * FROM ms.message WHERE author_id = ?;";
    public static final String GET_FOR_SUBSCRIBER =
            "SELECT * FROM ms.subscription s " +
            "LEFT JOIN ms.message m ON m.author_id = s.publisher_id " +
            "WHERE s.subscriber_id = ?;";
}
