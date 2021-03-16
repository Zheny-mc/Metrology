package test;
import java.io.*;

public class Example {
	public void writeFile() {
		File file = new File("E:\\metrology\\lb1\\test\\src\\test\\testFile.txt");
		
		try(FileReader reader = new FileReader(file))
        {
           // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                 
                System.out.print((char)c);
            } 
            reader.close();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }
	}
	
}
