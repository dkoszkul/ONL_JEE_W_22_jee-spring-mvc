package pl.coderslab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Controller
@SessionAttributes("loginStart")
public class SessionController {

    private static final String LOGIN_START_KEY = "loginStart";

    @GetMapping("login")
    @ResponseBody
    public String login(Model model, HttpSession session) {
        model.asMap().entrySet()
                .forEach(kv -> log.info("{} : {}", kv.getKey(), kv.getValue()));

        // to mogloby jeszcze trafic do orElse()
        if(!model.asMap().containsKey(LOGIN_START_KEY)) {
            LocalDateTime now = LocalDateTime.now();
            session.setAttribute(LOGIN_START_KEY, now);
            return now.toString();
        }

        return model.asMap().entrySet()
                .stream()
                .filter(kv -> kv.getKey().equals(LOGIN_START_KEY))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse("Brak")
                .toString();
    }
}
