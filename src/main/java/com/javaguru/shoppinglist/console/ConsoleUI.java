package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class ConsoleUI {
    private final List<Action> actions;

    public ConsoleUI(List<Action> actions) {
        this.actions = actions;
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int response = 0;

        while (response >= 0) {
            printMenu();
            try {
                response = Integer.parseInt(reader.readLine());
                actions.get(response).execute();
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                e.printStackTrace();
            }
        }
    }

    private void printMenu() {
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i));
        }
    }

}


