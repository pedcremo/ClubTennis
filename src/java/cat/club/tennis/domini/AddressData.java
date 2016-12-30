
package cat.club.tennis.domini;


public class AddressData {

    private String carrer;
    private String poblacio;
    private String provincia;
    private String cp;

    public AddressData() {
    }
    
    public AddressData(String carrer, String poblacio, String provincia, String cp) {
        this.carrer = carrer;
        this.poblacio = poblacio;
        this.provincia = provincia;
        this.cp=cp;
    }
    
    /**
     * @return the carrer
     */
    public String getCarrer() {
        return carrer;
    }

    /**
     * @param carrer the carrer to set
     */
    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }
    /**
     * @return the poblacio
     */
    public String getPoblacio() {
        return poblacio;
    }

    /**
     * @param poblacio the poblacio to set
     */
    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }
    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }
}
