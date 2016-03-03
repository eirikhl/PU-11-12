package weatherBusiness;

import main.SensorInterface;

/**
 * Created by HallvardTiseth on 26.02.2016.
 */
//https://no.wikipedia.org/wiki/Friksjon



public class WeatherToCoefficient implements SensorInterface{

    private float coefficient;

    private boolean auto; //settes når bruker velger om koeffisienten skal settet automatisk (basert på værmelding) eller manuelt

    private int postalNumber = (int)0000;

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public boolean getAuto() {
        return auto;
    }

    public void setPostalNumber(int postalNumber) {
        this.postalNumber = postalNumber;
    }

    public int getPostalNumber() {
        return postalNumber;
    }

    public float getData() {
        //URL url = new URL("http://www.yr.no/sted/Norge/postnummer/"+getPostalNumber()+"/varsel.xml");
        setAuto(true);//for testing-purposes
        float weathercoefficient;
        if (auto) {
            XmlDownload xml = new XmlDownload();
            String weatherXml = xml.getURLContent("http://www.yr.no/sted/Norge/postnummer/" + getPostalNumber() + "/varsel.xml");
            weathercoefficient = new XmlParser().returncoefficient(weatherXml);
        }else{
            weathercoefficient = new UserInput().getInput();
        }
        return weathercoefficient;
    }
    public void update(){

    }
}
