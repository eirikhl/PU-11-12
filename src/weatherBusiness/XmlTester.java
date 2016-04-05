package weatherBusiness;


import Sensors.XmlDownload;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


public class XmlTester {
    public XmlTester() {
    }

    public static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }

    }
    public static String prettyFormat(String input) {
        return prettyFormat(input, 2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        /*XmlDownload xml = new XmlDownload();

         
        //String weatherXml = xml.getURLContent("http://www.yr.no/sted/Norge/postnummer/" + 7037 + "/varsel.xml");
        File f = new File("XmlTest");
        String weatherXml = f;
        System.out.println(weatherXml);
        //System.out.println(XmlTester.prettyFormat());
        */
    }
}
