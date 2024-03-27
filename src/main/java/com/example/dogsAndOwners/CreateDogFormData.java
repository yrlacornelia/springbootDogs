package com.example.dogsAndOwners;

import com.example.dogsAndOwners.entity.Dog;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateDogFormData {
    @NotNull
    @Size(min = 1, max = 100 )
    private String name;
    @Min(0)
    @Max(50)
    private Integer age;

    public CreateDogFormData(String name, int age) {
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
    public Dog toEntity(){
        var dog = new Dog();
        dog.setName(name);
        dog.setAge(age);
        return dog;
    }
}
