package org.cool.zoo.entities.core;

import javax.persistence.*;

/**
 * Created by Dang Dim
 * Date     : 24-Jan-18, 10:11 AM
 * Email    : d.dim@gl-f.com
 */

@Entity
@Table(name = "tbl_category")
public class Category extends AbstractEntity{

    private String categoryName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    @Column(name = "cat_code")
    public String getCode() {
        return this.getCode();
    }

    @Override
    @Column(name = "cat_des")
    public String getDesc() {
        return this.getDesc();
    }

    @Column(name = "cat_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
