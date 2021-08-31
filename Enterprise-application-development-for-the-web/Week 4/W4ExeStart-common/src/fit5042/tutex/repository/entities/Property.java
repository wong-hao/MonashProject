package fit5042.tutex.repository.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Eddie
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Property.GET_ALL_QUERY_NAME, query = "SELECT p FROM Property p order by p.propertyId desc")})


public class Property implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "Property.getAll";

    private int propertyId;
    private Double size;
    private Integer numberOfBedrooms;
    private Double price;

    private Address address;
    private ContactPerson contactPerson;

    private Set<String> tags;

    public Property() {
        this.tags = new HashSet<>();
    }

    public Property(int propertyId, Address address, Integer numberOfBedrooms, Double size, Double price, ContactPerson contactPerson, Set<String> tags) {
        this.propertyId = propertyId;
        this.address = address;
        this.size = size;
        this.numberOfBedrooms = numberOfBedrooms;
        this.price = price;
        this.contactPerson = contactPerson;
        this.tags = tags;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "property_id")
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    //insert annotation here to make addess as embeded to Property entity and stored as part of Property

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Column(name = "number_of_bedrooms")
    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    //enforce the relationship between a property and its contact person using annotation(s). Each property has one and only one contact person. Each contact person might be responsible for zero to many properties

    @ManyToOne
    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    //annotate the attribute tags in Property class so that the tags of a property will be stored in a table called TAG. The tags of a property should be eagerly fetched and the value of each tag must be stored in a column VALUE in the TAG table

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tag")
    @Column(name = "value")
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Property{" + "propertyId=" + propertyId + ", size=" + size + ", numberOfBedrooms=" + numberOfBedrooms + ", price=" + price + ", address=" + address + ", contactPerson=" + contactPerson + ", tags=" + tags + '}';
    }
}
