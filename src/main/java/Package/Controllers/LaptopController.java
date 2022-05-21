package Package.Controllers;

import Package.Entities.Laptop;
import Package.Repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor @Getter @Setter
public class LaptopController {

    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    //Find all laptops and return laptops list
    @GetMapping("/laptops")
    @ApiOperation("This method is to find all laptops")
    public List<Laptop> findAllLaptops(){
        return laptopRepository.findAll();
    }

    //Find laptop by ID
    @GetMapping("/laptops/{id}")
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
    @PostMapping("/laptops")
    @ApiOperation("This method is to create a laptop")
    public Laptop createLaptop(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }

    @PutMapping("/laptops")
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

    @DeleteMapping("/laptops/{id}")
    @ApiOperation("This method is to delete laptop by ID")
    public ResponseEntity<Laptop> deleteLaptopById(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a laptop that doesn't exist");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/laptops")
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
