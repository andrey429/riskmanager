package org.riskassess.domain.basic;
import javax.persistence.*;
/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class MediaType {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String mediaTypeName;

    @Column
    private String description;

    public MediaType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaTypeName() {
        return mediaTypeName;
    }

    public void setMediaTypeName(String mediaTypeName) {
        this.mediaTypeName = mediaTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return mediaTypeName;
    }
}
