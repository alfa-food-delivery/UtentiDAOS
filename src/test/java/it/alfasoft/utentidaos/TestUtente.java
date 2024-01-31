package it.alfasoft.utentidaos;

import it.alfasoft.daosimple.DaoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
public class TestUtente {

    private static UtentiDAOS utentiDAO = new UtentiDAOS();

    private static Utente userDeleted;

    @BeforeAll
    public static void beforeAll() throws DaoException {

        utentiDAO.setTableName("food_delivery.utenti");
        userDeleted = utentiDAO.getById(1);
    }
    /**
     * Scenario Base
     * TEST CREATE UTENTE ( INSERT )
     * @throws DaoException
     */
/*
    @Test
    public void testCreaUtente() throws DaoException {
        StringBuilder emailString = new StringBuilder("esempio");
        List<Utente> utenti = utentiDAO.read();

        int lastIndex = 0;
        int dimensioneDb = utenti.size();
        if(dimensioneDb!=0){
            lastIndex = ( utenti.get((dimensioneDb-1 )).getId() ) + 1;
        }
        emailString.append(lastIndex + "@gmail.com");
        Utente utente1 = new Utente(emailString.toString(),"passwordhash1");
        Assertions.assertEquals((int) utentiDAO.create(utente1), lastIndex);

        StringBuilder emailString2 = new StringBuilder("esempio");
        int lastIndex2 = lastIndex+1;
        emailString2.append(lastIndex2 + "@gmail.com");
        Utente utente2 = new Utente(emailString2.toString(),"passwordhash2");
        Assertions.assertTrue(utentiDAO.create(utente2) > 0);
    }
/*
    /**
     * Variante Scenario
     * TEST CREATE ENTITA' VUOTA
     */
/*
    @Test
    public void testCreaUtenteNull() {
        Assertions.assertThrows(
                DaoException.class,
                () -> utentiDAO.create(null),
                "Il null non restituisce un errore corretto!!"
        );
    }


    /**
     * Scenario Base
     * TEST ASSEGNA RUOLO ESISTENTE AD UN UTENTE ( )
     * @throws DaoException
     */
/*
    @Test
    public void testAssegnaRuoloUtente() throws DaoException {
        Utente fattorino = utentiDAO.getById(3);
        //Ruolo fattorino_role = new Ruolo("FATTORINO");
        Ruolo ristoratore_role = new Ruolo("RISTORATORE");
        fattorino.addRuolo(ristoratore_role);

        //Assertions.assertTrue(utentiDAO.assegnaCategoria("FATTORINO",3)==0 );
        Assertions.assertTrue(utentiDAO.assegnaCategoria("RISTORATORE",2)==0 );
    }

    /**
     * Scenario Base
     * TEST READ UTENTE ( READ ALL )
     * @throws DaoException
     */
/*

    @Test
    public void testReadUtente() throws DaoException {
        String sql = "SELECT COUNT(*) FROM food_delivery.utenti;";
        int qty = 0;
        try( Statement stmt = utentiDAO.getConnection().createStatement();){
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            qty = rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace();throw new DaoException();}

        List<Utente> utenti = utentiDAO.read();
        Assertions.assertTrue(qty == utenti.size());
    }

    /**
     * Scenario Base
     * TEST UPDATE UTENTE (mail e password ; idUtente=10)
     */
/*
    @Test
    public void testUpdateUtente() throws DaoException {
        String newEmail = "alessandrobrillante68@gmail.com";
        String newPasswordHash = "passwordHash123456";
        Utente nuoveCredenzialiUtente = new Utente(newEmail,newPasswordHash);
        Assertions.assertTrue(utentiDAO.update(6,nuoveCredenzialiUtente) == 1);
    }

    /**
     * Variante Scenario
     * TEST UPDATE UTENTE NULL(mail o password ; idUtente=5)
     */
/*
    @Test
    public void testUpdateUtenteNull() throws DaoException {

        String newEmail = "alessandrobrillante88@gmail.com";
        Utente nuoveCredenzialiUtente = new Utente(newEmail,null);
        Assertions.assertThrows(
                DaoException.class,
                () -> utentiDAO.update(5,nuoveCredenzialiUtente),
                "Il null non restituisce un errore corretto!!"
        );
    }

    /**
     * Scenario Base
     * TEST DELETE UTENTE (mail e password ; idUtente=5)
     */
/*
    @Test
    public void testDeleteUtente() throws DaoException {
        Assertions.assertTrue(utentiDAO.delete(1)>0);
    }


    @AfterAll
    public static void reinserisciUtenteRimosso() throws SQLException {
        try ( Statement stmt = utentiDAO.getConnection().createStatement() )
        {
            stmt.executeUpdate("insert into food_delivery.utenti (id_utente,email, password_hash) VALUES (1,'alessandrobrillante@gmail.com', SHA2('password1', 256));");
        }

        try ( Statement stmt = utentiDAO.getConnection().createStatement() )
        {
            stmt.executeUpdate("DELETE FROM food_delivery.utenti_ruoli WHERE id_ruolo = 2;");
        }
    }
}
*/