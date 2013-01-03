package org.selfassess.domain;



import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 28.12.12
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class ListOfListEntry {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private Integer entryOrder;

    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToOne()
    private NumbersList numbersList;



    public ListOfListEntry() {
    }

    public Integer getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NumbersList getNumbersList() {
        return numbersList;
    }

    public void setNumbersList(NumbersList numbersList) {
        this.numbersList = numbersList;
    }
}
