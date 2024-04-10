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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


    @GetMapping("/deleteDog/{id}")
    public String deleteDog(@PathVariable("id") long id, Model model) {
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
    public String postCreateDog(@ModelAttribute("formdata") CreateDogFormData dog,
                                BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/createDog";
        }
        Dog savedDog = dogRepository.save(dog.toEntity());
        Long dogId = savedDog.getId(); // Assuming getId() returns the ID of the saved dog

        if (dog.getImageData() != null) {
            redirectAttributes.addAttribute("image", dog.getImageData());
            redirectAttributes.addAttribute("dogId", dogId); // Assuming dog has getId() method
            return "redirect:/uploadWithDogId";
        }
        else {
            return "redirect:/";
        }

    }
// om man postar en bild så sparas det en när man laddar upp om det inte finns ett id annars så är det samma
    @PostMapping("/createDogImage")
    public String createDogImage(@ModelAttribute("formdata") CreateDogFormData dog,
                                 @RequestParam("image") MultipartFile file, Model model) throws IOException {

        return "redirect:/createDog"; // Return the view
    }



    @GetMapping("/editDog/{id}")
    public String editMessage(@PathVariable Long id, Model model){
        Dog dog = dogRepository.findById(id).orElse(null);
        model.addAttribute("dog", dog.getId());
        if(dog.getImageData() != null) {
            model.addAttribute("imageData", Base64.getEncoder().encodeToString(dog.getImageData()));
        }
        model.addAttribute("formData", new editDogFormData(dog.getId(), dog.getName(), dog.getAge() ));
        return "editDog";
    }

    @PostMapping("/editDog/{id}")
    public String submitEditMessage(@PathVariable Long id, Model model, @ModelAttribute("formData") editDogFormData messageForm
    ) {

        Optional<Dog> optionalMessage = dogRepository.findById(id);

        if (optionalMessage.isPresent()) {
            Dog message = optionalMessage.get();


            message.setName(messageForm.getName());
            message.setAge(messageForm.getAge());

            dogRepository.save(message);
            return "redirect:/";
        } else {
            return "redirect:/error";
        }

    }

    @PostMapping("/uploadWithDogId")
    public String uploadImageWithDogId(Model model, @RequestParam("image") MultipartFile file, @RequestParam("dogId") Long dogId) throws IOException {
        if (dogId == null) {
            return "error";
        }

        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new IllegalArgumentException("Invalid Dog Id: " + dogId));
        dog.setImageData(file.getBytes());
        dogRepository.save(dog);

        model.addAttribute("msg", "Uploaded image for " + dog.getName());
        return "redirect:/editDog/" + dogId;
    }
/*    @GetMapping("/uploadimage/{id}")
    public String displayUploadForm(@PathVariable Long id, Model model) {
        Dog dog = dogRepository.findById(id).orElse(null);
        model.addAttribute("dog", dog.getId());
        if(dog.getImageData() != null) {
            model.addAttribute("imageData", Base64.getEncoder().encodeToString(dog.getImageData()));
        }
        return "test";
    }*/

    //  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
/*    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        File directory = new File(UPLOAD_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        model.addAttribute("msg", "Uploaded image: " + file.getOriginalFilename());
        return "test";
    }*/


}


