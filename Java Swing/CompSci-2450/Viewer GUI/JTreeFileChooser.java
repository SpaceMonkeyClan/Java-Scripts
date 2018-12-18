import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*Here i use the FileNameExtensionFilter class which inherits from the FileFilt-
er class to filer files by file extension. Its constructor accepts the file 
extensions and its description. The second argument is a variable length 
argument. After i create an object of the FileNameExtensionFilter class, i 
needed to call the addChoosableFileFilter() method of the file chooser to set a
filter. The following code adds "java" and "jav" as file name extension filters.
*/

public class JTreeFileChooser {
  public static void main(String[] args) {
    FileNameExtensionFilter extFilter = new FileNameExtensionFilter(
        "Java Source  File", "java", "jav");
    // Set the file filter
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.addChoosableFileFilter(extFilter);

    int returnValue = fileChooser.showDialog(null, "Attach");
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      // Process the file
    }

  }
}
