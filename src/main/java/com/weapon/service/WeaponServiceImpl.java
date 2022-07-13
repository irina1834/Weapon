package com.weapon.service;

import com.weapon.exeptionhandler.exceptions.SerialNotFoundException;
import lombok.AllArgsConstructor;
import com.weapon.model.Weapon;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import com.weapon.repository.WeaponRepository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class WeaponServiceImpl  {
    //внедряем зависимость от WeaponRepository
    private final WeaponRepository weaponRepository;

    public String creat(Weapon weapon) throws ParserConfigurationException, IOException, TransformerException, SAXException, SerialNotFoundException {
        if (WeaponRepository.selectExistWeaponById(weapon.getId())) {
            throw new SerialNotFoundException(String.format("Weapon with id: %s doesn't exist", weapon.getId()));
        }
        weaponRepository.save(weapon);
        return null;
    }

    public List<Weapon> readAll() throws ParserConfigurationException, IOException, SAXException {
        return WeaponRepository.findAll();
    }

    public Weapon read(int id) throws ParserConfigurationException, IOException, ClassNotFoundException, SAXException, SerialNotFoundException {
        if (!WeaponRepository.selectExistWeaponById(id)) {
            throw new SerialNotFoundException(String.format("Weapon with id: %s doesn't exist", id));
        }
        return weaponRepository.findById(id);
    }

    public boolean update(Weapon weapon, int id) throws ParserConfigurationException, IOException, SAXException, TransformerException, SerialNotFoundException {
        if (!WeaponRepository.selectExistWeaponById(id)) {
            throw new SerialNotFoundException(String.format("Weapon with id: %s doesn't exist", id));
        }
        weaponRepository.update(weapon,id);
        return true;
    }

    public boolean delete(int id) throws ParserConfigurationException, IOException, TransformerException, SAXException, SerialNotFoundException {
        if (!WeaponRepository.selectExistWeaponById(id)) {
            throw new SerialNotFoundException(String.format("Weapon with id: %s doesn't exist", id));
        }
        weaponRepository.deleteById(id);
        return true;
    }
}