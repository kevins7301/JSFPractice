package at.jsfpractice.jsf22.fileuploadanddownload;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import at.jsfpractice.jsf22.testDB.testDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.primefaces.model.DefaultStreamedContent;
import org.apache.commons.codec.binary.Base64;


@ManagedBean (name="fileUploadAndDownload")
public class FileUploadAndDownload 
{
	private UploadedFile ulFile;
	private StreamedContent SCFile;

	
	
	
	
    public UploadedFile getUlFile() {
		return ulFile;
	}

	public void setUlFile(UploadedFile ulFile) {
		this.ulFile = ulFile;
	}

	public StreamedContent getSCFile() {
		return SCFile;
	}

	public void setSCFile(StreamedContent sCFile) {
		SCFile = sCFile;
	}

	public void upload() 
	{
		
		System.err.println("upload~~~");
//        if(ulFile != null) 
//        {
        	// do into DataBase convert base64 byte
        	ArrayList<File> fileList = new ArrayList<File>();
        	ArrayList filePathList = new ArrayList();
        	
        	File file = null;
        	for (int i = 1; i < 7; i++)
        	{
        		file = new File("D:/image/0" + i + ".jpg");
        		fileList.add(file);
        		filePathList.add("D:/image/0" + i + ".jpg");
        	}
        	 
        	ArrayList<FileInputStream> finList = new ArrayList<FileInputStream>();
        	ArrayList finLengthList = new ArrayList();
        	
        	
//            try 
//            {     
//            	for (File inputfile : fileList)
//            	{
            		// Reading a Image file from file system
//                    FileInputStream imageInFile = new FileInputStream(inputfile);
                    // upload database
//                    finList.add(imageInFile);
//                    finLengthList.add(inputfile.length());
                    
                    //
//                    byte imageData[] = new byte[(int) inputfile.length()];
//                    imageInFile.read(imageData);
         
                    // Converting Image byte array into Base64 String
//                    String imageDataString = encodeImage(imageData);
         
                	// Converting a Base64 String into Image byte array
//                    byte[] imageByteArray = decodeImage(imageDataString);
         
                    // Write a image byte array into file system
//                    FileOutputStream imageOutFile = new FileOutputStream(
//                            "/Users/jeeva/Pictures/wallpapers/water-drop-after-convert.jpg");
//         
//                    imageOutFile.write(imageByteArray);
         
//                    imageInFile.close();
//                    imageOutFile.close();
//            	}
            	
            	testDAO.upload(filePathList/*, finList, finLengthList*/);
     
//                System.out.println("Image Successfully Manipulated!");
//            } 
//            catch (FileNotFoundException e) 
//            {
//                System.out.println("Image not found" + e);
//            } 
//            catch (IOException ioe) 
//            {
//                System.out.println("Exception while reading the Image " + ioe);
//            }
//        }
        
        
        testDAO.test();
        	
//        FacesMessage message = new FacesMessage("Succesful", ulFile.getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
        
	}

    
    public FileUploadAndDownload() {        
//        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
//        SCFile = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
    }
    
    
    
    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
    	
//    	Base64.en
    	
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
 
    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
}
