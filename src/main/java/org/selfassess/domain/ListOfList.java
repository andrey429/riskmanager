package org.selfassess.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 28.12.12
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ListOfList {

    @Id
    @GeneratedValue
    @Column
    private Integer id;



    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToMany()
    private List<ListOfListEntry> listOfListEntries;

    public ListOfList() {
    }


    public List<ListOfListEntry> getListOfListEntries() {
        return listOfListEntries;
    }

    public void setListOfListEntries(List<ListOfListEntry> listOfListEntries) {
        this.listOfListEntries = listOfListEntries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
