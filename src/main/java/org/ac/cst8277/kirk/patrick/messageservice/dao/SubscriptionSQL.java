package org.ac.cst8277.kirk.patrick.messageservice.dao;

public final class SubscriptionSQL {
    public static final String INSERT =
            "INSERT INTO ms.subscription (subscriber_id, publisher_id) VALUES (?, ?);";
    public static final String DELETE =
            "DELETE FROM ms.subscription WHERE subscriber_id = ? AND publisher_id = ?;";
    public static final String GET_FOR_PUBLISHER =
            "SELECT subscriber_id FROM ms.subscription WHERE publisher_id = ?;";
}
