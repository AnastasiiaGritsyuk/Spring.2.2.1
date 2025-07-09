package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Toyota Camry", 2020);
      Car car2 = new Car("BMW X5", 2019);
      Car car3 = new Car("Mazda 3", 2021);
      Car car4 = new Car("Nissan Qashqai", 2018);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);
      userService.add(user1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);
      userService.add(user2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(car3);
      userService.add(user3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(car4);
      userService.add(user4);

      System.out.println("==== All Users ====");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() +
                 ", series " + user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("==== Search for users by car ====");
      String modelToFind = "BMW X5";
      int seriesToFind = 2019;

      User userWithCar = userService.getUserByCar(modelToFind, seriesToFind);
      if (userWithCar != null) {
         System.out.println("User with a car was found: " +
                 modelToFind + " " + seriesToFind + ":");
         System.out.println("Id = " + userWithCar.getId());
         System.out.println("First Name = " + userWithCar.getFirstName());
         System.out.println("Last Name = " + userWithCar.getLastName());
         System.out.println("Email = " + userWithCar.getEmail());
      } else {
         System.out.println("User with a car " +
                 modelToFind + " " + seriesToFind + " was not found.");
      }

      context.close();
   }
}
