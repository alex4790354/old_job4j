package ru.job4j.ch1_IoC2;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

/*import java.util.Properties;
import java.io.InputStream;
import java.io.InputStream;*/

public class ModuleDAO {
   private static String url;
   private static String username;
   private static String password ;
   private int amount;
   private static ModuleDAO instance = null;
   private Connection connection = null;    // use DataSource

   // По какой-то причине эта часть не отрабатывает.
   private ModuleDAO() {
      url = "jdbc:postgresql://localhost:5432/cars01";
      username = "alex";
      password = "password";
   }

   public static synchronized ModuleDAO INSTANCE() {
      if (instance == null) {
         System.out.println("instance == null; ModuleDAO = ModuleDAO();");
         instance = new ModuleDAO();
         //instance.connect();


         // Нужно ли мне ради этого понижать Language lvl до 5?
         /*try (InputStream in = ModuleDAO.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                  config.getProperty("username"),
                  config.getProperty("password"),
                  config.getProperty("url")
            );
         } catch (Exception e) {
            throw new IllegalStateException(e);
         }*/

      }
      return instance;
   }

   public Connection getConnection() {
      if (connection == null) {
         try {
            connection = DriverManager.getConnection(url, username, password);
                  //"jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "raduga");
         } catch (SQLException e) {
            System.err.println(String.format("Error getting connection: %s", e.getMessage()));
         }
      }
      return connection;
   }

   public void closeConnection() {
      try {
         if (connection != null) {
            int i = 0;
            connection.close();
            connection = null;
            System.out.println("##174: ModuleDAO.closeConnection");
         }
      } catch (Exception e) {
         System.err.println(String.format("##112 Error in closeConnection = %s", e.getMessage()));
      }
   }

   public boolean execSQL(final String sql) {
      boolean result = false;
      if (connection == null) {
         //System.err.println(String.format("##552 Error при коннекте к БД: sql %s", sql));
         connection = getConnection();
      }
      if (connection == null) {
         System.err.println("##261 con_avent == null");
         return false;
      }
      try {
         //if (getConnection() != null) {
         Statement statement = connection.createStatement();
         statement.execute(sql);
         // Наверное нам пока не нужно делать commit()
         // connection.commit();
         statement.close();
         //statement = null;
         result = true;
         //}
      } catch (SQLException e) {
         System.err.println("##277 SQLException : code = " + String.valueOf(e.getErrorCode()) +
               " - " + e.getMessage());
         System.err.println("SQL : " + sql);
      }
      return result;
   }


   public String getStringFromSQL(final String sql) {
      String result = null;
      Statement stat;
      ResultSet rs;
      if (connection == null) {
         //System.err.println(String.format("##552 Error при коннекте к БД: sql %s", sql));
         connection = getConnection();
      }
      if (connection == null) {
         System.err.println("##233 con_avent == null");
         return null;
      }
      try {
         //if (getConnection() != null) {
         stat = connection.createStatement();
         rs = stat.executeQuery(sql);
         rs.next();
         result = rs.getString(1);
         stat.close();
         //stat = null;
         rs.close();
         //}
      } catch (SQLException e) {
         System.err.println("SQLException : code = " + String.valueOf(e.getErrorCode()) +
               " - " + e.getMessage());
         System.err.println("SQL : " + sql);
      }
      return result;
   }

   public int getIntFromSQL(final String sql) {
      int result = -1;
      String resultStr = getStringFromSQL(sql);
      try {
         result = Integer.parseInt(resultStr);
      } catch (Exception e) {
         System.err.println(String.format("Error in parsing for sql = %s. Erros : %s", sql, e.getMessage() ));
      }
      return result;
   }


   //  ###########################################################################################################################
   //  # Это было только для того что бы протестировать создание таблицы в случае ее отсутсвия. Можно удалить за ненадобностью   #
   //  ###########################################################################################################################

   /*public static void main(String[] args) {
      try {
         Class.forName("org.postgresql.Driver");
         System.out.println("Полет нормальный");
         try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/cars01", "alex", "password");
            Statement st  = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM   pg_catalog.pg_class c WHERE  c.relname = 'users'  AND c.relkind = 'r'");
            int count = 0;
            while (rs.next()) {
               count = rs.getInt(1);
            }
            if (count == 0) {
               System.err.println("Ошибка таблица не создана");
               rs.close();

               st.execute("CREATE TABLE public.users " +
                     " ( id SERIAL PRIMARY KEY, " +
                     "   login character varying(50), " +
                     "   password character varying(50), " +
                     "   name character varying(200), " +
                     "   inserted_date TIMESTAMP, " +
                     "   email character varying(200) )" );
               System.err.println("1");
               st.execute("INSERT INTO users (login, password, name, inserted_date, email) VALUES('alex', '123', 'Alex', CURRENT_TIMESTAMP(0), 'alex@mail.ru') ");
               System.err.println("2");
               st.execute("INSERT INTO users (login, password, name, inserted_date, email) VALUES('agent007', '123', 'Agent 007', CURRENT_TIMESTAMP(0), '007@mail.ru') ");
               System.err.println("3");
               st.execute("INSERT INTO users (login, password, name, inserted_date, email) VALUES('agent009', '123', 'Agent KGB', CURRENT_TIMESTAMP(0), 'kgb@mail.ru') ");
               System.err.println("4");

               st.close();
               conn.close();
               System.out.println("Создали объекты заново");

            } else {
               System.out.println("Таблица есть");
            }
         } catch (SQLException e) {
            System.err.println(String.format("Ошибка в SQL: %s", e.getMessage()));
         } catch (Exception e) {
            System.err.println(String.format("Ошибка в драйвере соединения: %s", e.getMessage()));
         }

      } catch (ClassNotFoundException e) {
         System.out.println("Не смог соедениться с базой");
         System.exit(-1);
      }
   }*/

}
