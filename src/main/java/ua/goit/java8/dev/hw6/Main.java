package ua.goit.java8.dev.hw6;

import ua.goit.java8.dev.hw6.services.ClientService;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
//        System.out.println(clientService.create("Maks BBN"));
//
//        System.out.println(clientService.getById(6));

//      clientService.setName(6L, "MAKS BBN");

//        clientService.deleteById(8);

        System.out.println(clientService.listAll());
    }
}
