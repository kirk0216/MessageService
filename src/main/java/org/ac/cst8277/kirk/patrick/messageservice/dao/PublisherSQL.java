package org.ac.cst8277.kirk.patrick.messageservice.dao;

public final class PublisherSQL {
    public static final String INSERT =
            "INSERT INTO ms.publisher (id) VALUES (?);";
    public static final String DELETE =
            "DELETE FROM ms.publisher WHERE id = ?;";
    public static final String IS_PUBLISHER =
            "SELECT COUNT(*) AS COUNT FROM ms.publisher WHERE id = ?;";
}
