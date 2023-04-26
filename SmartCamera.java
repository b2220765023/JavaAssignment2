package Assignment2;

/**

 The SmartCamera class represents a smart camera device.
 */
public class SmartCamera {
    private float storage;
    private String status;
    private String name;

    /**

     Gets the name of the smart camera.
     @return the name of the smart camera
     */
    public String getName() {
        return name;
    }

    /**

     Sets the name of the smart camera.
     @param name the name of the smart camera
     */
    public void setName(String name) {
        this.name = name;
    }

    /**

     Gets the storage capacity of the smart camera.
     @return the storage capacity of the smart camera in GB
     */
    public float getStorage() {
        return storage;
    }

    /**

     Sets the storage capacity of the smart camera.
     @param storage the storage capacity of the smart camera in GB
     */
    public void setStorage(float storage) {
        this.storage = storage;
    }

    /**

     Gets the status of the smart camera.
     @return the status of the smart camera (On/Off)
     */
    public String getStatus() {
        return status;
    }

    /**

     Sets the status of the smart camera.
     @param status the status of the smart camera (On/Off)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**

     Constructs a SmartCamera object with the specified name and storage.
     @param name the name of the smart camera
     @param storage the storage capacity of the smart camera in GB
     */
    public SmartCamera(String name, float storage){
        setName(name);
        setStorage(storage);
        setStatus("Off");
    }

    /**

     Constructs a SmartCamera object with the specified name, storage, and status.
     @param name the name of the smart camera
     @param storage the storage capacity of the smart camera in GB
     @param status the status of the smart camera (On/Off)
     */
    public SmartCamera(String name, float storage, String status){
        setName(name);
        setStorage(storage);
        setStatus(status);
    }

}
