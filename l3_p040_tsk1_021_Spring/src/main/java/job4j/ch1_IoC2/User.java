package job4j.ch1_IoC2;

public class User {
   private int id;
   private String login;
   private String password;
   private String name;
   private String email;

   public User (int id, String login, String password, String name, String email) {
      this.id = id;
      this.login = login;
      this.password = password;
      this.name = name;
      this.email = email;
   }

   public int getId() {
      return id;
   }

   public String getLogin() {
      return login;
   }

   public String getPassword() {
      return password;
   }

   public String getName() {
      return name;
   }

   public String getEmail() {
      return email;
   }

}
