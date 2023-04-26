package Assignment2;

/**

 A class representing a smart lamp that can be controlled remotely.
 */
public class SmartLamp {
    private String name;
    private int kelvin;
    private String status;

    /**

     Returns the name of the smart lamp.
     @return the name of the smart lamp
     */
    public String getName() {
        return name;
    }

    /**

     Sets the name of the smart lamp.
     @param name the new name of the smart lamp
     */
    public void setName(String name) {
        this.name = name;
    }

    /**

     Returns the kelvin temperature of the smart lamp's light.
     @return the kelvin temperature of the smart lamp's light
     */
    public int getKelvin() {
        return kelvin;
    }

    /**

     Sets the kelvin temperature of the smart lamp's light.
     @param kelvin the new kelvin temperature of the smart lamp's light
     */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    /**

     Returns the current status of the smart lamp.
     @return the current status of the smart lamp (On/Off)
     */
    public String getStatus() {
        return status;
    }

    /**

     Constructs a new SmartLamp object with the given name, status, kelvin temperature, and brightness level.
     @param name the name of the smart lamp
     @param status the current status of the smart lamp (On/Off)
     @param kelvin the kelvin temperature of the smart lamp's light
     @param brightness the brightness level of the smart lamp's light
     */
    public SmartLamp(String name,  String status, int kelvin, int brightness) {
        setName(name);
        setKelvin(kelvin);
        setStatus(status);
        setBrightness(brightness);
    }

    /**

     Constructs a new SmartLamp object with the given name, status, and kelvin temperature.
     The brightness level is set to 100% by default.
     @param name the name of the smart lamp
     @param status the current status of the smart lamp (On/Off)
     @param kelvin the kelvin temperature of the smart lamp's light
     */
    public SmartLamp(String name,  String status, int kelvin) {
        setName(name);
        setKelvin(kelvin);
        setStatus(status);
        setBrightness(100);
    }

    /**

     Sets the status of the SmartLamp.
     @param status The status to set the SmartLamp to (e.g. "On", "Off", "Dimmed").
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**

     Constructs a new SmartLamp object with the given name, status, kelvin temperature of 4000, and brightness level of 100%.
     @param name the name of the smart lamp
     @param status the current status of the smart lamp (On/Off)
     */
    public SmartLamp(String name, String status) {
        setName(name);
        setStatus(status);
        setKelvin(4000);
        setBrightness(100);
    }

    public int getBrightness() {
        return brightness;
    }

    /**

     Constructs a new SmartLamp object with the given name.
     The initial status is set to "Off", kelvin temperature is set to 4000, and brightness level is set to 100% by default.
     @param name the name of the smart lamp
     */
    public SmartLamp(String name) {
        setName(name);
        setStatus("Off");
        setKelvin(4000);
        setBrightness(100);
    }

    /**

     Sets the brightness of the SmartLamp.
     @param brightness an integer value representing the brightness of the SmartLamp, ranging from 0 to 100.
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    private int brightness;
}
