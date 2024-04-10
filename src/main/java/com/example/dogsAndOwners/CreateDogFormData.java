package com.example.dogsAndOwners;

import com.example.dogsAndOwners.entity.Dog;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateDogFormData {

    @Size(min = 1, max = 100 )
    private String name;
    @Min(0)
    @Max(50)
    private Integer age;
    @Lob
    private byte[] imageData;
    public CreateDogFormData(String name, int age, byte[] imageData) {
        this.imageData = imageData;
        this.name = name;
        this.age = age;
    }

    public CreateDogFormData(){

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

    public Dog toEntity(){
        var dog = new Dog();
        if (age != null) {        dog.setAge(age);}
        if (name != null) {        dog.setName(name);}


        return dog;
    }
}
