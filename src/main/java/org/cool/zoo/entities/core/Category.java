package org.cool.zoo.entities.core;

import javax.persistence.*;

/**
 * Created by Dang Dim
 * Date     : 24-Jan-18, 10:11 AM
 * Email    : d.dim@gl-f.com
 */

@Entity
@Table(name = "tbl_category")
public class Category {

    private Long id;
    private String categoryName;
    private String categoryDesc;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cat_id")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "cat_desc")
    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    @Column(name = "cat_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
