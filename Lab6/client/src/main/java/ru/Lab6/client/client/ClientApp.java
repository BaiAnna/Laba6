package ru.Lab6.client.client;



import ru.Lab6.client.console.ConsoleReader;
import ru.Lab6.client.console.ReadDataConsole;
import ru.Lab6.client.generationRequests.GenerationRequestImpl;

import java.io.IOException;
import java.nio.channels.UnresolvedAddressException;
import java.util.Scanner;

public class ClientApp {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        try {
            ConsoleReader consoleReader = new ConsoleReader();
            ClientImpl serverAPI = new ClientImpl();
            GenerationRequestImpl generationRequest = new GenerationRequestImpl();
            ReadDataConsole readDataConsole = new ReadDataConsole(consoleReader, serverAPI, generationRequest);
            readDataConsole.go(consoleReader);
        } catch (UnresolvedAddressException e) {
            System.out.println("Сервер с данным адресом не доступен");
        } catch (IOException e) {
            while (true) {
                System.out.println("Сервер не доступен. Закончить работу клиента? Если да, напишите yes, иначе- no");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    break;
                } else if (answer.equals("")) {
                    break;}
                else if (answer.equals("no")){
                    System.out.println("Подключение....");
                    continue;
                } else {
                    System.out.println("Введите корректный ответ");
                }
            }
        }
    }
}





