package Assignment2;

/**

 A class representing a smart color lamp that extends the SmartLamp class and has additional color settings.
 */
public class SmartColorLamp {
    private String name;
    private String status;
    private int kelvin;
    private int brightness;
    private String colorMode;

    /**

     Gets the name of the smart color lamp.
     @return the name
     */
    public String getName() {
        return name;
    }

    /**

     Sets the name of the smart color lamp.
     @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**

     Gets the status of the smart color lamp.
     @return the status
     */
    public String getStatus() {
        return status;
    }

    /**

     Creates a SmartColorLamp object with a given name and status, and sets the Kelvin value to 4000 and the brightness to 100.
     @param name the name of the lamp
     @param status the status of the lamp (on/off)
     */
    public SmartColorLamp(String name, String status) {
        setName(name);
        setStatus(status);
        setKelvin(4000);
        setBrightness(100);
    }

    /**

     Creates a SmartColorLamp object with a given name, status, color mode, and brightness.
     @param name the name of the lamp
     @param status the status of the lamp (on/off)
     @param colorMode the color mode of the lamp
     @param brightness the brightness of the lamp
     */
    public SmartColorLamp(String name, String status,String colorMode, int brightness) {
        setName(name);
        setStatus(status);
        setBrightness(brightness);
        setColorMode(colorMode);
    }

    /**

     Sets the status of the smart color lamp.
     @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public SmartColorLamp(String name, String status, int kelvin, int brightness) {
        setName(name);
        setStatus(status);
        setKelvin(kelvin);
        setBrightness(brightness);
    }

    /**

     Gets the kelvin value of the smart color lamp.
     @return the kelvin value
     */
    public int getKelvin() {
        return kelvin;
    }

    /**

     Sets the kelvin value of the smart color lamp.
     @param kelvin the kelvin value to set
     */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    /**

     Gets the brightness value of the smart color lamp.
     @return the brightness value
     */
    public int getBrightness() {
        return brightness;
    }

    /**

     Sets the brightness value of the smart color lamp.
     @param brightness the brightness value to set
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**

     Gets the color mode of the smart color lamp.
     @return the color mode
     */
    public String getColorMode() {
        return colorMode;
    }

    /**

     Sets the color mode of the smart color lamp.
     @param colorMode the color mode to set
     */
    public void setColorMode(String colorMode) {
        this.colorMode = colorMode;
    }

    /**

     Constructs a SmartColorLamp object with the specified name and default settings.
     @param name the name of the smart color lamp
     */
    public SmartColorLamp(String name) {

        setName(name);
        setStatus("Off");
        setBrightness(100);
        setKelvin(4000);
    }
}
