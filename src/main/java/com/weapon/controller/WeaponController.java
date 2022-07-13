package com.weapon.controller;


import com.weapon.exeptionhandler.exceptions.SerialNotFoundException;
import com.weapon.model.Weapon;
import com.weapon.service.WeaponServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/weapons")
@AllArgsConstructor
public class WeaponController {
    //внедряем зависимость от WeaponService
    private final WeaponServiceImpl weaponService;

    @GetMapping
    public List<Weapon> findAllWeapons() throws ParserConfigurationException, IOException, SAXException {
        return weaponService.readAll();
    }


    @PostMapping
    public String create(@RequestBody Weapon weapon) throws SerialNotFoundException, ParserConfigurationException, IOException, TransformerException, SAXException {
        return weaponService.creat(weapon);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable int id) throws SerialNotFoundException, ParserConfigurationException, IOException, TransformerException, SAXException {
        return weaponService.delete(id);
    }
    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody Weapon weapon ) throws SerialNotFoundException, ParserConfigurationException, IOException, TransformerException, SAXException {
        return weaponService.update( weapon, id);
    }

    @GetMapping("/{id}")
    public Weapon getWeaponBySerial(@PathVariable int id) throws SerialNotFoundException, ParserConfigurationException, IOException, ClassNotFoundException, SAXException {
        return weaponService.read(id);
    }


    @GetMapping("information")
    public String information() throws ParserConfigurationException, IOException, SAXException {
        return String.format("Проект выполнила студент группы УВА-211 Ирина Радченко! кол-во объектов: %s", weaponService.readAll().size());
    }
}


