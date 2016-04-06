package weatherBusiness;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XmlTesterHallvard {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://www.xyz.com/book.xml");
            InputStream stream = url.openStream();
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File inputFile = new File("D:\\Documents\\TDT4140 - PU\\IJ\\PU-11-12\\src\\weatherBusiness\\varsel.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("time");
            System.out.println(nList.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
