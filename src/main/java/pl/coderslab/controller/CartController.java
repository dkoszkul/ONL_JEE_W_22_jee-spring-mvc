package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Cart;
import pl.coderslab.model.CartItem;
import pl.coderslab.model.Product;

import java.util.Random;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final Cart cart;

//    @Autowired
//    public CartController(Cart cart) {
//        this.cart = cart;
//    }

    @GetMapping("/addtocart")
    @ResponseBody
    public String addtocart() {
        Random rand = new Random();
        cart.addToCart(new CartItem(1, new Product("prod" + rand.nextInt(10), rand.nextInt())));
        return "addtocart";
    }

    @GetMapping("cart")
    @ResponseBody
    public String showAllItemsFromCart() {
        return cart.getCartItems().toString();
    }

}
