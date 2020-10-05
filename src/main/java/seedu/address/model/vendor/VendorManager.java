package seedu.address.model.vendor;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameVendor comparison)
 */
public class VendorManager implements ReadOnlyVendorManager {

    private final UniqueVendorList vendorList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        vendorList = new UniqueVendorList();
    }

    public VendorManager() {}

    /**
     * Creates a VendorManager using the Vendors in the {@code toBeCopied}
     */
    public VendorManager(ReadOnlyVendorManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the vendor list with {@code vendors}.
     * {@code vendors} must not contain duplicate vendors.
     */
    public void setVendorList(List<Vendor> vendors) {
        this.vendorList.setVendors(vendors);
    }

    /**
     * Resets the existing data of this {@code VendorManager} with {@code newData}.
     */
    public void resetData(ReadOnlyVendorManager newData) {
        requireNonNull(newData);

        setVendorList(newData.getVendorList());
    }

    //// vendor-level operations

    /**
     * Returns true if a vendor with the same identity as {@code vendor} exists in the vendor manager.
     */
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return vendorList.contains(vendor);
    }

    /**
     * Adds a vendor to the address book.
     * The vendor must not already exist in the address book.
     */
    public void addVendor(Vendor v) {
        vendorList.add(v);
    }

    /**
     * Replaces the given vendor {@code target} in the list with {@code editedVendor}.
     * {@code target} must exist in the address book.
     * The vendor identity of {@code editedVendor} must not be the same as another existing vendor in the address book.
     */
    public void setVendor(Vendor target, Vendor editedVendor) {
        requireNonNull(editedVendor);

        vendorList.setVendor(target, editedVendor);
    }

    /**
     * Removes {@code key} from this {@code VendorManager}.
     * {@code key} must exist in the address book.
     */
    public void removeVendor(Vendor key) {
        vendorList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return vendorList.asUnmodifiableObservableList().size() + " vendors";
        // TODO: refine later
    }

    @Override
    public ObservableList<Vendor> getVendorList() {
        return vendorList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VendorManager // instanceof handles nulls
                && vendorList.equals(((VendorManager) other).vendorList));
    }

    @Override
    public int hashCode() {
        return vendorList.hashCode();
    }
}
