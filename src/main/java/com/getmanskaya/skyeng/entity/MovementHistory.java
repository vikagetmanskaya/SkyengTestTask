package com.getmanskaya.skyeng.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movement_histories")
@ApiModel
public class MovementHistory {
    @ApiModelProperty(name = "id", required = true, notes = "history entry id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ApiModelProperty(name = "dateTime", required = true, notes = "date and time of change in" +
            " the position of the postal item")
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @ApiModelProperty(name = "postOffice", required = true, notes = "location of the postal item")
    @ManyToOne
    @JoinColumn(name = "post_office_id", nullable = false)
    private PostOffice postOffice;
    @ApiModelProperty(name = "status", required = true, notes = "status of the postal item at the" +
            " dateTime", allowableValues = "REGISTERED, SENDING, INTERMEDIATE_POINT, RECEIVING")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MovementStatus status;
    @ApiModelProperty(name = "postal item", required = true, notes = "postal item that owns the history")
    @ManyToOne
    @JoinColumn(name = "postal_item_id", nullable = false)
    private PostalItem postalItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    public MovementStatus getStatus() {
        return status;
    }

    public void setStatus(MovementStatus status) {
        this.status = status;
    }

    public PostalItem getPostalItem() {
        return postalItem;
    }

    public void setPostalItem(PostalItem postalItem) {
        this.postalItem = postalItem;
    }

    @Override
    public String toString() {
        return "MovementHistory{" + '\n' +
                "id=" + id + '\n' +
                ", dateTime=" + dateTime + '\n' +
                ", postOffice=" + postOffice + '\n' +
                ", status=" + status + '\n' +
                ", postalItem=" + postalItem + '\n' +
                '}';
    }
}
