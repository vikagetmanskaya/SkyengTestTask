package com.getmanskaya.skyeng.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "postal_items")
@ApiModel
public class PostalItem {
    @ApiModelProperty(name = "id", required = true, notes = "postal item id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ApiModelProperty(name = "identifier", required = true, notes = "postal item identifier")
    @Column(name = "identifier", nullable = false)
    private String identifier;
    @ApiModelProperty(name = "postalItemType", required = true, notes = "type of postal item",
            allowableValues = "LETTER, PACKAGE, PARCEL, POSTCARD")
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PostalItemType postalItemType;
    @ApiModelProperty(name = "recipientIndex", required = true, notes = "postal item recipient index")

    @Column(name = "recipient_index", nullable = false)
    private String recipientIndex;
    @ApiModelProperty(name = "recipientAddress", required = true, notes = "postal item recipient address")
    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;
    @ApiModelProperty(name = "recipientName", required = true, notes = "postal item recipient name")
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;
    @ApiModelProperty(name = "finalPostOffice", required = true, notes = "final post office")
    @ManyToOne
    @JoinColumn(name = "post_office_id", nullable = false)

    private PostOffice finalPostOffice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public PostalItemType getPostalItemType() {
        return postalItemType;
    }

    public void setPostalItemType(PostalItemType postalItemType) {
        this.postalItemType = postalItemType;
    }

    public String getRecipientIndex() {
        return recipientIndex;
    }

    public void setRecipientIndex(String recipientIndex) {
        this.recipientIndex = recipientIndex;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public PostOffice getFinalPostOffice() {
        return finalPostOffice;
    }

    public void setFinalPostOffice(PostOffice finalPostOffice) {
        this.finalPostOffice = finalPostOffice;
    }

    @Override
    public String toString() {
        return "PostalItem{" + '\n' +
                "id=" + id + '\n' +
                ", identifier=" + identifier + '\n' +
                ", postalItemType=" + postalItemType +
                ", recipientIndex=" + recipientIndex + '\n' +
                ", recipientAddress=" + recipientAddress + '\n' +
                ", recipientName=" + recipientName + '\n' +
                ", finalPostOffice=" + finalPostOffice + '\n' +
                '}';
    }
}
