package org.selfassess.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 28.12.12
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table
public class NumbersListEntry {

    @Id
    @Column
    @GeneratedValue
    private Integer id;



    @Column
    private Integer entry;

    @Column
    private Double value;

    public NumbersListEntry() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
