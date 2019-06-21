package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetTypeService;
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

    @Autowired//not required
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

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

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kevin");
        vet2.setLastName("Garvey");

        vetService.save(vet2);

        System.out.println("Loaded vets...");

    }
}
