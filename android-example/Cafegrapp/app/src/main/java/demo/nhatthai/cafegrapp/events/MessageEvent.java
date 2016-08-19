package demo.nhatthai.cafegrapp.events;

/**
 * Created by nhatthai on 7/6/16.
 */
public class MessageEvent {
    public final String firstname;
    public final String lastname;
    public final String city;

    public MessageEvent(String firstname, String lastname, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }
}
