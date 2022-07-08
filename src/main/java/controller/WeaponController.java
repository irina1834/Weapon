package controller;

import model.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import service.WeaponService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/weapons")
public class WeaponController {
    private final WeaponService weaponService;
    @Autowired
    public WeaponController(WeaponService weaponService){
        this.weaponService=weaponService;
    }
    @PostMapping(value = "/weapons")
    public ResponseEntity<?>create(@RequestBody Weapon weapon){
        weaponService.creat(weapon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/read")
    public List<Weapon> read() throws ParserConfigurationException, IOException, SAXException {
        return weaponService.readAll();
    }
    @GetMapping(value = "/weapons{id}")
    public ResponseEntity<List<Weapon>>read(@PathVariable(name="id") int id){
        final Weapon weapon=weaponService.read(id);

        return weapon!=null
                ? new  ResponseEntity<>(HttpStatus.OK)
                : new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/weapons{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Weapon weapon) {
        final boolean updated = weaponService.update(weapon, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/weapons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = weaponService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
