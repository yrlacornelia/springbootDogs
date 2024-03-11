package com.example.dogsAndOwners;
import com.example.dogsAndOwners.entity.Dog;
import com.example.dogsAndOwners.entity.Owner;
import com.example.dogsAndOwners.repository.DogRepository;
import com.example.dogsAndOwners.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class mvcController {
    private static final Logger logger = LoggerFactory.getLogger(mvcController.class);

    @Autowired
    DogRepository dogRepository;
    @Autowired
    OwnerRepository ownerRepository;

    @GetMapping("")
    String firstPage(Model model) {
        return "firstPageView";
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
    @GetMapping("/availableDogs")
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
    }

    // all dogs x
    // all owners x
    // all owners and its dogs x (dont show dogs without owners or opposite) x
    // all avaliable dogs  ( no dogs that has an owner) x
    // owners without dogs  ( no owners that has a dog) x

    // snyggare sida
    // filter
    // adopt sheet



}


