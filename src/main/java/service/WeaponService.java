package service;

import model.Weapon;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface WeaponService {
    /**
     * Создает новое оружие
     * @param weapon - оружие для создания
     */
    void creat( Weapon weapon);
    /**
     * Возвращает список всех имеющихся оружий
     * @return список клиентов
     */
    List<Weapon> readAll() throws ParserConfigurationException, IOException, SAXException;

    /**
     * Возвращает клиента по его ID
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    Weapon read(int id);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param weapon - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Weapon weapon, int id);

    /**
     * Удаляет клиента с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);

}
