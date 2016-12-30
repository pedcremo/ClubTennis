/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.club.tennis.scripts;
import java.math.BigInteger;

/**
 *
 * @author Fernando Rodríguez-Izquierdo Serrano
 */
class CalcularIBAN {
    
    static String getIban(String codigoPais,String entidad,String oficina,String dc,String numeroCuenta){
        String dc_calculated=dc;
        if (dc.isEmpty()){
         dc_calculated=calcularDC(entidad,oficina,numeroCuenta);
        }
        String dcIban = calculaDcIban(codigoPais,entidad,oficina,dc_calculated,numeroCuenta);
        //String iban = codigoPais+dcIban+" "+entidad+" "+oficina+" "+dc_calculated+" "+numeroCuenta;
        return dcIban;
    }
    
    static String calculaDcIban(String codigoPais,String entidad,String oficina,String dc,String numeroCuenta){
        
        String preIban = entidad+
                oficina+
                dc+
                numeroCuenta+
                damePesoIBAN(codigoPais.charAt(0))+
                damePesoIBAN(codigoPais.charAt(1))+
                "00";
        BigInteger ccc = new BigInteger(preIban);
        BigInteger noventaysiete = new BigInteger("97");
        ccc = ccc.mod(noventaysiete);
        int dcIb = ccc.intValue();
        dcIb = 98 - dcIb;
        return ponCerosIzquierda(Integer.toString(dcIb),2);
    }
    
    static String ponCerosIzquierda(String str,int longitud){
        String ceros = "";
        if(str.length()<longitud){
            for(int i=0;i<(longitud-str.length());i++){
                ceros = ceros + '0';
            }
            str = ceros + str;
        }
        
        return str;
    }
    
    static String damePesoIBAN(char letra){
        String peso = "";
        letra = Character.toUpperCase(letra);
        switch (letra){
            case 'A': peso = "10"; 
                break;
            case 'B': peso = "11"; 
                break;
            case 'C': peso = "12"; 
                break;
            case 'D': peso = "13"; 
                break;
            case 'E': peso = "14"; 
                break;
            case 'F': peso = "15"; 
                break;
            case 'G': peso = "16"; 
                break;
            case 'H': peso = "17"; 
                break;
            case 'I': peso = "18"; 
                break;
            case 'J': peso = "19"; 
                break;
            case 'K': peso = "20"; 
                break;
            case 'L': peso = "21"; 
                break;
            case 'M': peso = "22"; 
                break;
            case 'N': peso = "23"; 
                break;
            case 'O': peso = "24"; 
                break;
            case 'P': peso = "25"; 
                break;
            case 'Q': peso = "26"; 
                break;
            case 'R': peso = "27"; 
                break;
            case 'S': peso = "28"; 
                break;
            case 'T': peso = "29"; 
                break;
            case 'U': peso = "30"; 
                break;
            case 'V': peso = "31"; 
                break;
            case 'W': peso = "32"; 
                break;
            case 'X': peso = "33"; 
                break;
            case 'Y': peso = "34"; 
                break;
            case 'Z': peso = "35"; 
                break;
        }
        return peso;
    }
    static public String calcularDC(String banco,String sucursal,String cuenta) {
            int dc1=0;

            dc1=dc1+Integer.parseInt(banco.substring(0,1))*4;
            dc1=dc1+Integer.parseInt(banco.substring(1,2))*8;
            dc1=dc1+Integer.parseInt(banco.substring(2,3))*5;
            dc1=dc1+Integer.parseInt(banco.substring(3,4))*10;
            dc1=dc1+Integer.parseInt(sucursal.substring(0,1))*9;
            dc1=dc1+Integer.parseInt(sucursal.substring(1,2))*7;
            dc1=dc1+Integer.parseInt(sucursal.substring(2,3))*3;
            dc1=dc1+Integer.parseInt(sucursal.substring(3,4))*6;   

            dc1=dc1 %11;
            dc1=11-dc1;
            if (dc1==10) {
                dc1=1;
            }
            if (dc1==11) {
                dc1=0;
            }    

            int dc2=0;

            dc2=dc2+Integer.parseInt(cuenta.substring(0,1))*1;
            dc2=dc2+Integer.parseInt(cuenta.substring(1,2))*2;
            dc2=dc2+Integer.parseInt(cuenta.substring(2,3))*4;
            dc2=dc2+Integer.parseInt(cuenta.substring(3,4))*8;
            dc2=dc2+Integer.parseInt(cuenta.substring(4,5))*5;
            dc2=dc2+Integer.parseInt(cuenta.substring(5,6))*10;
            dc2=dc2+Integer.parseInt(cuenta.substring(6,7))*9;
            dc2=dc2+Integer.parseInt(cuenta.substring(7,8))*7;
            dc2=dc2+Integer.parseInt(cuenta.substring(8,9))*3;
            dc2=dc2+Integer.parseInt(cuenta.substring(9,10))*6;    


            dc2=dc2 %11;
            dc2=11-dc2;
            if (dc2==10) {
                dc2=1;
            }
            if (dc2==11) {
                dc2=0;
            }    

            String dc=String.valueOf(dc1)+String.valueOf(dc2);

            return dc;
    }
    
}