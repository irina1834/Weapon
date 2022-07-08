package service;

import model.Weapon;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import repository.WeaponRepository;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WeaponServiceImpl implements WeaponService {
    private final WeaponRepository weaponRepository;
    //Хранилище клиентов
    private static final Map<Integer, Weapon> WEAPON_REPOSITORY_MAP=new HashMap<>();

    public WeaponServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    //Переменная для генерации ID оружия
    private static final AtomicInteger WEAPON_ID_HOLDER=new AtomicInteger();

    @Override
    public void creat(Weapon weapon) {
        final int weaponId= WEAPON_ID_HOLDER.incrementAndGet();
        weapon.setId(weaponId);
        WEAPON_REPOSITORY_MAP.put(weaponId, weapon);
    }

    @Override
    public List<Weapon> readAll() throws ParserConfigurationException, IOException, SAXException {
        return weaponRepository.findAll();
    }

    @Override
    public Weapon read(int id) {
        return WEAPON_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Weapon weapon, int id) {
        if (WEAPON_REPOSITORY_MAP.containsKey(id)){
            weapon.setId(id);
            WEAPON_REPOSITORY_MAP.put(id, weapon);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return WEAPON_REPOSITORY_MAP.remove(id)!=null ;
    }
}
