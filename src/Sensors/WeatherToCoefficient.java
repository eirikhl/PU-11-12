package Sensors;

//https://no.wikipedia.org/wiki/Friksjon

import java.io.IOException;

public class WeatherToCoefficient implements SensorInterface{
    private float coefficient;
    private boolean auto; // settes når bruker velger om koeffisienten skal settet automatisk (basert på værmelding) eller manuelt
    private int postalNumber;

    @SuppressWarnings("OctalInteger")
    public WeatherToCoefficient() throws IOException {
        postalNumber = 7034;
        coefficient = 0.8f;
        XmlParser.download("http://www.yr.no/sted/Norge/postnummer/"+postalNumber+"/varsel.xml");
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    private void setCoefficient(float co){
        coefficient = co;
    }

    public void update(){
        // URL url = new URL("http://www.yr.no/sted/Norge/postnummer/"+getPostalNumber()+"/varsel.xml");
//        setAuto(true);//for testing-purposes
//        float weathercoefficient;
//        if (auto) {
//            XmlDownload xml = new XmlDownload();
//            String weatherXml = xml.getURLContent("http://www.yr.no/sted/Norge/postnummer/" + postalNumber + "/varsel.xml");
//            weathercoefficient = new XmlParser().returncoefficient(weatherXml);
//        }else{
//            weathercoefficient = new UserInput().getInput();
//        }
//        setCoefficient(weathercoefficient);
    }

    public float getData() {
        return coefficient;
    }
}
