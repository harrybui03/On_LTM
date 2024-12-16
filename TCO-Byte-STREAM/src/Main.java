import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String code = "B21DCCN427;P4jI46Qz";
        out.write(code.getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int byteRead = in.read(buffer);
        String s = new String(buffer , 0 , byteRead);
        Set<Character> set = new HashSet<>();
        String res = "";
        int maxLn = 0;
        int start = 0;
        for (int i = 0 ; i < s.length() ; i++){
            while(set.contains(s.charAt(i))){
                set.remove(s.charAt(start));
                start += 1;
            }

            set.add(s.charAt(i));
            if (i - start + 1 > maxLn) {
                maxLn = i - start + 1;
                res = s.substring(start , i + 1);
            }
        }

        res = res + ";" + res.length();
        System.out.println(res);
        out.write(res.getBytes());
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}