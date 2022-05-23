package Package.Controllers;

import Package.Entities.Laptop;
import Package.Repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

//    @Value("${app.messageDev}")
//    String messageProperties;

    //Find all laptops and return laptops list
    @GetMapping("/getAllLaptops")
    @ApiOperation("This method is to find all laptops")
    public List<Laptop> findAllLaptops(){
//        System.out.println(messageProperties);
        return laptopRepository.findAll();
    }

    //Find laptop by ID
    @GetMapping("/getLaptop/{id}")
    @ApiOperation("This method is to find laptop by ID")
    public ResponseEntity<Laptop> findLaptopById(@PathVariable Long id){

        Optional<Laptop> laptopOption = laptopRepository.findById(id);

        if(laptopOption.isPresent()){
            return ResponseEntity.ok(laptopOption.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Receive a laptop object in JSON format from Postman
    @PostMapping("/createLaptop")
    @ApiOperation("This method is to create a laptop")
    public Laptop createLaptop(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }

    @PutMapping("/updateLaptop")
    @ApiOperation("This method is to modifying a laptop")
    public ResponseEntity<Laptop> updateCurrentLaptop(@RequestBody Laptop laptop){

        if(laptop.getId() == null){
            log.warn("¡Error! Trying to modify a laptop that doesn't exist");
            return ResponseEntity.badRequest().build();
        }

        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("¡Error! Trying to modify a laptop that doesn't exist");
            return ResponseEntity.notFound().build();
        }

        Laptop laptopsModified = laptopRepository.save(laptop);
        return ResponseEntity.ok(laptopsModified);
    }

    @DeleteMapping("/deleteLaptop/{id}")
    @ApiOperation("This method is to delete laptop by ID")
    public ResponseEntity<Laptop> deleteLaptopById(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a laptop that doesn't exist");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/deleteAllLaptops")
    @ApiOperation("This method is to delete all laptops")
    public ResponseEntity<Laptop> deleteAllLaptops(){

        long data = laptopRepository.count();

        if (data == 0){
            log.warn("¡Error! Trying to delete all laptops but no laptop exists in the database");
            return ResponseEntity.badRequest().build();
        }

        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
