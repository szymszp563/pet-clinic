package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetTypeService;
import com.springframework.petclinic.services.SpecialityService;
import com.springframework.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {


    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    @Autowired//not required
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }


    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality saveDentistry = specialityService.save(dentistry);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialityService.save(radiology);

        Owner owner1 = new Owner();
        owner1.setFirstName("Frank");
        owner1.setLastName("Sinatra");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("668543456");
        ownerService.save(owner1);

        Pet franksPet = new Pet();
        franksPet.setPetType(saveDogPetType);
        franksPet.setBirthDate(LocalDate.now());
        franksPet.setOwner(owner1);
        franksPet.setName("Rosco");
        owner1.getPets().add(franksPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("Ian");
        owner2.setLastName("Curtis");
        owner2.setAddress("59 Koszlinska");
        owner2.setCity("Opole");
        owner2.setTelephone("778543456");
        ownerService.save(owner2);

        Pet iansCat = new Pet();
        iansCat.setName("Ciri");
        iansCat.setOwner(owner2);
        iansCat.setBirthDate(LocalDate.now());
        iansCat.setPetType(saveCatPetType);
        owner2.getPets().add(iansCat);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Mike");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(saveRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kevin");
        vet2.setLastName("Garvey");
        vet2.getSpecialities().add(saveSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
