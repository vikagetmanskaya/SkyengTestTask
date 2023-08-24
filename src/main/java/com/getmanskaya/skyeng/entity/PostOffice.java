package com.getmanskaya.skyeng.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "post_offices")
@ApiModel
public class PostOffice {
    @ApiModelProperty(name = "id", required = true, notes = "post office id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ApiModelProperty(name = "postcode", required = true, notes = "post office postcode")
    @Column(name = "postcode", nullable = false)
    private String postcode;
    @ApiModelProperty(name = "name", required = true, notes = "post office name")
    @Column(name = "name", nullable = false)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PostOffice{" + '\n' +
                "id=" + id + '\n' +
                ", postcode=" + postcode + '\n' +
                ", name=" + name + '\n' +
                '}';
    }
}
