import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
            try {
                switch (opcio) {
                    case 0:llistarIdsCursosTutorsAlumnes(); break;
                    case 1: PR141Main.main(args); break;
                    case 2: PR142Main.main(args); break;
                    
                    // Adapta aquí les altres classes de l’exercici (PR122cat…)
                    case 100: running = false; break;
                    default: break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    static public String llegirLinia (String text) {
        System.out.print(text);
        return in.nextLine();
    }

    static public void mostrarIdTitolperIdCurs(String idCurs){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
            // Analitza el fitxer XML
            Document doc = dBuilder.parse("./data/cursos.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();






            
            // Obté una llista de tots els elements "student" del document
            NodeList listCursos = doc.getElementsByTagName("curs");
            String sep = "     ";
            String columnFormat = "%-10s" + sep + "  %-20s %15s%n";
            System.out.printf(columnFormat, "Id Curs", "Tutor", "Total alumnes");
            for ( int i = 0; i < listCursos.getLength(); i++ ) {
                Node nodeCurs = listCursos.item(i);
                if (nodeCurs.getNodeType() == Node.ELEMENT_NODE){
                    Element elm = (Element) nodeCurs;
                    String id = elm.getAttribute("id");
                    if (id.equals(idCurs)){
                        String tutor = elm.getElementsByTagName("tutor").item(0).getTextContent();
                        String totalAlumnes = elm.getElementsByTagName("alumne").getLength() + "";
                    System.out.printf(columnFormat, 
                        id, tutor, totalAlumnes);
                    }
                }
            }

        } catch(Exception e) {
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
            for ( int i = 0; i < listCursos.getLength(); i++ ) {
                Node nodeCurs = listCursos.item(i);
                if (nodeCurs.getNodeType() == Node.ELEMENT_NODE){
                    Element elm = (Element) nodeCurs;
                    String id = elm.getAttribute("id");
                    String tutor = elm.getElementsByTagName("tutor").item(0).getTextContent();
                    String totalAlumnes = elm.getElementsByTagName("alumne").getLength() + "";
                System.out.printf(columnFormat, 
                    id, tutor, totalAlumnes);
                }
            }

        } catch(Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }  
    }
}
