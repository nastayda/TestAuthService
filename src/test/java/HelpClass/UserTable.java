package HelpClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserTable {
    @Id
    @Column(name = "ID")
    int id;

    public int getId( ) {
        return id;
    }

    @Column(name = "EMAIL")
    String email;

    public String getEmail( ) {
        return email;
    }

    @Column(name = "LOGIN")
    String login;

    public String getLogin( ) {
        return login;
    }

    @Column(name = "NAME_FIRST")
    String nameFirst;

    public String getNameFirst( ) {
        return nameFirst;
    }

    @Column(name = "NAME_LAST")
    String nameLast;

    public String getNameLast( ) {
        return nameLast;
    }

    @Column(name = "NAME_MIDDLE")
    String nameMiddle;

    public String getNameMiddle( ) {
        return nameMiddle;
    }

    @Column(name = "PHONE")
    String phone;

    public String getPhone( ) {
        return phone;
    }

    @Column(name = "ROLE")
    String role;

    public String getRole( ) {
        return role;
    }

    @Column(name = "CUSTOM_PARAM_1")
    String customParam1;

    public String getParam1( ) {
        return customParam1;
    }

    @Column(name = "CUSTOM_PARAM_2")
    String customParam2;

    public String getParam2( ) {
        return customParam2;
    }

    @Column(name = "CUSTOM_PARAM_3")
    String customParam3;

    public String getParam3( ) {
        return customParam3;
    }

    @Column(name = "CUSTOM_PARAM_4")
    String customParam4;

    public String getParam4( ) {
        return customParam4;
    }

    @Override
    public String toString( ) {
        return id + " " +
                email + " " +
                login + " " +
                nameFirst + " " +
                nameLast + " " +
                nameMiddle + " " +
                phone + " " +
                role + " " +
                customParam1 + " " +
                customParam2 + " " +
                customParam3 + " " +
                customParam4;
    }
}
