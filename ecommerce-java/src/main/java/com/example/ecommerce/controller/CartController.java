package com.example.ecommerce.controller;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        List<CartItem> cart = getCartFromSession(session);
        double total = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("userId") == null) {
            redirectAttributes.addFlashAttribute("error", "Please login to add items to cart");
            return "redirect:/login";
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        List<CartItem> cart = getCartFromSession(session);
        Optional<CartItem> existingItem = cart.stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
        } else {
            cart.add(new CartItem(product, 1));
        }

        session.setAttribute("cart", cart);
        redirectAttributes.addFlashAttribute("message", "Product added to cart successfully");
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        List<CartItem> cart = getCartFromSession(session);
        boolean removed = cart.removeIf(item -> item.getProduct().getId().equals(id));
        
        if (removed) {
            session.setAttribute("cart", cart);
            redirectAttributes.addFlashAttribute("message", "Product removed from cart");
        }
        return "redirect:/cart";
    }

    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable String id, @RequestParam int quantity, 
                               HttpSession session) {
        List<CartItem> cart = getCartFromSession(session);
        cart.stream()
            .filter(item -> item.getProduct().getId().equals(id))
            .findFirst()
            .ifPresent(item -> item.setQuantity(Math.max(1, quantity)));
        
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "Please login to checkout");
            return "redirect:/login";
        }

        List<CartItem> cart = getCartFromSession(session);
        if (cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty");
            return "redirect:/cart";
        }

        // Create new order
        Order order = new Order();
        order.setUserId(userId);
        order.setItems(new ArrayList<>(cart));
        order.setTotalAmount(cart.stream().mapToDouble(CartItem::getTotalPrice).sum());
        orderRepository.save(order);

        // Clear the cart
        session.removeAttribute("cart");
        redirectAttributes.addFlashAttribute("message", "Order placed successfully!");
        return "redirect:/products";
    }

    private List<CartItem> getCartFromSession(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
