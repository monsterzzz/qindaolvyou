import java.sql.*;

public class DataSource {
    final String URL = "jdbc:mysql://localhost:3306/qingdao";
    final String NAME = "root";
    final String PASSWORD = "123456";

    public String queryActionData(String base) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL,NAME,PASSWORD);
        Statement statement = connection.createStatement();

        String sql;
        if(base.equals("all")){
            sql = "SELECT * FROM actions";
        }else {
            sql = "SELECT * FROM actions WHERE want_time = " + "'" + base +"'";
        }
        ResultSet resultSet = statement.executeQuery(sql);
        StringBuilder stringBuilder = new StringBuilder();
        
        while (resultSet.next()){
            stringBuilder.append(resultSet.getInt("uid"));
            stringBuilder.append(",");
            stringBuilder.append(resultSet.getInt("want_time"));
            stringBuilder.append(",");
            stringBuilder.append(resultSet.getInt("spot_id"));
            stringBuilder.append("\n");
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

}
