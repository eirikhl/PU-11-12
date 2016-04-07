package Sensors;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class XmlParser {
    public static void download(String url) throws IOException {
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("src/Sensors/verdata.xml");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    public static float[] parse(){
        float temp = 0.0f;
        float prec = 0.0f;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/Sensors/verdata.xml");
//            Document doc = builder.parse(xmlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: "
                    + doc.getDocumentElement().getNodeName());
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = ("/weatherdata/forecast/tabular/time");

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            Node nNode = nodeList.item(0);

            System.out.println("\nCurrent Element: "+nNode.getNodeName());
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                prec = Float.parseFloat(eElement.getElementsByTagName("precipitation").item(0).getAttributes().getNamedItem("value").getTextContent());
                temp = Float.parseFloat(eElement.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("value").getTextContent());
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        float[] list = {temp, prec};
        System.out.println(list[0] + " " + list[1]);
        return list;
    }

}
