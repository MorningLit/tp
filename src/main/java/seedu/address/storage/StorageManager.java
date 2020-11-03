package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.preset.Preset;
import seedu.address.model.profile.Profile;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private PresetManagerStorage presetManagerStorage;
    private ProfileManagerStorage profileManagerStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}
     * and {@Code OrderManagerStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                          PresetManagerStorage presetManagerStorage, ProfileManagerStorage profileManagerStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.presetManagerStorage = presetManagerStorage;
        this.profileManagerStorage = profileManagerStorage;
    }

    public StorageManager() {

    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ PresetManager methods ==============================

    @Override
    public Path getPresetManagerFilePath() {
        return presetManagerStorage.getPresetManagerFilePath();
    }

    @Override
    public Optional<List<List<Preset>>> readPresetManager() throws DataConversionException, IOException {
        return readPresetManager(presetManagerStorage.getPresetManagerFilePath());
    }

    @Override
    public Optional<List<List<Preset>>> readPresetManager(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return presetManagerStorage.readPresetManager(filePath);
    }

    @Override
    public void savePresetManager(List<List<Preset>> allPresets) throws IOException {
        savePresetManager(allPresets, presetManagerStorage.getPresetManagerFilePath());
    }

    @Override
    public void savePresetManager(List<List<Preset>> allPresets, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        presetManagerStorage.savePresetManager(allPresets, filePath);
    }

    // ================ ProfileManager methods ==============================

    @Override
    public Path getProfileManagerFilePath() {
        return presetManagerStorage.getPresetManagerFilePath();
    }

    @Override
    public Optional<Profile> readProfileManager() throws DataConversionException {
        return readProfileManager(profileManagerStorage.getProfileManagerFilePath());
    }

    @Override
    public Optional<Profile> readProfileManager(Path filePath) throws DataConversionException {
        logger.fine("Attempting to read data from file: " + filePath);
        return profileManagerStorage.readProfileManager(filePath);
    }

    @Override
    public void saveProfileManager(Profile profile) throws IOException {
        saveProfileManager(profile, profileManagerStorage.getProfileManagerFilePath());
    }

    @Override
    public void saveProfileManager(Profile profile, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        profileManagerStorage.saveProfileManager(profile, filePath);
    }
}
