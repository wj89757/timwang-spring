package com.timwang.spring.other.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author wangjun
 * @date 2020-10-25
 */
public class TransactionUtil {
    public static final ThreadLocal<Connection> syncConnections = new ThreadLocal<>();
    private static Logger logger = LoggerFactory.getLogger(TransactionUtil.class);

    private TransactionUtil() {

    }

    public static Connection startTransaction() {
        logger.info("start transaction...");
        Connection connection = syncConnections.get();
        if (connection == null) {
            try {
                logger.info("get a new connection");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8", "root", "123456");
                syncConnections.set(connection);
                connection.setAutoCommit(false);
            } catch (Exception e) {
                logger.error("ex", e);
            }
        }
        return connection;
    }

    public static int execute(String sql, Object... args) {
        Connection connection = startTransaction();
        if (connection == null) {
            return 0;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            logger.info("connect exec success...");
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("ex", e);
        }
        return 0;
    }

    public static void commit() {
        try (Connection connection = syncConnections.get()) {
            connection.commit();
            logger.info("connection commit success");
            syncConnections.remove();
        } catch (Exception e) {
            logger.error("ex", e);
        }
    }

    public static void rollback() {
        try (Connection connection = syncConnections.get()) {
            connection.rollback();
            logger.info("connection rollback success");
            syncConnections.remove();
        } catch (Exception e) {
            logger.error("ex", e);
        }
    }
}
