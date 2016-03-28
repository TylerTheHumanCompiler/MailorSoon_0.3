import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Skynet on 28.03.2016.
 */
public class Person {
    private StringProperty firstName;
    public void setFirstName(String value) { firstNameProperty().set(value); }
    public String getFirstName() { return firstNameProperty().get(); }
    public StringProperty firstNameProperty() {
        if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
        return firstName;
    }

    private StringProperty lastName;
    public void setLastName(String value) { lastNameProperty().set(value); }
    public String getLastName() { return lastNameProperty().get(); }
    public StringProperty lastNameProperty() {
        if (lastName == null) lastName = new SimpleStringProperty(this, "lastName");
        return lastName;
    }

    private StringProperty emailaddy;
    public void setEmailaddy(String value) { emailaddyProperty().set(value); }
    public String getEmailaddy() { return emailaddyProperty().get(); }
    public StringProperty emailaddyProperty() {
        if (emailaddy == null) emailaddy = new SimpleStringProperty(this, "emailaddy");
        return emailaddy;
    }
}