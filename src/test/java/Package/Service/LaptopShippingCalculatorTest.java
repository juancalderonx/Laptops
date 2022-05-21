package Package.Service;

import Package.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaptopShippingCalculatorTest {

    @Test
    void calculatePrice() {

        //Preparo el entorno a testear
        Laptop laptop1 = new Laptop(null,"ASUS","TUF GAMIG F15",78000d,true);
        Laptop laptop2 = new Laptop(null,"ASUS","TUF GAMIG F15",145000d,true);

        LaptopShippingCalculator calculator = new LaptopShippingCalculator();

        double price1 = calculator.calculatePrice(laptop1);
        double price2 = calculator.calculatePrice(laptop2);

        assertTrue(price1 > 0);
        assertTrue(price2 > 0);

        assertEquals(90000.0,price1);
        assertEquals(145000d,price2);

        System.out.println(price1);
        System.out.println(price2);

    }
}