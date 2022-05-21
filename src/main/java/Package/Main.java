package Package;

import Package.Controllers.LaptopController;
import Package.Entities.Laptop;
import Package.Repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        LaptopRepository repository = context.getBean(LaptopRepository.class);
        LaptopController obj1 = context.getBean(LaptopController.class);

        Laptop laptop1 = new Laptop(null,"ASUS","TUF GAMIG F15",1199.9,true);
        Laptop laptop2 = new Laptop(null,"HP","PAVILLION",1299.9,true);
        Laptop laptop3 = new Laptop(null,"ACER","NITRO 5",999.9,false);

        repository.save(laptop1);
        repository.save(laptop2);
        repository.save(laptop3);

    }

}
