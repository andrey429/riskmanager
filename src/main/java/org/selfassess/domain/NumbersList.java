package org.selfassess.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 28.12.12
 * Time: 21:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class NumbersList {


    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToMany
    private List<NumbersListEntry> numbersListEntries;


    public NumbersList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<NumbersListEntry> getNumbersListEntries() {
        return numbersListEntries;
    }

    public void setNumbersListEntries(List<NumbersListEntry> numbersListEntries) {
        this.numbersListEntries = numbersListEntries;
    }
}
