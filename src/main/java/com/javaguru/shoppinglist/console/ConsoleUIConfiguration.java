package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfiguration {

    @Autowired
    private Action createProductAction;
    @Autowired
    private Action deleteProductAction;
    @Autowired
    private Action editProductAction;
    @Autowired
    private Action exitAction;
    @Autowired
    private Action findProductAction;
    @Autowired
    private Action addToCartAction;
    @Autowired
    private Action createCartAction;
    @Autowired
    private Action deleteCartAction;
    @Autowired
    private Action findCartAction;

    @Bean
    ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(createProductAction);
        actions.add(findProductAction);
        actions.add(deleteProductAction);
        actions.add(editProductAction);
        actions.add(createCartAction);
        actions.add(findCartAction);
        actions.add(addToCartAction);
        actions.add(deleteCartAction);
        actions.add(exitAction);
        return new ConsoleUI(actions);
    }
}
