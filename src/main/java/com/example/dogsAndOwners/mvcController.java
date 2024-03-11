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
    @GetMapping("/allDogs")
    String renderAllDogs(Model model) {
        logger.info("Rendering all dogs");
        List<Dog> dogList = (List)dogRepository.findAll();
        model.addAttribute("dogs", dogList);
        return "allDogsView";
    }
    @GetMapping("/allOwners")
    String renderAllOwners(Model model) {
       /* List<Owner> ownerList = new ArrayList<>();
        ownerList.add(new Owner("Pluto"));
        ownerList.add(new Owner("Ludde"));
        ownerList.add(new Owner("Chicco"));*/
        List<Owner> ownerList = (List)ownerRepository.findAll();
        model.addAttribute( "owners", ownerList);

        return "allOwnersView";

    }
    @GetMapping("/allOwnersWithItsDog")
    String renderAllOwnersWithItsDogs(Model model) {
        List<Owner> ownerList = (List)ownerRepository.findAll();
        model.addAttribute( "owners", ownerList);
        return "allOwnersWithItsDog";
    }
    @GetMapping("/dog/{id}")
    String renderOneDog(Model model, @PathVariable Long id) {
       Dog dog = dogRepository.findById(id).get();
        model.addAttribute( "dog", dog);
        return "oneDog";
    }

    // all dogs
    // all owners x
    // all owners and its dogs
    // all avaliable dogs
    // owners without dogs

    // man ska kunna ha en ensam person utan hund
    // man ska kunna ha en hund ensam
    // man ska kunna ha en person med hundar

}


