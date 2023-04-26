package Assignment2;

import java.time.LocalDateTime;

/**
 * SmartPlug class represents a smart plug device that can be turned on and off remotely and can monitor its power usage.
 */
public class SmartPlug {
    private String name;
    private String status;

    public float getAmpere() {
        return Ampere;
    }
    /**

     Gets the ampere of the SmartPlug.
     @return the ampere of the SmartPlug.
     */

    public void setAmpere(float ampere) {
        Ampere = ampere;
    }
    /**

     Sets the ampere of the SmartPlug.
     @param ampere the ampere to be set for the SmartPlug.
     */

    private float Voltage;
    private boolean isPluggedIn;
    private float Ampere;


    public LocalDateTime getOffTime() {
        return OffTime;
    }
    /**
     * Sets the date and time when the smart plug was last turned off.
     *
     * @param offTime the date and time when the smart plug was last turned off
     */




    /**
     *
     * @param offTime Used this when I turned the plug off (to calculate the watt that plug used)
     */
    public void setOffTime(LocalDateTime offTime) {
        OffTime = offTime;
    }

    private LocalDateTime OnTime;
    private LocalDateTime OffTime;

    /**
     * Returns the date and time the SmartPlug was last turned on.
     *
     * @return the date and time the SmartPlug was last turned on
     */
    public LocalDateTime getOnTime() {
        return OnTime;
    }

    /**
     * Sets the date and time the SmartPlug was turned on.
     *
     * @param onTime the date and time the SmartPlug was turned on
     */
    public void setOnTime(LocalDateTime onTime) {
        this.OnTime = onTime;
    }


    public boolean isPluggedIn() {
        return isPluggedIn;
    }
    /**
     * Sets whether the SmartPlug is currently plugged in.
     *
     * @param pluggedIn true if the SmartPlug is plugged in, false otherwise
     */

    public void setPluggedIn(boolean pluggedIn) {
        isPluggedIn = pluggedIn;
    }

    /**
     * Returns the name of the SmartPlug.
     *
     * @return the name of the SmartPlug
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the SmartPlug.
     *
     * @param name the name of the SmartPlug
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the status of the SmartPlug (on or off).
     *
     * @return the status of the SmartPlug
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the SmartPlug (on or off).
     *
     * @param status the status of the SmartPlug
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the voltage of the SmartPlug.
     *
     * @return the voltage of the SmartPlug
     *
     * It is stabilised 220 volt in this assignment
     */
    public float getVoltage() {
        return Voltage;
    }

    /**
     * Sets the voltage of the SmartPlug.
     *
     * @param Voltage the voltage of the SmartPlug
     */
    public void setVoltage(float Voltage) {
        this.Voltage = Voltage;
    }
    /**
     * Creates a new SmartPlug with the given name and default values for status, voltage, and plug-in status.
     *
     * @param name the name of the SmartPlug
     *
     * The other values are defaultly attached
     */
    public SmartPlug(String name){
        setName(name);
        setStatus("Off");
        setVoltage(220);
        setPluggedIn(false);
        /**
         * Creates a new SmartPlug with the given name and status, and default values for voltage and plug-in status.
         *
         * @param name the name of the SmartPlug
         *
         * @param status the initial status of the SmartPlug
         *
         * The other values are defaultly attached
         */
    }public SmartPlug(String name, String status){
        setName(name);
        setStatus(status);
        setVoltage(220);
        setPluggedIn(false);
    }
    /**
     * Creates a new SmartPlug with the given name, status, and amperage, and default value for plug-in status.
     *
     * @param name the name of the SmartPlug
     *
     * @param status the initial status of the SmartPlug
     *
     * @param Ampere the amperage of the SmartPlug
     *
     * The other values are defaultly attached
     */
    public SmartPlug(String name, String status, String Ampere){
        setName(name);
        setStatus(status);
        setAmpere(Float.parseFloat(Ampere));
        setPluggedIn(false);
    }
}
