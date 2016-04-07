package Sensors;

//https://no.wikipedia.org/wiki/Friksjon

import java.io.IOException;

public class WeatherToCoefficient implements SensorInterface{
    private float coefficient;
    private boolean auto; // settes når bruker velger om koeffisienten skal settet automatisk (basert på værmelding) eller manuelt
    private int postalNumber;
    private int ticks;
    private XmlParser parser;

    @SuppressWarnings("OctalInteger")
    public WeatherToCoefficient()  {
        postalNumber = 7034;
        coefficient = 0.8f;
        ticks = 0;
        try {
            XmlParser.download("http://www.yr.no/sted/Norge/postnummer/"+postalNumber+"/varsel.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    private void setCoefficient(float[] weather){
        //Starts with 0.8 as it's the standard coefficient for summer driving
        float temp = weather[0];
        float pre = weather[1];
        float temporaryCo = 0.8f;
        //Uses precipitation to potentially subtract from coefficient
        if(pre == -1f){
            temporaryCo = 0.8f;
        }
        else{
            if (temp >= 2.0f){
                if(pre > 4.2f){
                    temporaryCo = 0.4f;
                }
                else if(pre > 1.6f){
                    temporaryCo = 0.6f;
                }
                else if (pre > 0.0f){
                    temporaryCo = 0.7f;
                }
            }
            else{
                //If temperature is low enough, then rain doesn't matter too much
                temporaryCo = 0.4f;
                if(temp < -10.0f){
                    temporaryCo = 0.2f;
                }
                else if (temp < -5.0f){
                    temporaryCo = 0.3f;
                }
            }
        }

        coefficient = temporaryCo;
    }

    public void update(){
        ticks += 1;
        if (ticks == 864000){
            try {
                XmlParser.download("http://www.yr.no/sted/Norge/postnummer/"+postalNumber+"/varsel.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ticks = 0;
        }
        else if (ticks % 72000 == 0){
            setCoefficient(XmlParser.parse()); //Må fikses når xmlparser er ferdig
        }
    }

    public float getData() {
        return coefficient;
    }
}
