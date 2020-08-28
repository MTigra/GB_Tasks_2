package com.martirosyan.hw2;

import com.martirosyan.hw2.controller.ShopController;
import com.martirosyan.hw2.repository.ShopRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PrepareDataApp prep = new PrepareDataApp();
        prep.prepareDb();
        runCLI();

    }

    public static void runCLI() {
        Scanner scanner = new Scanner(System.in);
        ShopRepository repository = new ShopRepository();
        ShopController shopController = new ShopController();
        Scanner scan = new Scanner(System.in);
        System.out.println("Для выхода из прогаммы напишите \"exit\"");
        while (true) {
            System.out.print("Введите команду: ");
            String command = scan.next();
            if (command.equals("exit")) return;
            String params = scan.nextLine();
            command = command.strip();
            params = params.strip();

            shopController.execute(command, params);
        }
    }
}
