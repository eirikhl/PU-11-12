package weatherBusiness;


public class XmlTester {
    public static void main(String[] args) {
        XmlDownload xml = new XmlDownload();
        String weatherXml = xml.getURLContent("http://www.yr.no/sted/Norge/postnummer/" + 7037 + "/varsel.xml");
        System.out.println(weatherXml);
    }
}
