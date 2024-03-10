package com.ll.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final int CAPACITY = 10;
    private Scanner sc;
    private int lastQuotationId;
    private List<Quotation> quotations;

    public App() {
        sc = new Scanner(System.in);
        lastQuotationId = 0;
        quotations = new ArrayList<>();

        initCapacity();
    }

    public void initCapacity() {
        for (int i=0; i < this.CAPACITY; i++) {
            lastQuotationId++;
            int id = lastQuotationId;
            String content = "Quote" + id;
            String author = "Quote" + id;
            Quotation quotation = new Quotation(id, content, author);
            quotations.add(quotation);
        }
    }

    public void run() {

        System.out.println("=== Quotes App ===");

        while (true) {
            System.out.print("Command) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            String action = rq.getAction().toLowerCase();
            switch (action) {
                case "quit":
                    return;
                case "register":
                    register();
                    break;
                case "list":
                    list();
                    break;
                case "delete":
                    delete(rq);
                    break;
                case "modify":
                    modify(rq);
                    break;
                default:
                    System.out.println("I can't understand your command");
            }
        }
    }

    private void register() {
        System.out.println("Quote: ");
        String content = sc.nextLine();

        System.out.println("Author: ");
        String author = sc.nextLine();

        System.out.printf("Quote: %s\n", content);
        System.out.printf("Author: %s\n", author);

        lastQuotationId++;
        System.out.printf("Quote No.%d) %s has been registered\n", lastQuotationId, content);

        int id = lastQuotationId;
        Quotation quotation = new Quotation(id, content, author);
        quotations.add(quotation);
    }

    private void list() {
        if (quotations.isEmpty()) {
            System.out.println("There aren't quotations");
        } else {
            System.out.println("No. / Author / Quote");
            for (int i = quotations.size() - 1; i >= 0; i--) {
                Quotation quotation = quotations.get(i);
                System.out.printf("%d / %s / %s\n", quotation.getId(), quotation.getAuthor(), quotation.getContent());
            }

        }
    }

    private void delete(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("Please enter the 'id' correctly.");
            return;
        }

        int index = getIndexOfQuotationById(id);
        if (index == -1) {
            System.out.printf("No.%d quotation does not exist.\n", id);
            System.out.println("The following are the currently registered quotation numbers:");
            listAvailableQuotationsIds();
            return;
        }
        quotations.remove(index);
        System.out.printf("Quotation No. %d has been successfully deleted.\n", id);
    }

    // Method to print available quotation IDs
    private void listAvailableQuotationsIds() {
        if (quotations.isEmpty()) {
            System.out.println("There are no registered quotations.");
        } else {
            System.out.print("Numbers: ");
            for (Quotation quotation : quotations) {
                System.out.print(quotation.id + " ");
            }
        }
        System.out.println();
    }

    private int getIndexOfQuotationById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);
            if (quotation.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void modify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("Please enter the 'id' correctly.");
            return;
        }
        int index = getIndexOfQuotationById(id);
        if (index == -1) {
            System.out.printf("No.%d quotation does not exist.\n", id);
            System.out.println("The following are the currently registered quotation numbers:");
            listAvailableQuotationsIds();
            return;
        }
        Quotation quotation = quotations.get(index);

        System.out.printf("existing quotation) %s\n", quotation.getContent());
        System.out.print("Write new quotation: ");
        String newQuote = sc.nextLine();
        quotation.setContent(newQuote);
        System.out.printf("new) %s\n", quotation.getContent());

        System.out.printf("existing author) %s\n", quotation.getAuthor());
        System.out.print("Write new author: ");
        String newAuthor = sc.nextLine();
        quotation.setAuthor(newAuthor);
        System.out.printf("new) %s\n", quotation.getAuthor());

        System.out.printf("No.%d quotation has been modified.\n", id);
    }
}
