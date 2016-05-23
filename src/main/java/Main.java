import javax.swing.*;
import java.awt.*;
import java.util.Optional;


public class Main {

  public static final Optional<String> port = Optional.ofNullable(System.getenv("PORT"));

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {

      EventQueue.invokeLater(new Runnable() {
                                 public void run() {

                                     JFrame frame1 = new Frame();
                                     frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                     frame1.setVisible(true);
                                 }
                             }
      );

  }
}
