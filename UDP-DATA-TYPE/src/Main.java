import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        //
        DatagramSocket socket = new DatagramSocket();
        InetAddress iA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String code = ";B21DCCN427;XCs5METr";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(),code.length() , iA , sP);
        socket.send(dpGui);

        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer , buffer.length);
        socket.receive(dpNhan);

        String st = new String(dpNhan.getData());
        String []sTmp = st.trim().split(";");
        String rI = sTmp[0];
        String s1 = sTmp[1];
        String s2 = sTmp[2];
        int n = Integer.parseInt(s1.trim());
        String []num = s2.trim().split(",");
        Set<Integer> nums = new HashSet<>();
        for(String s : num){
            nums.add(Integer.parseInt(s));
        }

        String ans = "";
        for (int i = 1 ; i <= n ; i++){
            if (!nums.contains(i)){
                ans += STR."\{i},";
            }
        }

        String res = STR."\{rI};\{removeLastChar(ans)}";
        System.out.println(res);

        DatagramPacket dpGuiKq = new DatagramPacket(res.getBytes(), res.length(), iA,sP);
        socket.send(dpGuiKq);

        socket.close();
    }

    public static String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }
}