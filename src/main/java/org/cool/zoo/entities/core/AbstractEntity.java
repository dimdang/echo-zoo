package org.cool.zoo.entities.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dang Dim
 * Date     : 08-Jan-18, 2:54 PM
 * Email    : d.dim@gl-f.com
 */

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, Cloneable {

    protected Long id;
    protected String code;
    protected String desc;
    @JsonIgnore
    protected String userCreate;
    @JsonIgnore
    protected Date createDate;
    @JsonIgnore
    protected String userUpdate;
    @JsonIgnore
    protected Date updateDate;

    @Transient
    public abstract Long getId();

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public abstract String getCode();

    public void setCode(String code) {
        this.code = code;
    }

    @Transient
    public abstract String getDesc();

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "usr_create")
    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cre_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "usr_update")
    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upd_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
