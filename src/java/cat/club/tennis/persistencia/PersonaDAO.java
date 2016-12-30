package cat.club.tennis.persistencia;

import cat.club.tennis.domini.Persona;
import java.util.List;

public interface PersonaDAO {

    void insert(Persona persona) throws BussinessException;
            
    void update(int idPersona,Persona persona) throws BussinessException;

    Persona get(int idPersona) throws BussinessException;

    void delete(int idPersona) throws BussinessException;

    List<Persona> findAll(String tipusRelacio) throws BussinessException;
}
