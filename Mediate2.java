import java.net.*;
import java.util.Scanner;

class Network2 extends Thread {
    byte[] converter(String text) {
        byte[] bytes = new byte[text.length()];

        for (int x = 0; x < text.length(); x++)
            bytes[x] = (byte) text.charAt(x);

        return bytes;
    }

    String converter(byte[] bytes) {
        String text = "";

        for (int x = 0; x < bytes.length; x++)
            text += (char) bytes[x];

        return text;
    }

    String state = "receive";
    static boolean condition = true;

    public void run() {
        try {
            byte[] bytes = new byte[3];
            
            DatagramSocket socket = new DatagramSocket(8000);
            DatagramPacket packet = new DatagramPacket(bytes, 3);
            socket.setSoTimeout(1000);

            while (condition) {
                try {
                    sleep(1);
                } catch (Exception interruption) {
                    System.out.print(interruption);
                }

                if (state.equals("receive")) {
                    try {
                        socket.receive(packet);
                        System.out.print("packet received successfully");
                        state = "deliver";
                    } catch (SocketTimeoutException timeout) {};
                } else {
                    bytes[2] = (byte)'2';

                    while (true) {
                        try {
                            sleep(1);
                        } catch (Exception interruption) {
                            System.out.print(interruption);
                        }

                        if (condition == false)
                            break;

                        socket.send(
                            new DatagramPacket(
                                bytes, 3, 
                                InetAddress.getByName("43.217.108.88"), 8000
                            )
                        );

                        try {
                            socket.receive(packet);
                            state = "receive";
                            break;
                        } catch (SocketTimeoutException timeout) {}
                    }
                }

                try {
                    sleep(1);
                } catch (Exception interruption) {
                    System.out.print(interruption);
                };
            }
        } catch (Exception exc) {
            System.out.println("\n" + exc);
        };
    }
}

class Mediate2 {
    public static void main(String[] args) {
        new Network2().start();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter 'exit and confirm' without the quotation marks in order to exit program: ");

            if (scanner.nextLine().equals("exit and confirm")) {
                Network2.condition = false;
                break;
            }
        }
    }
}