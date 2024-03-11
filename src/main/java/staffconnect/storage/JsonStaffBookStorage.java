package staffconnect.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import staffconnect.commons.core.LogsCenter;
import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.commons.util.FileUtil;
import staffconnect.commons.util.JsonUtil;
import staffconnect.model.ReadOnlyStaffBook;

/**
 * A class to access StaffBook data stored as a json file on the hard disk.
 */
public class JsonStaffBookStorage implements StaffBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStaffBookStorage.class);

    private Path filePath;

    public JsonStaffBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStaffBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyStaffBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableStaffBook> jsonStaffBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableStaffBook.class);
        if (!jsonStaffBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStaffBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyStaffBook staffBook) throws IOException {
        saveAddressBook(staffBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyStaffBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyStaffBook staffBook, Path filePath) throws IOException {
        requireNonNull(staffBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStaffBook(staffBook), filePath);
    }

}