package it.alfasoft.utentidaos;

import it.alfasoft.daosimple.IDto;

import java.util.HashSet;
import java.util.Set;

public class Ruolo implements IDto<Integer> {
    private Integer idRuolo;
    private String nomeRuolo;
    private String descrizioneRuolo;
    private Set<Utente> utenti = new HashSet<>();;

    public Ruolo(){
    }

    public Ruolo(String nomeRuolo) {
        this.nomeRuolo = nomeRuolo;
    }
    public Ruolo(int idRuolo, String nomeRuolo){
        this.idRuolo = idRuolo;
        this.nomeRuolo = nomeRuolo;
    }
    public Ruolo(String nomeRuolo, String descrizioneRuolo){
        this.nomeRuolo = nomeRuolo;
        this.descrizioneRuolo = descrizioneRuolo;
    }

    public Ruolo(int idRuolo, String nomeRuolo, String descrizioneRuolo){
        this.idRuolo = idRuolo;
        this.nomeRuolo = nomeRuolo;
        this.descrizioneRuolo = descrizioneRuolo;
    }

    public Integer getId() { return this.idRuolo; }
    public String getNomeRuolo() { return this.nomeRuolo;}
    public String getDescrizioneRuolo(){ return this.descrizioneRuolo;}
    public Set<Utente> getUtenti(){ return this.utenti;}
    public void setIdRuolo(int idRuolo) { this.idRuolo = idRuolo;}
    public void setNomeRuolo(String nomeRuolo) { this.nomeRuolo = nomeRuolo;}
    public void setDescrizioneRuolo(String descrizioneRuolo){ this.descrizioneRuolo = descrizioneRuolo;}
    public void setUtenti(Set<Utente> utenti){ this.utenti = utenti;}

    public void addUtente(Utente user){
        this.getUtenti().add(user);
    }
    public void removeUtente(Utente user){
        this.getUtenti().remove(user);
    }

    @Override
    public String toString(){
        return "Ruolo: " + this.getNomeRuolo() + " Descrizione: " + this.getDescrizioneRuolo();
    }
}
