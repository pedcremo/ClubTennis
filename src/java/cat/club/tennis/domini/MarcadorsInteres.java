
package cat.club.tennis.domini;


public class MarcadorsInteres {

    private boolean deBaixa;
    private boolean ambAlerta;
    private String raoAlerta;

    public MarcadorsInteres() {
    }
    
    public MarcadorsInteres(boolean deBaixa, boolean ambAlerta, String raoAlerta) {
        this.deBaixa = deBaixa;
        this.ambAlerta = ambAlerta;
        this.raoAlerta = raoAlerta;
    }

    
    /**
     * @return the deBaixa
     */
    public boolean isDeBaixa() {
        return deBaixa;
    }

    /**
     * @param deBaixa the deBaixa to set
     */
    public void setDeBaixa(boolean deBaixa) {
        this.deBaixa = deBaixa;
    }

    /**
     * @return the ambAlerta
     */
    public boolean isAmbAlerta() {
        return ambAlerta;
    }

    /**
     * @param ambAlerta the ambAlerta to set
     */
    public void setAmbAlerta(boolean ambAlerta) {
        this.ambAlerta = ambAlerta;
    }

    /**
     * @return the raoAlerta
     */
    public String getRaoAlerta() {
        return raoAlerta;
    }

    /**
     * @param raoAlerta the raoAlerta to set
     */
    public void setRaoAlerta(String raoAlerta) {
        this.raoAlerta = raoAlerta;
    }
}
