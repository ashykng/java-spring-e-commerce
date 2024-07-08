package com.example.online_shop.controllers;

import com.example.online_shop.dto.MessageDto;
import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.Wishlist;
import com.example.online_shop.services.MessageService;
import com.example.online_shop.services.ProductService;
import com.example.online_shop.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/")
    public String index(Model model){
        List<Product> latestProducts = productService.findLatestProducts();
        MessageDto message = new MessageDto();

        model.addAttribute("message", message);
        model.addAttribute("products", latestProducts);
        return "index";
    }

    //about us
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    //contact us
    @GetMapping("/contact")
    public String contact(Model model){
        MessageDto message = new MessageDto();

        model.addAttribute("message", message);
        return "contact";
    }

    @PostMapping("/contact")
    public String send(@ModelAttribute("message") MessageDto messageDto, Model model){
        messageService.create(messageDto);

        model.addAttribute("success", "Thank you, your message sent successfully ");
        return "contact";
    }


    //products
    @GetMapping("/products")
    public String products(Model model){
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }


    @GetMapping("/products/{name}")
    public String showProduct(@PathVariable("name") String name, Model model){
        Product product = productService.findByName(name);

        model.addAttribute("product", product);
        return "product-detail";
    }
}
