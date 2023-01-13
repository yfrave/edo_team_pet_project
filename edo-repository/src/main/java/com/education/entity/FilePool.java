package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.engine.profile.Fetch;

import java.sql.Date;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "file_pool")
public class FilePool extends BaseEntity{
    /**
     * ID хранилища файла
     */
    @Id
    @Column(name = "storage_file_id")
    private UUID storageFileId;
    /**
     * Имя хранилища файла
     */
    @Column(name = "file_name")
    private String name;
    /**
     * Расширение файла
     */
    @Column(name = "extension")
    private String extension;
    /**
     * Размер файла
     */
    @Column(name = "file_size")
    private int size;
    /**
     * Количество страниц файла
     */
    @Column(name = "page_count")
    private int pageCount;
    /**
     * Дата загрузки файла
     */
    @Column(name = "upload_date")
    private Date uploadDate;
    /**
     * Дата архивирования файла
     */
    @Column(name = "archived_date")
    private Date archivedDate;
    /**
     * Автор файла
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

}
