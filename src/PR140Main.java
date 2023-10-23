import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class PR140Main {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
            // Analitza el fitxer XML
            Document doc = dBuilder.parse("./data/persones.xml");
        
            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();
        
            // Obté una llista de tots els elements "student" del document
            NodeList listPersones = doc.getElementsByTagName("persona");

            String columnFormat = "%-10s %-10s %5s" + "      "+ "%-15s%n";
            System.out.printf(columnFormat, "Nom", "Cognom", "Edat", "Ciutat");
            for ( int i = 0; i < listPersones.getLength(); i++ ) {
                System.out.printf(columnFormat, 
                    doc.getElementsByTagName("nom").item(i).getTextContent(),
                    doc.getElementsByTagName("cognom").item(i).getTextContent(),
                    doc.getElementsByTagName("edat").item(i).getTextContent(),
                    doc.getElementsByTagName("ciutat").item(i).getTextContent()
                );
            }

        } catch(Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }  
        
    }
}
