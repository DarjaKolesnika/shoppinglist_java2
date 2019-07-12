package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfiguration {

    private Action createProductAction;
    private Action deleteProductAction;
    private Action editProductAction;
    private Action exitAction;
    private Action findProductAction;

    @Autowired
    public ConsoleUIConfiguration(Action createProductAction, Action deleteProductAction, Action editProductAction,
                                  Action exitAction, Action findProductAction) {
        this.createProductAction = createProductAction;
        this.deleteProductAction = deleteProductAction;
        this.editProductAction = editProductAction;
        this.exitAction = exitAction;
        this.findProductAction = findProductAction;
    }

    @Bean
    ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(createProductAction);
        actions.add(deleteProductAction);
        actions.add(exitAction);
        actions.add(editProductAction);
        actions.add(findProductAction);
        return new ConsoleUI(actions);
    }
}
