package edu.ithaca.barr.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ithaca.barr.bank.BankAccount;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

class JsonUtilTest {

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        BankAccount a1 = new BankAccount("a@b.com", 500);
        String actualJsonString = JsonUtil.toJsonString(a1);

        String expectedJsonString = "{\n" +
                "  \"email\" : \"a@b.com\",\n" +
                "  \"balance\" : 500.0\n" +
                "}";
        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    void toJsonFileAndFromJsonFileTest() throws IOException {
        BankAccount firstObject = new BankAccount("a@b.com", 500);
        JsonUtil.toJsonFile("src/test/resources/toJsonFileAndFromJsonFileTest.json", firstObject);

        BankAccount accountFromFile = JsonUtil.fromJsonFile("src/test/resources/toJsonFileAndFromJsonFileTest.json", BankAccount.class);
        assertEquals(firstObject.getEmail(), accountFromFile.getEmail());
        assertEquals(firstObject.getBalance(), accountFromFile.getBalance(), 0.001);
    }

    @Test
    void listFromJsonFileTest() throws IOException{
        //code that created the file to be read in
//        List<BankAccount> accounts = Arrays.asList(
//                new BankAccount("a@b.com", 500),
//                new BankAccount("b@c.com", 1000),
//                new BankAccount("d@e.com", 5000),
//                new BankAccount("f@g.com", 10000)
//        );
        // You don't need anything special to write a list to json, only to read it in
//        JsonUtil.toJsonFile("src/test/resources/listFromJsonFileTest.json", accounts);

        List<BankAccount> accountsFromFile = JsonUtil.listFromJsonFile("src/test/resources/listFromJsonFileTest.json", BankAccount.class);
        assertEquals(4, accountsFromFile.size());
        assertEquals(1000, accountsFromFile.get(1).getBalance(), 0.001);
        assertEquals("f@g.com", accountsFromFile.get(3).getEmail());
    }
}

