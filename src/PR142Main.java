import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class PR142Main {
    static Scanner in = new Scanner(System.in); // System.in és global

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            String menu = "Escull una opció:";
            menu = menu + "\n 0) Llistar ids de cursos, tutors i total d'alumnes";
            menu = menu + "\n 1) Mostrar ids i titols dels mòduls a partir d'un id de curs";
            menu = menu + "\n 2) Llistar alumnes d'un curs";
            menu = menu + "\n 3) Afegir alumne a un curs";
            menu = menu + "\n 4) Eliminar alumne d'un curs";

            // Adapta aquí les altres classes de l’exercici (PR122cat…)
            menu = menu + "\n 100) Sortir";
            System.out.println(menu);

            int opcio = Integer.valueOf(llegirLinia("Opció:"));
            System.out.println("");
            try {
                switch (opcio) {
                    case 0:
                        llistarIdsCursosTutorsAlumnes();
                        break;
                    case 1:
                        mostrarIdTitolperIdCurs();
                        ;
                        break;
                    case 2:
                        llistarAlumnes();
                        break;
                    case 3:
                        afegirAlumneCurs();
                        break;
                    // Adapta aquí les altres classes de l’exercici (PR122cat…)
                    case 100:
                        running = false;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("");
        }
    }

    static public String llegirLinia(String text) {
        System.out.print(text);
        return in.nextLine();
    }

    static public void mostrarIdTitolperIdCurs() {
        try {

            String idCurs = String.valueOf(llegirLinia("ID del curs:"));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analitza el fitxer XML
            Document doc = dBuilder.parse("./data/cursos.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();

            String expresionModuls = "/cursos/curs[@id='" + idCurs + "']/moduls/modul";
            NodeList listExpression = (NodeList) xPath.compile(expresionModuls).evaluate(doc, XPathConstants.NODESET);

            System.out.println("Llista de moduls del curs " + idCurs);
            for (int i = 0; i < listExpression.getLength(); i++) {
                Node node = listExpression.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element) node;
                    String id = elm.getAttribute("id");
                    String titol = elm.getElementsByTagName("titol").item(0).getTextContent();
                    System.out.println("ID: " + id + " TITOL: " + titol);
                }
            }
        } catch (Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }
    }

    static public void llistarIdsCursosTutorsAlumnes() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analitza el fitxer XML
            Document doc = dBuilder.parse("./data/cursos.xml");

            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();

            // Obté una llista de tots els elements "student" del document
            NodeList listCursos = doc.getElementsByTagName("curs");
            String sep = "     ";
            String columnFormat = "%-10s" + sep + "  %-20s %15s%n";
            System.out.printf(columnFormat, "Id Curs", "Tutor", "Total alumnes");
            for (int i = 0; i < listCursos.getLength(); i++) {
                Node nodeCurs = listCursos.item(i);
                if (nodeCurs.getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element) nodeCurs;
                    String id = elm.getAttribute("id");
                    String tutor = elm.getElementsByTagName("tutor").item(0).getTextContent();
                    String totalAlumnes = elm.getElementsByTagName("alumne").getLength() + "";
                    System.out.printf(columnFormat,
                            id, tutor, totalAlumnes);
                }
            }

        } catch (Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }
    }

    static public void llistarAlumnes() {
        try {

            String idCurs = String.valueOf(llegirLinia("ID del curs:"));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analitza el fitxer XML
            Document doc = dBuilder.parse("./data/cursos.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();

            String expresionModuls = "/cursos/curs[@id='" + idCurs + "']/alumnes/alumne";
            NodeList listExpression = (NodeList) xPath.compile(expresionModuls).evaluate(doc, XPathConstants.NODESET);

            System.out.println("Llista d'alumnes del curs " + idCurs);
            for (int i = 0; i < listExpression.getLength(); i++) {
                Node node = listExpression.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element) node;
                    String nom = elm.getTextContent();

                    System.out.println("Nom: " + nom);
                }
            }
        } catch (Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }
    }

    static public void afegirAlumneCurs() {
        try {
            String idCurs = String.valueOf(llegirLinia("ID del curs:"));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analitza el fitxer XML
            Document doc = dBuilder.parse("./data/cursos.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();

            doc.getDocumentElement().normalize();

            String expresionModuls = "/cursos/curs[@id='" + idCurs + "']/alumnes";
            NodeList listExpression = (NodeList) xPath.compile(expresionModuls).evaluate(doc, XPathConstants.NODESET);

            String nouAlumne = String.valueOf(llegirLinia("Nom del nou alumne:"));

            Element nouAlumneElement = doc.createElement("alumne");
            Text nodeTextAlumne = doc.createTextNode(nouAlumne);
            nouAlumneElement.appendChild(nodeTextAlumne);

            Node node = listExpression.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elm = (Element) node;
                elm.appendChild(nouAlumneElement);
            }
            write("./data/cursos.xml", doc);
        } catch (Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }
    }
        static public void write(String path, Document doc) throws TransformerException, IOException {
        if (!new File(path).exists()) {
            new File(path).createNewFile();
        }

        // Crea una factoria de transformadors XSLT
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // Crea un transformador XSLT
        Transformer transformer = transformerFactory.newTransformer();
        // Estableix la propietat OMIT_XML_DECLARATION a "no" per no ometre la
        // declaració XML del document XML resultant
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        // Estableix la propietat INDENT a "yes" per indentar el document XML resultant
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // Elimina els espais en blanc innecessaris del document XML. Implementació
        // pròpia
        // Crea una instància de DOMSource a partir del document XML
        DOMSource source = new DOMSource(doc);
        // Crea una instància de StreamResult a partir del camí del fitxer XML
        StreamResult result = new StreamResult(new File(path));
        // Transforma el document XML especificat per source i escriu el document XML
        // resultant a l'objecte especificat per result
        transformer.transform(source, result);
    }
    
}
