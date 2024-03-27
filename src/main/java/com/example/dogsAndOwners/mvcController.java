package com.example.dogsAndOwners;
import com.example.dogsAndOwners.entity.Dog;
import com.example.dogsAndOwners.entity.Owner;
import com.example.dogsAndOwners.repository.DogRepository;
import com.example.dogsAndOwners.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class mvcController {
    @Autowired
    DogRepository dogRepository;
    @Autowired
    OwnerRepository ownerRepository;

    @GetMapping("")
    String firstPage(Model model) {
        List<Dog> dogList = (List)dogRepository.dogsWithoutOwner();
        model.addAttribute("dogs", dogList);
        return "firstPageView";
    }
    @GetMapping("/adminPageView")
    public String showAdminPage(@RequestParam("selectedButton") String selectedButton, Model model) {
        List<?> data = null;
        switch (selectedButton){
            case "1":
                data = dogRepository.dogsWithoutOwner();
                break;
            case "2":
                data = (List)ownerRepository.findAll();
                break;
        }

        model.addAttribute("tableName", selectedButton);
        model.addAttribute("data", data);
        return "adminPageView";
    }


    @GetMapping("/aboutUsView")
    String aboutUs(Model model) {
        List<Dog> dogList = (List)dogRepository.dogsWithoutOwner();
        model.addAttribute("dogs", dogList);
        return "aboutUsView";
    }
    @GetMapping("/allDogs")
    String renderAllDogs(Model model) {
        List<Dog> dogList = (List)dogRepository.findAll();
        model.addAttribute("dogs", dogList);
        return "allDogsView";
    }
    @GetMapping("/allOwners")
    String renderAllOwners(Model model) {
        List<Owner> ownerList = (List)ownerRepository.findAll();
        model.addAttribute( "owners", ownerList);
        return "allOwnersView";

    }
    @GetMapping("/allOwnersWithItsDog")
    String renderAllOwnersWithItsDogs(Model model) {
        List<Owner> ownerList = (List)ownerRepository.findOwnersWithDogs();
        model.addAttribute( "owners", ownerList);
        return "allOwnersWithItsDog";
    }
    @GetMapping("/dog/{id}")
    String renderOneDog(Model model, @PathVariable Long id) {
       Dog dog = dogRepository.findById(id).get();
        model.addAttribute( "dog", dog);
        return "oneDog";
    }
    @GetMapping("/editDog/{id}")
    String editDog(Model model, @PathVariable Long id) {
        Dog dog = dogRepository.findById(id).get();
        model.addAttribute( "dog", dog);
        return "editDog";
    }
/*    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }*/

    @GetMapping("/deleteDog/{id}")
    public String deleteDog(@PathVariable("id") long id, Model model) {
        Dog dog = dogRepository.findById(id).get();
        dogRepository.delete(dog);
        return "redirect:/";
    }
    @GetMapping("/updateDog/{id}")
    public String updateDog(@PathVariable("id") long id, Model model) {
        Dog dog = dogRepository.findById(id).get();
        dogRepository.delete(dog);
        return "redirect:/";
    }
    @GetMapping("/createDog")
    public String createDog(Model model) {
        model.addAttribute("formdata", new CreateDogFormData());

        return "createDog";
    }

    @PostMapping("/createDog")
    public String postCreateDog( @ModelAttribute("formdata") CreateDogFormData dog,
                                BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/createDog";
        }
        dogRepository.save(dog.toEntity());
        return "redirect:/";

    }
/*    @GetMapping("/availableDogs")
    String renderAvailableDogs(Model model) {
        List<Dog> dogList = (List)dogRepository.dogsWithoutOwner();
        model.addAttribute("dogs", dogList);
        return "availableDogs";
    }
    @GetMapping("/availableOwners")
    String renderAvailableOwners(Model model) {
        List<Owner> ownerList = (List)ownerRepository.ownerWithoutDogs();
        model.addAttribute("owners", ownerList);
        return "availableOwners";
    }*/




}


