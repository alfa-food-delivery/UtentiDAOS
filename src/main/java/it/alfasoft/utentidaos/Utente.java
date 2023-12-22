package it.alfasoft.utentidaos;

import it.alfasoft.daosimple.IDto;

import java.util.HashSet;
import java.util.Set;

public class Utente implements IDto<Integer> {
    private Integer idUtente;
    private String email;
    private String password;
    private Set<Ruolo> ruoli = new HashSet<>();

    public Utente(){ }

    public Utente(String email, String password){
        this.email = email;
        this.password = password;
    }
    public Utente(int idUtente, String email, String password){
        this.idUtente = idUtente;
        this.email = email;
        this.password = password;
    }
    //GETTERS
    @Override
    public Integer getId(){ return this.idUtente; }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){ return this.password; }
    public Set<Ruolo> getRuoli(){ return this.ruoli; }
    //SETTERS
    public void setIdUtente(Integer idUtente){ this.idUtente = idUtente; }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setRuoli(Set<Ruolo> ruoli){ this.ruoli = ruoli;}
    public void addRuolo(Ruolo role){
        this.getRuoli().add(role);
        role.addUtente(this);
    }
    public void removeRuolo(Ruolo role){
        this.getRuoli().remove(role);
        role.removeUtente(this);
    }

    @Override
    public String toString(){
        return "Utente: " + this.getEmail() + " Password: " + this.getPassword();
    }
}
