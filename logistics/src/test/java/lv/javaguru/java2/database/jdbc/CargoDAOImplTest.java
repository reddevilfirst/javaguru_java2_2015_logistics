package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Dinjvald on 07/02/15.
 */

@WebAppConfiguration
public class CargoDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreate() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user = new User("Dinjvald", "qwerty", "Deniss", "Beskorovainijs", "qwerty@email.com",
                "+37126957815", company);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Cargo cargo = new Cargo(userFromDB, "tilt", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
        cargoDAO.create(cargo);
        Cargo cargoFromDB = cargoDAO.getById(cargo.getCargoId());
        assertNotNull(userFromDB);
        assertNotNull(cargoFromDB);
        assertEquals(cargo.getCargoId(), cargoFromDB.getCargoId());
        assertEquals(user.getUserId(), cargoFromDB.getUser().getUserId());
        assertEquals(cargo.getVehicleType(), cargoFromDB.getVehicleType());
        assertEquals(cargo.getWeight(), cargoFromDB.getWeight());
        assertEquals(cargo.getLoadAddress(), cargoFromDB.getLoadAddress());
        assertEquals(cargo.getUnloadAddress(), cargoFromDB.getUnloadAddress());
        assertEquals(cargo.getLoadDate(), cargoFromDB.getLoadDate());
        assertEquals(cargo.getUnloadDate(), cargoFromDB.getUnloadDate());
        assertEquals(cargo.getStatus(), cargoFromDB.getStatus());
    }


    @Test
    @Transactional
    public void testDeleteAndGetAll() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user1 = new User("Dinjvald", "qwerty", "Deniss", "Beskorovainijs", "qwerty@email.com",
                "+37126957815", company);
        User user2 = new User("dinjab", "ytrewq", "Sergejs", "Popovs", "ytrewq@email.com",
                "+37128453698", company);
        userDAO.create(user1);
        userDAO.create(user2);
        Cargo cargo1 = new Cargo(user1, "ref", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
        Cargo cargo2 = new Cargo(user2, "tilt", 19.4, "LV Kurzemes", "DE Rein",
                cargoDAO.stringToDate("05/02/2015"), cargoDAO.stringToDate("10/02/2015"), "ready");
        cargoDAO.create(cargo1);
        cargoDAO.create(cargo2);
        cargoDAO.delete(cargo1.getCargoId());
        List<Cargo> cargos = cargoDAO.getAll();
        assertEquals(1, cargos.size());
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user1 = new User("Dinjvald", "qwerty", "Deniss", "Beskorovainijs", "qwerty@email.com",
                "+37126957815", company);
        userDAO.create(user1);
        Cargo cargo1 = new Cargo(user1, "ref", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
        cargoDAO.create(cargo1);
        Cargo cargoFromDB = cargoDAO.getById(cargo1.getCargoId());
        assertEquals(cargo1.getCargoId(), cargoFromDB.getCargoId());

        cargo1.setVehicleType("tilt");
        cargo1.setWeight(19.4);
        cargo1.setLoadAddress("LV Lurzemes");
        cargo1.setUnloadAddress("DE Rein");
        cargo1.setLoadDate(cargoDAO.stringToDate("05/02/2015"));
        cargo1.setUnloadDate(cargoDAO.stringToDate("10/02/2015"));
        cargo1.setStatus("pending");

        cargoDAO.update(cargo1);
        Cargo updatedCargoFromDB = cargoDAO.getById(cargo1.getCargoId());

        assertEquals(cargoFromDB.getCargoId(), updatedCargoFromDB.getCargoId());
        assertEquals(cargo1.getUserId(), updatedCargoFromDB.getUserId());
        assertEquals(cargo1.getVehicleType(), updatedCargoFromDB.getVehicleType());
        assertEquals(cargo1.getWeight(), updatedCargoFromDB.getWeight());
        assertEquals(cargo1.getLoadAddress(), updatedCargoFromDB.getLoadAddress());
        assertEquals(cargo1.getUnloadAddress(), updatedCargoFromDB.getUnloadAddress());
        assertEquals(cargo1.getLoadDate(), updatedCargoFromDB.getLoadDate());
        assertEquals(cargo1.getUnloadDate(), updatedCargoFromDB.getUnloadDate());
        assertEquals(cargo1.getStatus(), updatedCargoFromDB.getStatus());
    }


    @Test
    @Transactional
    public void testGetByParameters() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user1 = new User("Dinjvald", "qwerty", "Deniss", "Beskorovainijs", "qwerty@email.com",
                "+37126957815", company);
        userDAO.create(user1);

        Cargo cargo1 = new Cargo(user1, "platform", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("01/02/2015"), cargoDAO.stringToDate("15/03/2015"), "ready");
        Cargo cargo2 = new Cargo(user1, "platform", 19.4, "LV Kurzemes", "DE Rein",
                cargoDAO.stringToDate("12/02/2015"), cargoDAO.stringToDate("10/03/2015"), "ready");
        Cargo cargo3 = new Cargo(user1, "platform", 31.4, "LV AAA", "RU Moscow",
                cargoDAO.stringToDate("23/02/2015"), cargoDAO.stringToDate("25/03/2015"), "ready");
        Cargo cargo4 = new Cargo(user1, "platform", 9.8, "LV BBB", "DE Rein",
                cargoDAO.stringToDate("05/02/2015"), cargoDAO.stringToDate("13/03/2015"), "ready");
        cargoDAO.create(cargo1);
        cargoDAO.create(cargo2);
        cargoDAO.create(cargo3);
        cargoDAO.create(cargo4);
        List<Cargo> cargos = cargoDAO.getByParameters("platform", 15.0, 32.0, cargoDAO.stringToDate("04/02/2015"),
                cargoDAO.stringToDate("25/02/2015"), cargoDAO.stringToDate("10/03/2015"), cargoDAO.stringToDate("26/03/2015"));
        assertEquals(2, cargos.size());
        cargos = cargoDAO.getByParameters("platform", 15.0, 32.0, cargoDAO.stringToDate("01/02/2015"),
                cargoDAO.stringToDate("25/02/2015"), cargoDAO.stringToDate("10/03/2015"), cargoDAO.stringToDate("26/03/2015"));
        assertEquals(3, cargos.size());
    }

    @Test
    @Transactional
    public void testGetNullByNonExistentId() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);
        Cargo cargo = new Cargo(user, "platform", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("01/02/2015"), cargoDAO.stringToDate("15/03/2015"), "ready");
        cargoDAO.create(cargo);

        Cargo cargoFromDB;
        if(cargo.getCargoId() == 111L)
            cargoFromDB = cargoDAO.getById(222L);
        else
            cargoFromDB = cargoDAO.getById(111L);
        assertNull(cargoFromDB);
    }
}
