package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User u1 = new User("Далай", "Лама", "user1@mail.ru");
      User u2 = new User("Риши", "Сунак", "user2@mail.ru");
      User u3 = new User("Нельсон", "Мандела", "user3@mail.ru");

      Car zil = new Car("Зил", 130);
      Car packard = new Car("Пакард", 54);
      Car lorraineDietrich= new Car("Лорен-Дитрих", 14);

      userService.add(u1.setCar(zil).setUser(u1));
      userService.add(u2.setCar(packard).setUser(u2));
      userService.add(u3.setCar(lorraineDietrich).setUser(u3));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("car = "+user.getCar());

         System.out.println( userService.getUserByCar("Зил", 130));
      }


      context.close();
   }
}
