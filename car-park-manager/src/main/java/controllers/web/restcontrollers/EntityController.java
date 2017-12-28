package controllers.web.restcontrollers;

import entities.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import services.BusService;

import java.util.List;

@RestController
@RequestMapping("/rest/entity")
public class EntityController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    BusService busService;

    /**
     * Get method, return List of Bus.
     * @return ResponseEntity<List<Bus>>
     */
    @RequestMapping(value = "/getBuses", method = RequestMethod.GET)
    public ResponseEntity<List<Bus>> getBusesController(){
        return new ResponseEntity<>(busService.getBuses(), HttpStatus.CREATED );
    }

    /**
     * Get method. This method get id from URL and return Bus with id = id.
     * @param id Integer value
     * @return ResponseEntity<Bus>
     */
    @RequestMapping(value = "/getBus/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable(value = "id") Integer id){
        return new ResponseEntity<>(busService.getBuses().get(id), HttpStatus.CREATED );
    }
}
