package com.manager.account.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class Base {
    @Column(name = "CREATED_DATE")
    Date createdDate;
    @Column(name = "CREATED_BY")
    String createdBy;
    @Column(name = "UPDATED_DATE")
    Date updatedDate;
    @Column(name = "UPDATED_BY")
    String updatedBy;
}
