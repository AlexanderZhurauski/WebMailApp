package dao;

import dao.api.IMessageDAO;
import entities.MessageEntity;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageDAOTest {

    private IMessageDAO dao;
    private List<MessageEntity> expected;

    @BeforeAll
    public void setup() {
        dao = new MessageMemoryDAO();
        expected = new ArrayList<>();
    }

    @Test
    @Order(1)
    @DisplayName("Testing getAll method of MessageDAO when DAO is empty: ")
    public void testGetAllEmpty() {
        List<MessageEntity> actual = dao.getAll();
        assertTrue(actual.isEmpty());
    }
    @Test
    @Order(2)
    @DisplayName("Testing add and getAll methods of MessageDAO: ")
    public void testAdd() {
        MessageEntity message1 = new MessageEntity("Sample","random1","random2",
                LocalDateTime.of(2000,10,10,10,10));
        MessageEntity message2 = new MessageEntity("Sample","random3","random4",
                LocalDateTime.of(2001,11,11,11,11));

        expected.add(message1);
        expected.add(message2);
        dao.add(message1);
        dao.add(message2);

        List<MessageEntity> actual = dao.getAll();

        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    @Order(3)
    @DisplayName("Testing get method of MessageDAO: ")
    public void testGet() {
        String recipient = "random2";
        List<MessageEntity> expected = this.expected
                .stream()
                .filter(message -> message.getRecipient().equals(recipient))
                .collect(Collectors.toList());
        List<MessageEntity> actual = dao.get(recipient);

        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
