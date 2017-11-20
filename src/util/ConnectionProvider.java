package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {

    private static DataSource dataSource;

    public Connection getConnection() throws SQLException, NamingException {
        return getDataSource().getConnection();
    }

    public static DataSource getDataSource() throws NamingException {
        if (dataSource == null)
        {
            try {
            Context context = new InitialContext();
            Context con = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) con.lookup("jdbc/shareyourfind");
            dataSource = ds;
            } catch (NamingException e)
            {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

}
