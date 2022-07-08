package repository;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WeaponRepositoryTest {

    @Test
    void findAll() throws ParserConfigurationException, IOException, SAXException {
        WeaponRepository weaponRepository= new WeaponRepository();
        weaponRepository.findAll().forEach(System.out::println);
    }
}