package timefeel.com.contactstest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

/**
 * Created by test on 17/02/2017.
 */

public class ContactsResponse {

    @SerializedName("contacts")
    @Expose
    private List<Contact> contacts;
    @SerializedName("me")
    @Expose
    private Me me;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Me getMe() {
        return me;
    }

    public void setMe(Me me) {
        this.me = me;
    }
}
