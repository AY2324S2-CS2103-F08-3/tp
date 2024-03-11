package staffconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static staffconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.commons.util.JsonUtil;
import staffconnect.model.StaffBook;
import staffconnect.testutil.TypicalPersons;

public class JsonSerializableStaffConnectTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStaffConnectTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableStaffConnect dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableStaffConnect.class).get();
        StaffBook staffBookFromFile = dataFromFile.toModelType();
        StaffBook typicalPersonsStaffBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(staffBookFromFile, typicalPersonsStaffBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStaffConnect dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableStaffConnect.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableStaffConnect dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableStaffConnect.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStaffConnect.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
