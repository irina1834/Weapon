package repository;

import model.Weapon;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.springframework.stereotype.Repository;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

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
@Repository
public class WeaponRepository {

    public List<Weapon> findAll() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Weapon> weapons = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File("src/main/resources/data.xml"));
        NodeList weaponsElement=document.getDocumentElement().getElementsByTagName("weapon");
        // Перебор всех элементов employee
        for (int i = 0; i < weaponsElement.getLength(); i++) {
            Node employee = weaponsElement.item(i);
            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = employee.getAttributes();
            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
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
    public void roomWhriteAll(List<Room> list) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("rooms");
        doc.appendChild(rootElement);



        for (Room r: list
        ) {
            Element staff = doc.createElement("staff");
            // add staff to root
            rootElement.appendChild(staff);
            staff.setAttribute("numberFloor", String.valueOf(r.getNumberFloor());
            staff.setAttribute("name", "45");
        }
        // add xml attribute
        staff.setAttribute("id", "1001");
        staff.setAttribute("name", "45");

        //...create XML elements, and others...

        // write dom document to a file
        try (FileOutputStream output =
                     new FileOutputStream("data.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
