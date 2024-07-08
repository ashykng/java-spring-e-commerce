package com.example.online_shop.controllers;

import com.example.online_shop.models.Cart;
import com.example.online_shop.models.Order;
import com.example.online_shop.models.Payment;
import com.example.online_shop.models.User;
import com.example.online_shop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model) {
        int userId = getUserDetails().getId();
        List<Cart> items = cartService.getCart(userId);
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("items", items);
        return "user/cart";
    }

    @GetMapping("/cart/add/{name}")
    public String add(@PathVariable String name, Model model, RedirectAttributes redirectAttributes) {
        int userId = getUserDetails().getId();

        if (cartService.addToCart(name, userId)) {
            redirectAttributes.addFlashAttribute("success", "item added to cart successfully");
        } else
            redirectAttributes.addFlashAttribute("fail", "item already exist you can select more!");

        return "redirect:/dashboard/cart";
    }

    @DeleteMapping("/cart/delete/{name}")
    public String delete(@PathVariable String name, RedirectAttributes redirectAttributes) {
        int userId = getUserDetails().getId();
        cartService.deleteByNameAndUserId(name, userId);
        redirectAttributes.addFlashAttribute("deleted", "item removed successfully !");
        return "redirect:/dashboard/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        int userId = getUserDetails().getId();
        Double totalPrice = cartService.getTotalPriceByUser(userId);
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("totalPrice", totalPrice);

        return "user/checkout";
    }

    @PostMapping("/checkout")
    public void checkout(@RequestBody Map<String, String> contactInfo) {
        User user = userService.findById(getUserDetails().getId());

        //update info
        String newPhoneStr = contactInfo.get("phone");
        String newAddress = contactInfo.get("address");
        Long newPhone = Long.parseLong(contactInfo.get("phone"));

        if(newPhoneStr != null || newAddress != null)
            userService.updateContactInfo(user.getId(), newPhone, newAddress);
    }

    @PostMapping("/cart/updateQuantities")
    @ResponseBody
    public ResponseEntity<?> updateQuantities(@RequestBody List<UpdateQuantityRequest> requests) {
        User user = userService.findById(getUserDetails().getId());
        cartService.updateQuantities(requests, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        return "user/payment";
    }

    @GetMapping("/order-confirmation")
    @Transactional
    public String orderConfirm(RedirectAttributes redirectAttributes, Model model) {
        User user = userService.findById(getUserDetails().getId());
        Double totalAmount = cartService.getTotalPriceByUser(getUserDetails().getId());
        List<Cart> items = cartService.getCart(user.getId());

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAmount(totalAmount);
        paymentService.create(payment);

        for (Cart item : items) {
            orderService.addToOrder(item.getProduct(), item.getQuantity(), user, payment);
            productService.updateInventory(item.getProduct(), item.getQuantity());
        }

        List<Order> orders = orderService.findByUserAndPayment(user, payment);

        cartService.deleteAllCartItemsByUser(getUserDetails().getId());

        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "user/order-confirmation";
    }




    private CustomUserDetail getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetail) authentication.getPrincipal();
    }


    public static class UpdateQuantityRequest {
        private int productId;
        private int quantity;

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}