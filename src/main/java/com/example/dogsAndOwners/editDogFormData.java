package com.example.dogsAndOwners;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class editDogFormData {

    @NotNull
    private Long id;
    @NotNull
    @Size(min = 1, max = 100 )
    private String name;
    @Min(0)
    @Max(50)
    private Integer age;
    @Lob
    private byte[] imageData;

    public editDogFormData() {
    }

    public editDogFormData(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public editDogFormData(byte[] imageData) {
        this.imageData = imageData;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
