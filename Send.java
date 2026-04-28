import java.net.*;

class Send {
    static byte[] converter(String text) {
        byte[] bytes = new byte[text.length()];

        for (int x = 0; x < text.length(); x++)
            bytes[x] = (byte) text.charAt(x);

        return bytes;
    }

    static String converter(byte[] bytes) {
        String text = "";

        for (int x = 0; x < bytes.length; x++)
            text += (char) bytes[x];

        return text;
    }

    public static void main(String[] args) {
        try {
            byte[] bytes = new byte[3];
            DatagramSocket socket = new DatagramSocket(8000);

            socket.send(
                new DatagramPacket(
                    converter("" + args[0].charAt(0) + args[0].charAt(1)),
                    2, InetAddress.getByName("43.217.230.24"), 8000
                )
            );

            socket.send(
                new DatagramPacket(
                    converter("" + args[0].charAt(2) + args[0].charAt(3)),
                    2, InetAddress.getByName("56.68.14.177"), 8000
                )
            );

            System.out.print("Message sent successfully");
        } catch (Exception exc) {
            System.out.print(exc);
        };
    }
}