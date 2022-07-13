package com.weapon.service;

import com.weapon.exeption.exceptions.SerialNotFoundException;
import com.weapon.model.Weapon;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface WeaponService {
    /**
     * Создает новое оружие
     *
     * @param weapon - оружие для создания
     * @return
     */
    String creat(Weapon weapon) throws ParserConfigurationException, IOException, TransformerException, SAXException, SerialNotFoundException;
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
    Weapon read(int id) throws ParserConfigurationException, IOException, ClassNotFoundException, SAXException, SerialNotFoundException;

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param weapon - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Weapon weapon, int id) throws ParserConfigurationException, IOException, SAXException, TransformerException, SerialNotFoundException;

    /**
     * Удаляет клиента с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id) throws ParserConfigurationException, IOException, TransformerException, SAXException, SerialNotFoundException;

}