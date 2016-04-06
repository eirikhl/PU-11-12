package Sensors;

//https://no.wikipedia.org/wiki/Friksjon

public class WeatherToCoefficient implements SensorInterface{
    private float coefficient;
    private boolean auto; // settes når bruker velger om koeffisienten skal settet automatisk (basert på værmelding) eller manuelt
    private int postalNumber;

    @SuppressWarnings("OctalInteger")
    public WeatherToCoefficient(){
        postalNumber = 0000;
        coefficient = 0.8f;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    private void setCoefficient(float pre, float temp){
        //Starts with 0.8 as it's the standard coefficient for summer driving
        float temporaryCo = 0.8f;
        //Uses precipitation to potentially subtract from coefficient
        if (temp >= 0.0f){
            if(pre > 4.2f){
                temporaryCo -= 0.4f;
            }
            else if(pre >1.6f){
                temporaryCo -= 0.2f;
            }
            else if (pre > 0.0f){
                temporaryCo -= 0.1f;
            }
        }
        else{
            temporaryCo = 0.4f;
            if(temp < -10.0f){
                temporaryCo -= 0.2f;
            }
            else if (temp < -5.0f){
                temporaryCo -= 0.1f;
            }
        }

        coefficient = temporaryCo;
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
