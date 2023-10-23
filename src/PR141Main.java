import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.*;

public class PR141Main {
    public static void main(String[] args) {
        try {
            // Crea una factoria de constructors de documents
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // Crea un constructor de documents
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Crea un nou document XML
            Document doc = db.newDocument();
            // Crea l'element root del document XML
            Element elmRoot = doc.createElement("biblioteca");
            // Afegeix l'element root al document XML
            doc.appendChild(elmRoot);
            Element elmLlibre = doc.createElement("llibre");
            elmRoot.appendChild(elmLlibre);
            Attr attrId = doc.createAttribute("id");
            attrId.setValue("001");
            elmLlibre.setAttributeNode(attrId);

            Element elmTitol = doc.createElement("titol");
            elmTitol.appendChild(doc.createTextNode("El viatge dels venturons"));
            elmLlibre.appendChild(elmTitol);

            Element elmAutor = doc.createElement("autor");
            elmAutor.appendChild(doc.createTextNode("Joan Pla"));
            elmLlibre.appendChild(elmAutor);

            Element elmAny = doc.createElement("anyPublicacio");
            elmAny.appendChild(doc.createTextNode("1998"));
            elmLlibre.appendChild(elmAny);

            Element elmEditorial = doc.createElement("editorial");
            elmEditorial.appendChild(doc.createTextNode("Edicions Mar"));
            elmLlibre.appendChild(elmEditorial);

            Element elmGenere = doc.createElement("genere");
            elmGenere.appendChild(doc.createTextNode("Aventura"));
            elmLlibre.appendChild(elmGenere);

            Element elmPagines = doc.createElement("pagines");
            elmPagines.appendChild(doc.createTextNode("320"));
            elmLlibre.appendChild(elmPagines);

            Element elmDisponible = doc.createElement("disponible");
            elmDisponible.appendChild(doc.createTextNode("true"));
            elmLlibre.appendChild(elmDisponible);

            write("./data/biblioteca.xml", doc);
        } catch (Exception e) {
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
