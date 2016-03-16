package Sensors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class XmlDownload {
    String url;
    /*public XmlDownload(String url){
        this.url = url;
    }*/
    /*public void download(){
        InputStream stream = url.openStream();
        Document doc = docBuilder.parse(stream);
    }*/
    //henter xml og returnerer ufromatert streng
    public String getURLContent(String p_sURL)
    {
        URL oURL;
        URLConnection oConnection;
        BufferedReader oReader;
        String sLine;
        StringBuilder sbResponse;
        String sResponse = null;

        try
        {
            oURL = new URL(p_sURL);
            oConnection = oURL.openConnection();
            oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream()));
            sbResponse = new StringBuilder();

            while((sLine = oReader.readLine()) != null)
            {
                sbResponse.append(sLine);
            }

            sResponse = sbResponse.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sResponse;
    }

    /*public static void main(String[] args) {
        String str = "http://www.yr.no/sted/Norge/postnummer/7037/varsel.xml";
        XmlDownload xml = new XmlDownload(str);
        String s = xml.getURLContent(xml.url);
        System.out.println(s);
    }
    */
}

