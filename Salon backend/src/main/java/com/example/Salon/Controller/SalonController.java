package com.example.Salon.Controller;
import com.example.Salon.Model.Images;
import com.example.Salon.Model.Products;
import com.example.Salon.Model.Services;
import com.example.Salon.Model.Users;
import com.example.Salon.Repository.*;
import com.example.Salon.Service.SalonServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

@Controller
public class SalonController {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SalonServices salonServices;

    @Value("images/")
    private String path;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/uploadImage")
    public ResponseEntity<Object> uploadPicture(@RequestParam("file") MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = "./images";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //save the file to specified directory
        Path filepath = Path.of(uploadDir, fileName);
        Files.copy(image.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
        Images savedFile = new Images();
        savedFile.setImage_name(fileName);
        Images data = imageRepository.save(savedFile);
        return ResponseEntity.ok(data);

    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(value = "images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.salonServices.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        if (imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")) {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        } else if (imageName.endsWith(".png")) {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        } else if (imageName.endsWith(".gif")) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);

        }
        StreamUtils.copy(resource, response.getOutputStream());

    }

    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/add_product")
    public ResponseEntity<Object> addPdroduct(@RequestParam("productName") String productName, @RequestParam("productDescription") String productDescription, @RequestParam("productImage") String productImage) {
        Products productsData = new Products();
        productsData.setProduct_name(productName);
        productsData.setProduct_description(productDescription);
        productsData.setProduct_image(productImage);
        Products savedData = productRepository.save(productsData);
        return ResponseEntity.ok(savedData);

    }
    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/add_services")
    public ResponseEntity<Object> addservice(@RequestParam("serviceName")String serviceName, @RequestParam("serviceImage") String serviceImage,@RequestParam("serviceDescription") String serviceDescription){
        Services ServicesData= new Services();
        ServicesData.setService_name(serviceName);
        ServicesData.setService_image(serviceImage);
        ServicesData.setService_Description(serviceDescription);

        Services savedData=serviceRepository.save(ServicesData);
        return ResponseEntity.ok(savedData);
    }
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/getmenu")
    public ResponseEntity<Object> getMenu(){
        Iterable<Products> data= productRepository.findAll();
        return  ResponseEntity.ok(data);
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestParam("user_name") String userName,@RequestParam("user_email")String userEmail,@RequestParam("user_contact") String userContact,@RequestParam("user_age") String  userAge,@RequestParam("user_gender") String userGender,@RequestParam("user_password")String userPassword){
        Users userData=new Users();
        userData.setUser_name(userName);
        userData.setUser_email(userEmail);
        userData.setUser_contact(userContact);
        userData.setUser_gender(userGender);
        userData.setUser_age(userAge);
        userData.setUser_password(userPassword);
        Calendar calandar=Calendar.getInstance();
        Users data=usersRepository.save(userData);
        return ResponseEntity.ok(data);
    }
}