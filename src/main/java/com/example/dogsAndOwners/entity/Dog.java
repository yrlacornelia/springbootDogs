package com.example.dogsAndOwners.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Breed")
    private String breed; // Added breed field to match your schema

    @Column(name = "Age")
    private int age;

    @Column(name = "Image_data")
    @Lob
    private byte[] imageData;
    @ManyToOne
    @JoinColumn(name = "OwnerID") // This ensures the foreign key relationship is correctly mapped.
    private Owner owner;

    public Dog() {
    }
    public Dog(String name, String breed, int age, byte[] imageData) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.imageData = imageData;

    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
