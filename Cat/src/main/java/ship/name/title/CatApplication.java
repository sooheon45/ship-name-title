package ship.name.title;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
public class CatApplication {

    protected static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(CatApplication.class, args);
    }
}
