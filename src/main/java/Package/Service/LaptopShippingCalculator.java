package Package.Service;

import Package.Entities.Laptop;
import org.springframework.stereotype.Service;

@Service
public class LaptopShippingCalculator {

    public double calculatePrice(Laptop laptop){

        double price = laptop.getPrice();
        double shipping = 12000d;

        if(price > 100000d){
            shipping = 0;
            price += shipping;
            return price;
        }

        price += shipping;
        return price;

    }

}
