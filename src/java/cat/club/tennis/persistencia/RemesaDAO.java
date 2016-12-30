package cat.club.tennis.persistencia;

import cat.club.tennis.domini.Remesa;
import java.util.ArrayList;
import java.util.List;

public interface RemesaDAO {

    void insert(Remesa remesa) throws BussinessException;
            
    void update(int idRemesa,Remesa remesa) throws BussinessException;

    Remesa get(int idRemesa) throws BussinessException;

    void delete(int idRemesa) throws BussinessException;
    
    void addLiniesRemesa(int idRemesa,ArrayList<String> idsPersona,String concepte,Float importe) throws BussinessException;

    List<Remesa> findAll() throws BussinessException;
}
