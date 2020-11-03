package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.profile.Profile;

public class JsonAdaptedProfile {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Profile's %s field is missing!";

    private String address;
    private String phone;

    /**
     * Constructs a {@code JsonAdaptedProfile} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedProfile(@JsonProperty("address") String address,
                             @JsonProperty("phone") String phone) {
        this.address = address;
        this.phone = phone;
    }

    /**
     * Converts a given {@code Profile} into this class for Jackson use.
     */
    public JsonAdaptedProfile(Profile source) {
        this.address = source.getAddress().toString();
        this.phone = source.getPhone().toString();
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    /**
     * Converts this Jackson-friendly adapted profile object into the model's {@code Profile} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Profile.
     */
    public Profile toModelType() throws IllegalValueException {

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Address"));
        }

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Phone"));
        }

        return new Profile(address, phone);
    }
}
