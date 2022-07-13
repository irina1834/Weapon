package com.weapon.repository;

import com.weapon.model.Weapon;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WeaponRepository {
    //метод, который читает файл data.xml и преобразовывает его в массив сущностей (weapon)
    public static List<Weapon> findAll() throws ParserConfigurationException, IOException, SAXException {
        // Список для оружий из XML файла
        ArrayList<Weapon> weapons = new ArrayList<>();
        // Получение фабрики, чтобы после получить билдер документов
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно
        Document document = builder.parse(new File("src/main/resources/data.xml"));
        NodeList weaponsElement = document.getDocumentElement().getElementsByTagName("weapon");
        // Перебор всех элементов
        for (int i = 0; i < weaponsElement.getLength(); i++) {
            Node employee = weaponsElement.item(i);
            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = employee.getAttributes();
            // Добавление оружия. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            weapons.add(new Weapon(
                    Integer.parseInt(attributes.getNamedItem("id").getNodeValue()),
                    attributes.getNamedItem("type").getNodeValue(),
                    attributes.getNamedItem("name").getNodeValue(),
                    Integer.parseInt(attributes.getNamedItem("firingRange").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("storeCapacity").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("weight").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("rateOfFire").getNodeValue())
            ));
        }
        return weapons;
    }

    public Weapon findById(int id) throws ClassNotFoundException, ParserConfigurationException, IOException, SAXException {
        final List<Weapon> weaponList = findAll();
        for (Weapon weapon : weaponList
        ) {
            if (id == weapon.getId()) return weapon;
        }

        throw new ClassNotFoundException();
    }

    public void save(Weapon weapon) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        List<Weapon> weapons = findAll();
        weapons.add(weapon);
        weaponWriteAll(weapons);
    }

    public void update(Weapon weapon, int id) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        List<Weapon> weapons = findAll();
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getId() == id) {
                weapons.set(i,weapon);
            }
        }
        weaponWriteAll(weapons);

    }

    public void deleteById(int id) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        final List<Weapon> weapons = findAll().stream()
                .filter(w -> w.getId() != id)
                .collect(Collectors.toList());
        weaponWriteAll(weapons);
    }

    //метод, который перезаписывает файл и добавляет в него массив переданных сущностей
    private static void weaponWriteAll(List<Weapon> list) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("weapons");
        doc.appendChild(rootElement);

        for (Weapon w : list
        ) {
            Element weapon = doc.createElement("weapon");
            rootElement.appendChild(weapon);

            weapon.setAttribute("type", String.valueOf(w.getType()));
            weapon.setAttribute("name", String.valueOf(w.getName()));
            weapon.setAttribute("firingRange", String.valueOf(w.getFiringRange()));
            weapon.setAttribute("storeCapacity", String.valueOf(w.getStoreCapacity()));
            weapon.setAttribute("weight", String.valueOf(w.getWeight()));
            weapon.setAttribute("rateOfFire", String.valueOf(w.getRateOfFire()));
            weapon.setAttribute("id", String.valueOf(w.getId()));
        }


        // write dom document to a file
        try (FileOutputStream output =
                     new FileOutputStream("src/main/resources/data.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //проверяет существует ли обьект с переданным id
    public static boolean selectExistWeaponById(int id) throws ParserConfigurationException, IOException, SAXException {
        final List<Weapon> all = findAll();
        for (Weapon weapon: all
        ) {
            if (weapon.getId() == id) return true;
        }
        return false;
    }
    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
